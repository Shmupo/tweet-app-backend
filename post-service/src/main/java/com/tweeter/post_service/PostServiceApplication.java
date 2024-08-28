package com.tweeter.post_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate RestTemplate() {
		return new RestTemplate();
	}
}