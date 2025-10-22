package br.com.outsera.worse_movie.exception;

public class NoProducerHasMoreThanTwoTitlesException extends RuntimeException {
    public NoProducerHasMoreThanTwoTitlesException() {
        super("No producer has more than two titles");
    }
}
