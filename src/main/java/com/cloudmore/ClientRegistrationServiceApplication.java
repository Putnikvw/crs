package com.cloudmore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition
public class ClientRegistrationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientRegistrationServiceApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenApi()  {
        return new OpenAPI()
                .components(new Components())

                .info(new Info().title("Client Service API")
                        .description("Service to store client information"));
    }
}
