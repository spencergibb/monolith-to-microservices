package tech.gibb.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HelloApplication {

	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return "HELLO " + name;
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}
}
