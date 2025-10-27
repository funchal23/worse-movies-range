package br.com.outsera.worse_movie.domain.shared.valueobject;

import br.com.outsera.worse_movie.domain.shared.exception.InvalidProducerNameException;


public record ProducerName(String value) {
    public ProducerName {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidProducerNameException("Producer name cannot be null or empty");
        }
        value = value.trim();
    }

    public static ProducerName from(String value) {
        return new ProducerName(value);
    }
}