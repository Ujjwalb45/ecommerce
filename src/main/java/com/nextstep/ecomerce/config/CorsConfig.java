package com.nextstep.ecomerce.config;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class CorsConfig implements CorsConfigurationSource {



    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest httpServletRequest) {
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("*"));

        return corsConfiguration;
    }
}
