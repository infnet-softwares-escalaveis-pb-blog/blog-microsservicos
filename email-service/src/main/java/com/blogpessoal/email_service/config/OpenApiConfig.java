package com.blogpessoal.email_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Email Service API")
                        .version("1.0")
                        .description("Microserviço responsável pelo envio de e-mails do Blog Pessoal")
                )
                .addServersItem(new Server()
                .url("http://localhost:8083")
                .description("Servidor local"));
    }
}