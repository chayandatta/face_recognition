package com.example.iotaiproject.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Secutiry implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {

            registry.addMapping("/**")
                    .allowedOrigins("http://35.237.44.109/chatbot")
                    .allowedOrigins("http://localhost:3000")
                    //.allowedOrigins("http://192.168.100.106:8081")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD","OPTIONS")
                    .allowCredentials(true)
                    //.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept");
                    .allowedHeaders("Access-Control-Allow-Origin", "*");

        }

}
