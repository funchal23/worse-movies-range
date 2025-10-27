package br.com.outsera.worse_movie.domain.shared.valueobject;

import br.com.outsera.worse_movie.domain.shared.exception.InvalidStudioException;

public record Studio(String value) {
    public Studio {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidStudioException("Studio cannot be null or empty");
        }
        value = value.trim();
    }
    
    public static Studio from(String value) {
        return new Studio(value);
    }
}