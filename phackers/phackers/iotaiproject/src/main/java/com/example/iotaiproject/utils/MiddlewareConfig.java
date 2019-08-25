package com.example.iotaiproject.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MiddlewareConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                //.allowedOrigins("http://192.168.100.106:8081")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD","OPTIONS")
//                .allowCredentials(true)
//                //.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept");
//                .allowedHeaders("Access-Control-Allow-Origin", "*");
//
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

}