package br.com.outsera.worse_movie.domain.movie;

import br.com.outsera.worse_movie.domain.Domain;
import br.com.outsera.worse_movie.domain.producer.ProducerDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class MovieDomain extends Domain {
    private Integer year;
    private String title;
    private String studios;
    private List<ProducerDomain> producers;
    private boolean winner;
    private int line;
    private boolean isValid;
    private List<String> errors;

    public static void validate(List<MovieDomain> movies) {
        movies.removeIf(movie -> {
            movie.validate();
            if (!movie.isValid()) {
                log.error("Movie in line {} is invalid. Errors: {}", movie.getLine(), movie.getErrors());
                return true;
            }
            return false;
        });
    }

    @Override
    public void validate() {
        new MovieValidator().validate(this);
    }

    public void addValidation(MovieValidator validator) {
        this.isValid = validator.isValid();
        this.errors = validator.getErrors();
    }

    public static Map<String, List<MovieDomain>> extractMapProducerMovies(List<MovieDomain> movieDomains) {
        return movieDomains.stream()
                .flatMap(movie -> movie.getProducers().stream()
                        .map(producer -> Map.entry(producer.getName(), movie)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }
}
