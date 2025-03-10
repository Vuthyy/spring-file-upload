package com.vt.spring_file_upload.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi frontendGroup() {
        return GroupedOpenApi
                .builder()
                .group("frontend-api")
                .addOpenApiCustomizer(openApi -> openApi.info(getFrontedApiInfo()))
                .packagesToScan("com.vt.spring_file_upload.controller.frontend")
                .build();
    }

    private Info getFrontedApiInfo() {
        Contact contact = new Contact();
        contact.setName("Chan-Vuthy");
        contact.setEmail("vuthysh11@gmail.com");
        contact.setUrl("https://github.com/Vuthyy");

        return new Info()
                .title("Upload API")
                .description("Frontend Upload API")
                .contact(contact)
                .version("1.0.1");
    }
}
