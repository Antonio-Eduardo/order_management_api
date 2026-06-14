package com.project.eduardo.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.server.url:}")
    private String serverUrl;

    @Bean
    public OpenAPI customAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Order Management API")
                        .version("1.0.0")
                        .description("API para gerenciamento de pedidos.\n")
                        .contact(new Contact()
                                .name("Antonio Eduardo")
                                .email("eduardo.moreira.java@gmail.com")));
    }
}
