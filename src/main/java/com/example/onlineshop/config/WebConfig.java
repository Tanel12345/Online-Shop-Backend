//package com.example.onlineshop.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class WebConfig implements WebMvcConfigurer {
//
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**")
//
//                .allowedOrigins("http://localhost:4200")
//                .allowedMethods("GET","POST","PUT", "DELETE")
////                .allowedHeaders("header1", "header2", "header3")
////                .exposedHeaders("header1", "header2")
//                .allowedHeaders("*")
//                .allowCredentials(true).maxAge(3600);
//
//        // Add more mappings...
//    }
//}
