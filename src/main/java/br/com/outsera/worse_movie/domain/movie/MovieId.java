package br.com.outsera.worse_movie.domain.movie;

import java.util.Objects;
import java.util.UUID;

public record MovieId(String value) {
    public MovieId {
        Objects.requireNonNull(value, "Movie ID value cannot be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Movie ID cannot be empty");
        }
    }
    public static MovieId generate() {
        return new MovieId(UUID.randomUUID().toString());
    }
    public static MovieId from(String id) {
        return new MovieId(id);
    }
}