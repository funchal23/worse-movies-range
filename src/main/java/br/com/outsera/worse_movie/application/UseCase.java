package br.com.outsera.worse_movie.application;

public interface UseCase<T, Y> {
    T execute(Y request);
}
