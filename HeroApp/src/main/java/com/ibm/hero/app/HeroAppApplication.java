package com.ibm.hero.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({ "com.ibm.hero.controllers", "com.ibm.hero.services" })
public class HeroAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroAppApplication.class, args);
	}

}
