package org.example.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Return the username of the current logged-in user
        // For simplicity, returning a fixed value here. In real applications, this should be dynamic.
        return Optional.of("system");
    }
}
