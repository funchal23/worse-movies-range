package br.com.outsera.worse_movie.infrastructure.file;

import br.com.outsera.worse_movie.application.port.SaveWorseMoviesUseCase;
import br.com.outsera.worse_movie.application.usecase.GetOrSaveProducerUseCaseImpl;
import br.com.outsera.worse_movie.domain.movie.Movie;
import br.com.outsera.worse_movie.domain.movie.MovieId;
import br.com.outsera.worse_movie.domain.producer.Producer;
import br.com.outsera.worse_movie.domain.shared.valueobject.Studio;
import br.com.outsera.worse_movie.domain.shared.valueobject.Title;
import br.com.outsera.worse_movie.domain.shared.valueobject.Year;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Component
public class CsvLoader {

    private final SaveWorseMoviesUseCase saveWorseMoviesUseCase;
    private final GetOrSaveProducerUseCaseImpl getOrSaveProducerUseCase;

    public CsvLoader(SaveWorseMoviesUseCase saveWorseMoviesUseCase, GetOrSaveProducerUseCaseImpl getOrSaveProducerUseCase) {
        this.saveWorseMoviesUseCase = saveWorseMoviesUseCase;
        this.getOrSaveProducerUseCase = getOrSaveProducerUseCase;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadCsv() throws IOException, CsvException {
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .build();

        try (CSVReader reader = new CSVReaderBuilder(
                new InputStreamReader(Objects.requireNonNull(
                        getClass().getResourceAsStream("/files/movies.csv")
                )))
                .withCSVParser(parser)
                .build()) {
            List<String[]> rows = reader.readAll();
            List<Movie> movies = extractMoviesDto(rows);
            saveWorseMoviesUseCase.execute(movies);
        }
    }

    private List<Movie> extractMoviesDto(List<String[]> rows) {
        return IntStream.range(1, rows.size())
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
