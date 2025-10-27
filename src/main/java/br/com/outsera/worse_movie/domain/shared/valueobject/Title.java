package br.com.outsera.worse_movie.domain.shared.valueobject;

import br.com.outsera.worse_movie.domain.shared.exception.InvalidTitleException;

public record Title(String value) {
    public Title {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidTitleException("Title cannot be null or empty");
        }
        value = value.trim();
    }
    public static Title from(String value){
        return new Title(value);
    }
}