package com.example.projectperfectus.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(infoAPI());
    }
    public Info infoAPI() {
        return new Info().title("Profecus RH")
                .description("Profecus")
                .contact(contactAPI());
    }
    public Contact contactAPI() {
        Contact contact = new Contact().name("")
                .email("")
                .url("");
        return contact;
    }
}
