package br.com.outsera.worse_movie.domain.shared.exception;


public class InvalidYearException extends DomainException {
    public InvalidYearException(String message) {
        super(message);
    }
}