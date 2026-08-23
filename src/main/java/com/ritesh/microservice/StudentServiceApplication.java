package com.ritesh.microservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.ritesh.microservice.feignclient")
@EnableDiscoveryClient
public class StudentServiceApplication
{
	@Value("${service.address-service.url}")
	String addressServiceUrl;

	public static void main(String[] args)
	{
		SpringApplication.run(StudentServiceApplication.class, args);
	}

	/*@Bean
	WebClient webClient()
	{
		WebClient webClient = WebClient.builder().baseUrl(addressServiceUrl)
				.build();
		return webClient;
	}*/

}
