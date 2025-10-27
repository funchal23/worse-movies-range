package br.com.outsera.worse_movie.domain.shared.exception;


public class InvalidStudioException extends DomainException {
    public InvalidStudioException(String message) {
        super(message);
    }
}