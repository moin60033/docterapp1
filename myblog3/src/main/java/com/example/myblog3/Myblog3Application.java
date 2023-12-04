package com.example.myblog3;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Myblog3Application {

	public static void main(String[] args) {
		SpringApplication.run(Myblog3Application.class, args);
	}
	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}

}
