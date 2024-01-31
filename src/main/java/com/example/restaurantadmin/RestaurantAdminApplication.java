package com.example.restaurantadmin;

import com.example.restaurantadmin.security.CustomExceptionResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableConfigurationProperties
@SpringBootApplication()
public class RestaurantAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantAdminApplication.class, args);
    }

    @Bean
    public CustomExceptionResolver myCustomExceptionResolver() {
        return new CustomExceptionResolver();
    }
}
