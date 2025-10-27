package br.com.outsera.worse_movie.domain.shared.exception;

public class InvalidTitleException extends DomainException {
    public InvalidTitleException(String message) {
        super(message);
    }
}