package br.com.outsera.worse_movie.domain.shared.exception;

public abstract class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }
}