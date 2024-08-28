package com.tweeter.reply_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ReplyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReplyServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate RestTemplate() {
		return new RestTemplate();
	}
}
