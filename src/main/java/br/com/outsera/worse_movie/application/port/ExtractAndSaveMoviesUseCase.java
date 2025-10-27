package br.com.outsera.worse_movie.application.port;

import java.util.List;

public interface ExtractAndSaveMoviesUseCase {
    void execute(List<String[]> rows);
}
