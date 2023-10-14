//package com.example.onlineshop.config;
//
//
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//
//import java.io.IOException;
//
//
//@Component
//public class LoggingFilter extends OncePerRequestFilter {
//    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        HttpServletRequest wrappedRequest = new ContentCachingRequestWrapper(request);
//        filterChain.doFilter(wrappedRequest, response);
//        String method = wrappedRequest.getMethod();
//        String uri = wrappedRequest.getRequestURI();
//        String body = new String(((ContentCachingRequestWrapper) wrappedRequest).getContentAsByteArray());
//        LOGGER.info("HTTP Method: {}, URI: {}, Body: {}", method, uri, body);
//    }
//
//
//}
