package br.com.outsera.worse_movie.domain.movie;

import lombok.Getter;

import java.util.List;

@Getter
public class MovieValidator {

    private List<String> errors;
    private boolean isValid;

    public void validate(MovieDomain movie) {
        errors = new java.util.ArrayList<>();

        if (movie.getYear() == null || movie.getYear() <= 0) {
            errors.add("Year must be a positive integer.");
        }
        if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
            errors.add("Title cannot be null or empty.");
        }
        if (movie.getStudios() == null || movie.getStudios().trim().isEmpty()) {
            errors.add("Studios cannot be empty.");
        }
        if (movie.getProducers().isEmpty()) {
            errors.add("Producers cannot be empty.");
        }

        isValid = errors.isEmpty();
        movie.addValidation(this);
    }
}
