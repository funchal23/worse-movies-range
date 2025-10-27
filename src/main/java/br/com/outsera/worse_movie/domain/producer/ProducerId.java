package br.com.outsera.worse_movie.domain.producer;

import java.util.Objects;
import java.util.UUID;

public record ProducerId(String value) {
    
    public ProducerId {
        Objects.requireNonNull(value, "Producer ID value cannot be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Producer ID cannot be empty");
        }
    }
    public static ProducerId generate() {
        return new ProducerId(UUID.randomUUID().toString());
    }

    public static ProducerId from(String id) {
        return new ProducerId(id);
    }
}