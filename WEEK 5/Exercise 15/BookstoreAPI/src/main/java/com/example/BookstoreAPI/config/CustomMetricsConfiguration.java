package com.example.bookstoreapi.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomMetricsConfiguration {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "bookstore-api");
    }
}
