package com.test.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.test")
@EntityScan(basePackages = "com.test.entity")
@EnableJpaRepositories(basePackages = "com.test.repositories")
public class TaskDashboardServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskDashboardServicesApplication.class, args);
	}

}
