package com.example.postCommentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PostCommentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostCommentServiceApplication.class, args);
	}

}
