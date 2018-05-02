package tech.gibb.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("index", p -> p.path("/")
						.filters(f -> f.setPath("/index.html"))
						.uri("lb://ui"))
				.route("ui", p -> p.path("/").or().path("/css/**").or().path("/js/**")
						.uri("lb://ui1"))
				.route("monolith", p -> p.path("/**")
						.uri("http://localhost:8081"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
