package tech.gibb.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GatewayApplication {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("fortune", p -> p.path("/service/randomfortune")
						.filters(f -> f.hystrix(c -> c.setFallbackUri("forward:/defaultfortune")))
						.uri("http://localhost:8081"))
				.route("hello_rewrite", p -> p.path("/service/hello/**")
						.filters(f -> f.filter((exchange, chain) -> {
							String name = exchange.getRequest().getQueryParams().getFirst("name");
							String path = "/hello/"+name;
							ServerHttpRequest request = exchange.getRequest().mutate()
									.path(path)
									.build();
							return chain.filter(exchange.mutate().request(request).build());
						}))
						.uri("lb://hello"))
				.route("index", p -> p.path("/")
						.filters(f -> f.setPath("/index.html"))
						.uri("lb://ui"))
				.route("ui", p -> p.path("/").or().path("/css/**").or().path("/js/**")
						.uri("lb://ui"))
				.route("monolith", p -> p.path("/**")
						.uri("http://localhost:8081"))
				.build();
	}

	@RequestMapping("/defaultfortune")
	public String defaultFortune() {
		return "When you feel sad: start dot spring dot io.";
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
