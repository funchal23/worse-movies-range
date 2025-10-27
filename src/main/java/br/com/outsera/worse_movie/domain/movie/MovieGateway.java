package br.com.outsera.worse_movie.domain.movie;

import java.util.List;

public interface MovieGateway {
    void save(List<Movie> movies);
    List<Movie> findByWinners();
}
