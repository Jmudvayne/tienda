package com.payulatam.integracion.service;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceIntegracionApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RestServiceIntegracionApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8086"));
        app.run(args);
        
		
	}

}
