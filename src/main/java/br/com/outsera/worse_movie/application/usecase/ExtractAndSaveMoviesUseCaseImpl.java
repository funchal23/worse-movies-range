package br.com.outsera.worse_movie.application.usecase;

import br.com.outsera.worse_movie.application.port.ExtractAndSaveMoviesUseCase;
import br.com.outsera.worse_movie.domain.movie.Movie;
import br.com.outsera.worse_movie.domain.movie.MovieGateway;
import br.com.outsera.worse_movie.domain.movie.MovieId;
import br.com.outsera.worse_movie.domain.producer.Producer;
import br.com.outsera.worse_movie.domain.shared.valueobject.Studio;
import br.com.outsera.worse_movie.domain.shared.valueobject.Title;
import br.com.outsera.worse_movie.domain.shared.valueobject.Year;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ExtractAndSaveMoviesUseCaseImpl implements ExtractAndSaveMoviesUseCase {

    private final GetOrSaveProducerUseCaseImpl getOrSaveProducerUseCase;
    private final MovieGateway gateway;

    public ExtractAndSaveMoviesUseCaseImpl(GetOrSaveProducerUseCaseImpl getOrSaveProducerUseCase, MovieGateway gateway) {
        this.getOrSaveProducerUseCase = getOrSaveProducerUseCase;
        this.gateway = gateway;
    }

    @Override
    public void execute(List<String[]> rows) {
        List<Movie> movies = IntStream.range(1, rows.size())
                .mapToObj(line -> {
                    String[] columns = rows.get(line);
                    int year = Integer.parseInt(columns[0].trim());
                    String title = columns[1].trim();
                    String studios = columns[2].trim();
                    String producers = columns[3].trim();
                    boolean winner = columns.length > 4 && columns[4].trim().equalsIgnoreCase("yes");
                    return Movie.create(
                            MovieId.generate(),
                            Title.from(title),
                            Year.from(year),
                            Studio.from(studios),
                            saveProducers(producers),
                            winner
                    );
                })
                .toList();
        gateway.save(movies);
    }

    private List<Producer> saveProducers(String value) {
        if (value == null || value.isBlank()) {
            return List.of();
        }
        String normalized = value.replace(" and ", ",");
        String[] parts = normalized.split(",");
        List<Producer> producerList = new ArrayList<>();
        for (String part : parts) {
            String producer = part.trim();
            if (!producer.isEmpty()) {
                producerList.add(getOrSaveProducerUseCase.execute(producer));
            }
        }
        return producerList;
    }
}
