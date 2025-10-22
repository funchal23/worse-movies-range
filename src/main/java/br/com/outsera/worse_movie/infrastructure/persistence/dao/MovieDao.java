package br.com.outsera.worse_movie.infrastructure.persistence.dao;

import br.com.outsera.worse_movie.domain.movie.MovieDomain;
import br.com.outsera.worse_movie.domain.movie.MovieGateway;
import br.com.outsera.worse_movie.infrastructure.persistence.entities.MovieEntity;
import br.com.outsera.worse_movie.infrastructure.persistence.repository.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MovieDao implements MovieGateway {

    private final MovieRepository movieRepository;

    public MovieDao(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void save(MovieDomain movie) {
        movieRepository.save(MovieEntity.fromEntity(movie));
    }

    @Override
    public List<MovieDomain> findAll() {
        List<MovieEntity> all = movieRepository.findAll();
        return MovieDomain.fromDomainEntityList(all);
    }
}
