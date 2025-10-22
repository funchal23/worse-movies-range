package br.com.outsera.worse_movie.infrastructure.file;

import br.com.outsera.worse_movie.application.UseCaseIn;
import br.com.outsera.worse_movie.infrastructure.file.dtos.MovieDto;
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

@Component
public class CsvLoader {

    private final UseCaseIn<List<MovieDto>> useCaseIn;

    public CsvLoader(UseCaseIn<List<MovieDto>> useCaseIn) {
        this.useCaseIn = useCaseIn;
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
            List<MovieDto> movies = getMovies(rows);
            useCaseIn.execute(movies);
        }

    }

    private static List<MovieDto> getMovies(List<String[]> rows) {
        List<MovieDto> movies = new ArrayList<>();
        for (int line = 1; line < rows.size(); line++) {
            String[] columns = rows.get(line);
            Integer year = Integer.parseInt(columns[0].trim());
            String title = columns[1].trim();
            String studios = columns[2].trim();
            String producers = columns[3].trim();
            boolean winner = columns.length > 4 && columns[4].trim().equalsIgnoreCase("yes");
            MovieDto movie = new MovieDto(year, title, getListFromListString(studios), getListFromListString(producers), winner, line);
            movies.add(movie);
        }
        return movies;
    }

    private static List<String> getListFromListString(String list) {
        if (list == null || list.isBlank()) {
            return List.of();
        }

        String normalized = list.replace(" and ", ",");
        String[] parts = normalized.split(",");
        List<String> producerList = new ArrayList<>();
        for (String part : parts) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty()) {
                producerList.add(trimmed);
            }
        }
        return producerList;
    }
}
