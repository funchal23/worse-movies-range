package br.com.outsera.worse_movie.domain.movie;

import br.com.outsera.worse_movie.domain.shared.valueobject.ProducerName;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieRules {
    public static Map<ProducerName, List<Movie>> extractMapProducerMovies(List<Movie> movies) {
        return movies.stream()
                .flatMap(movie ->
                        movie.getProducers()
                                .stream()
                                .map(producer -> Map.entry(producer.getName(), movie)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }
}
