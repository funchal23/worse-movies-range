package br.com.outsera.worse_movie.application;

public interface UseCaseIn<T> {
    void execute(T request);
}
