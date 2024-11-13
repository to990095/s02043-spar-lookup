package com.telenor.spar.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(getApiInformation());
    }

    private Info getApiInformation() {
        return new Info()
                .title("SPAR Look Up Service")
                .version("V1")
                .contact(new Contact()
                        .name("Spar Modernization"));
                      //  .url("https://prima.corp.telenor.no/confluence/display/MBSS/Economy+Services"));
    }
}