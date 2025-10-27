package br.com.outsera.worse_movie.application.port;

import br.com.outsera.worse_movie.domain.movie.Movie;

import java.util.List;

public interface SaveWorseMoviesUseCase {
    void execute(List<Movie> request);
}
