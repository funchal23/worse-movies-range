package br.com.outsera.worse_movie.infrastructure.mappers;

import br.com.outsera.worse_movie.domain.movie.Movie;
import br.com.outsera.worse_movie.domain.movie.MovieId;
import br.com.outsera.worse_movie.domain.shared.valueobject.Studio;
import br.com.outsera.worse_movie.domain.shared.valueobject.Title;
import br.com.outsera.worse_movie.domain.shared.valueobject.Year;
import br.com.outsera.worse_movie.infrastructure.persistence.entities.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    private final ProducerMapper producerMapper;

    public MovieMapper(ProducerMapper producerMapper) {
        this.producerMapper = producerMapper;
    }

    public List<MovieEntity> toEntityList(List<Movie> domains) {
        List<MovieEntity> entities = new ArrayList<>();
        domains.forEach(domain -> {
            MovieEntity movieEntity = MovieEntity.create(
                    domain.getId().value(),
                    domain.getYear().value(),
                    domain.getTitle().value(),
                    domain.getStudios().value(),
                    producerMapper.toEntityList(domain.getProducers()),
                    domain.isWinner()
            );
            entities.add(movieEntity);
        });
        return entities;
    }

    public List<Movie> toDomainList(List<MovieEntity> entities) {
        List<Movie> domains = new ArrayList<>();
        entities.forEach(entity -> {
            Movie movie = Movie.create(
                    MovieId.from(entity.getId()),
                    Title.from(entity.getTitle()),
                    Year.from(entity.getYear()),
                    Studio.from(entity.getStudios()),
                    producerMapper.toDomainList(entity.getProducers()),
                    entity.isWinner()
            );
            domains.add(movie);
        });
        return domains;
    }
}
