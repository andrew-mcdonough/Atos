package com.qikserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.qikserve")
public class QikServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(QikServerApplication.class, args);
	}
}
