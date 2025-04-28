package com.dr7.dr7.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiSwageer {
    @Bean
    public OpenAPI Validation(){
        return  new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("jwt")
                        )
                ).info(new Info()
                        .title("zapdai")
                        .description("Sistema de pedidos")
                        .contact(new Contact()
                                .email("zapdaicompany@gmail.com\n")
                                .name("zapdai")
                        )
                        .license(new License()
                                .name("apache 2.0")
                                .url("http://localhost:8080/empresa")
                        )
                );

    }
}
//http://localhost:8080/swagger-ui/index.html