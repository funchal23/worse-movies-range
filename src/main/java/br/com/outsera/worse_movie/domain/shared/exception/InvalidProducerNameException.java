package br.com.outsera.worse_movie.domain.shared.exception;

public class InvalidProducerNameException extends DomainException {
    public InvalidProducerNameException(String message) {
        super(message);
    }
}