package br.com.outsera.worse_movie.infrastructure.file;

import br.com.outsera.worse_movie.application.port.ExtractAndSaveMoviesUseCase;
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
import java.util.List;
import java.util.Objects;

@Component
public class CsvLoader {

    private final ExtractAndSaveMoviesUseCase extractAndSaveMoviesUseCase;

    public CsvLoader(ExtractAndSaveMoviesUseCase extractAndSaveMoviesUseCase) {
        this.extractAndSaveMoviesUseCase = extractAndSaveMoviesUseCase;
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
            extractAndSaveMoviesUseCase.execute(rows);
        }
    }
}
