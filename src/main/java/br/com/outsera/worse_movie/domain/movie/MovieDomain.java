package br.com.outsera.worse_movie.domain.movie;

import br.com.outsera.worse_movie.domain.Domain;
import br.com.outsera.worse_movie.exception.NoProducerHasMoreThanTwoTitlesException;
import br.com.outsera.worse_movie.infrastructure.file.dtos.MovieDto;
import br.com.outsera.worse_movie.infrastructure.persistence.entities.MovieEntity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Getter
public class MovieDomain extends Domain {
    private final Integer year;
    private final String title;
    private final List<String> studios;
    private final List<String> producers;
    private final boolean winner;
    private int line;
    private boolean isValid;
    private List<String> errors;

    public MovieDomain(MovieEntity entity) {
        this.year = entity.getYear();
        this.title = entity.getTitle();
        this.studios = entity.getStudios();
        this.producers = entity.getProducers();
        this.winner = entity.getWinner();
    }

    public MovieDomain(MovieDto dto) {
        this.year = dto.getYear();
        this.title = dto.getTitle();
        this.studios = dto.getStudios();
        this.producers = dto.getProducer();
        this.winner = dto.isWinner();
        this.line = dto.getLine();
        this.isValid = true;
        this.validate();
    }


    public static List<MovieDomain> fromDomainEntityList(List<MovieEntity> entities) {
        return entities.stream().map(MovieDomain::new).toList();
    }

    public static List<MovieDomain> fromDomainDtoList(List<MovieDto> dto) {
        List<MovieDomain> movies = dto.stream().map(MovieDomain::new).toList();
        List<MovieDomain> moviesValid = movies.stream().filter(movieDomain -> movieDomain.isValid).toList();
        List<MovieDomain> moviesInvalid = movies.stream().filter(movieDomain -> !movieDomain.isValid).toList();
        if (!moviesInvalid.isEmpty()) {
            moviesInvalid.forEach(movieDomain ->
                    log.error("Movie in line {} is invalid. Errors: {}", movieDomain.getLine(), movieDomain.getErrors()));
        }
        return moviesValid;
    }

    public static List<MovieDomain> filterRemoveMoreThanOneWinnerPerYear(List<MovieDomain> all) {
        Map<Integer, Long> winnersPerYear = all.stream()
                .filter(MovieDomain::isWinner)
                .collect(Collectors.groupingBy(MovieDomain::getYear, Collectors.counting()));

        return all.stream()
                .filter(movie -> winnersPerYear.getOrDefault(movie.getYear(), 0L) <= 1)
                .toList();
    }

    @Override
    public void validate() {
        new MovieValidator().validate(this);
    }

    public void addValidation(MovieValidator validator) {
        this.isValid = validator.isValid();
        this.errors = validator.getErrors();
    }

    public static Map<String, List<MovieDomain>> getMoviesWinner(List<MovieDomain> movieDomains) {
        Map<String, List<MovieDomain>> mappingProducersWinners = getMappingProducersWinners(movieDomains);
        eliminateProducersWithLessThanTwoAwards(mappingProducersWinners);
        if (mappingProducersWinners.isEmpty()) {
            throw new NoProducerHasMoreThanTwoTitlesException();
        }
        return mappingProducersWinners;
    }

    private static void eliminateProducersWithLessThanTwoAwards(Map<String, List<MovieDomain>> mappingProducersWinners) {
        mappingProducersWinners.entrySet().removeIf(entry -> entry.getValue().size() < 2);
    }

    private static Map<String, List<MovieDomain>> getMappingProducersWinners(List<MovieDomain> movieDomains) {
        return movieDomains.stream()
                .filter(MovieDomain::isWinner)
                .flatMap(movie -> movie.getProducers().stream()
                        .map(producer -> Map.entry(producer, movie)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }
}
