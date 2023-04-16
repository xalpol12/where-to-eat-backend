package dev.xalpol12.wheretoeatbackend.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("google")
public record GoogleApiConfig(String placesApiKey) {
}
