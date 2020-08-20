package rao.saikrishna.apps.credmgr.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfiguration {

    @Value("${server.servlet.context-path}")
    private String apiContextRoot;

    @Value("#{'${credmgr.api.whitelisted-origins}'.split(',')}")
    private String allowedOrigins [];

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String rootPath = "/**";
                registry.addMapping(rootPath)
                        .allowedOrigins(allowedOrigins)
                        .allowedMethods("PUT", "POST", "GET", "DELETE", "OPTIONS");
            }
        };
    }
}
