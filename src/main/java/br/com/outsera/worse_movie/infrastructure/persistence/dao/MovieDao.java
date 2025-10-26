package br.com.outsera.worse_movie.infrastructure.persistence.dao;

import br.com.outsera.worse_movie.domain.movie.MovieDomain;
import br.com.outsera.worse_movie.domain.movie.MovieGateway;
import br.com.outsera.worse_movie.infrastructure.persistence.entities.MovieEntity;
import br.com.outsera.worse_movie.infrastructure.persistence.repository.MovieRepository;
import br.com.outsera.worse_movie.utils.mappers.MovieMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MovieDao implements MovieGateway {

    private final MovieRepository repository;
    private final MovieMapper mapper;

    public MovieDao(MovieRepository repository, MovieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void save(List<MovieDomain> movies) {
        List<MovieEntity> entityList = mapper.toEntityList(movies);
        repository.saveAll(entityList);
    }

    @Override
    public List<MovieDomain> findByWinners() {
        List<MovieEntity> all = repository.findWinningMoviesWithRepeatedProducers();
        return mapper.toDomainListByEntity(all);
    }
}
