package com.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The main/application class must be in a root package above other classes
 * This is because @SpringBootApplication implicitly include @CompoenentScan 
 * with default attributes. Other implicitly included attributes are @Configuration 
 * and @EnableAutoConfiguration
 * @author cc03091
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class RESTfulStartup {
	
	public static void main(String[] args) {
		SpringApplication.run(RESTfulStartup.class, args);
	}
}
