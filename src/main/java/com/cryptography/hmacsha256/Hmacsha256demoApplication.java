package com.cryptography.hmacsha256;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cryptography.hmacsha256")
public class Hmacsha256demoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hmacsha256demoApplication.class, args);
	}

}
