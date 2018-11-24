package com.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main/application class must be in a root package above other classes
 * This is because @SpringBootApplication implicitly include @CompoenentScan 
 * with default attributes. Other implicitly included attributes are @Configuration 
 * and @EnableAutoConfiguration
 * @author cc03091
 *
 */
@SpringBootApplication
public class RESTfulStartup {
	
	public static void main(String[] args) {
		SpringApplication.run(RESTfulStartup.class, args);
	}
}
