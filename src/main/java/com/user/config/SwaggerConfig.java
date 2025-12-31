package com.user.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme securityScheme = createAPIKeyScheme();
        Components components = new Components()
                .addSecuritySchemes("Bearer Authentication", securityScheme);
        
        return new OpenAPI()
                .info(new Info()
                        .title("User Service API")
                        .description("RESTful API for User Management with JWT Authentication. " +
                                "Use the /api/auth/login endpoint to authenticate and get a JWT token. " +
                                "Then click the 'Authorize' button above and enter 'Bearer {your-token}' " +
                                "to access protected endpoints.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("User Service Team")
                                .email("support@userservice.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        new Server().url("http://localhost:8081").description("Local Development Server"),
                        new Server().url("https://api.example.com").description("Production Server")
                ))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(components);
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}

