package br.com.outsera.worse_movie.domain.shared.valueobject;

import br.com.outsera.worse_movie.domain.shared.exception.InvalidYearException;

public record Year(int value) {
    public Year {
        if (value <= 0) {
            throw new InvalidYearException("Year must be a positive integer");
        }
    }
    public static Year from(int value) {
        return new Year(value);
    }
}