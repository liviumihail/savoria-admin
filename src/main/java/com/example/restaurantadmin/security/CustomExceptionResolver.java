package com.example.restaurantadmin.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomExceptionResolver extends SimpleMappingExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return new ModelAndView("pages-404"); // Numele fișierului HTML personalizat cu eroarea 404
        }
        return null;
    }
}
