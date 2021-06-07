package com.mercedes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.mercedes")
public class FileInteractorImplementorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileInteractorImplementorApplication.class, args);
	}

}
