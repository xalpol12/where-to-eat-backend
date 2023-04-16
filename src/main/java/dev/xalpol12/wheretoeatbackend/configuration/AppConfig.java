package dev.xalpol12.wheretoeatbackend.configuration;

import com.google.maps.GeoApiContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableConfigurationProperties
public class AppConfig {
    private static final String variable = System.getenv("PLACES_API_KEY");

    @Bean(destroyMethod = "shutdown")
    @Scope(value = "singleton")
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder().apiKey(variable).queryRateLimit(2).disableRetries().build();
    }
}
