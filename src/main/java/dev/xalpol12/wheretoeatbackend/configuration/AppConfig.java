package dev.xalpol12.wheretoeatbackend.configuration;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableConfigurationProperties(GoogleApiConfig.class)
public class AppConfig {
    private final GoogleApiConfig googleConfig;

    @Autowired
    public AppConfig(GoogleApiConfig googleConfig) {
        this.googleConfig = googleConfig;
    }
    @Bean(destroyMethod = "shutdown")
    @Scope(value = "singleton")
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder().apiKey(googleConfig.placesApiKey()).queryRateLimit(2).disableRetries().build();
    }
}
