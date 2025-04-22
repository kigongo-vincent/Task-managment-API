package com.tasks.tasks.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "app")
public record appProps(
    int id,
    String name
) {
    
}
