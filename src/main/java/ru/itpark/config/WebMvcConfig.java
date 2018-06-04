package ru.itpark.config;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry
                .addViewController("/login")
                .setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
