package br.com.outsera.worse_movie.domain.movie;

import java.util.List;

public interface MovieGateway {
    void save(List<MovieDomain> movies);
    List<MovieDomain> findByWinners();
}
