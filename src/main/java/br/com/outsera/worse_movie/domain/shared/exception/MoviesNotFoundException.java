package br.com.outsera.worse_movie.domain.shared.exception;

public class MoviesNotFoundException extends RuntimeException {
    public MoviesNotFoundException() {
        super("No movies found in the database. Rerun application to load data from CSV.");
    }
}
