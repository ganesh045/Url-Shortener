package com.primesolutions.shorturlgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShortUrlGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortUrlGeneratorApplication.class, args);
	}
}
