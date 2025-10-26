package br.com.outsera.worse_movie.infrastructure.file;

import br.com.outsera.worse_movie.application.UseCaseIn;
import br.com.outsera.worse_movie.domain.producer.ProducerGateway;
import br.com.outsera.worse_movie.infrastructure.file.dtos.MovieDto;
import br.com.outsera.worse_movie.infrastructure.file.dtos.ProducerDto;
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
import java.util.Optional;
import java.util.stream.IntStream;

@Component
public class CsvLoader {

    private final UseCaseIn<List<MovieDto>> useCaseIn;
    private final ProducerGateway producerGateway;

    public CsvLoader(UseCaseIn<List<MovieDto>> useCaseIn, ProducerGateway producerGateway) {
        this.useCaseIn = useCaseIn;
        this.producerGateway = producerGateway;
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
            List<MovieDto> movies = extractMoviesDto(rows);
            useCaseIn.execute(movies);
        }
    }

    private List<MovieDto> extractMoviesDto(List<String[]> rows) {
        return IntStream.range(1, rows.size())
                .mapToObj(line -> {
                    String[] columns = rows.get(line);
                    Integer year = Integer.parseInt(columns[0].trim());
                    String title = columns[1].trim();
                    String studios = columns[2].trim();
                    String producers = columns[3].trim();
                    boolean winner = columns.length > 4 && columns[4].trim().equalsIgnoreCase("yes");
                    return new MovieDto(year, title, studios, saveProducers(producers), winner, line);
                })
                .toList();
    }

    private List<ProducerDto> saveProducers(String value) {
        if (value == null || value.isBlank()) {
            return List.of();
        }
        String normalized = value.replace(" and ", ",");
        String[] parts = normalized.split(",");
        List<ProducerDto> producerList = new ArrayList<>();
        for (String part : parts) {
            String producer = part.trim();
            if (!producer.isEmpty()) {
                Optional<ProducerDto> producerDto = producerGateway.findByName(producer);
                producerList.add(producerDto.orElseGet(() -> producerGateway.save(producer)));
            }
        }
        return producerList;
    }
}
