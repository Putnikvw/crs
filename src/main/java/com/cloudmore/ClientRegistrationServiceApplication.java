package com.cloudmore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition
public class ClientRegistrationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientRegistrationServiceApplication.class, args);
    }
}
