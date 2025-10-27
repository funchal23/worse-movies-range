package br.com.outsera.worse_movie.application.usecase;

import br.com.outsera.worse_movie.application.port.SaveWorseMoviesUseCase;
import br.com.outsera.worse_movie.domain.movie.Movie;
import br.com.outsera.worse_movie.domain.movie.MovieGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveWorseMoviesUseCaseImpl implements SaveWorseMoviesUseCase {

    private final MovieGateway gateway;

    public SaveWorseMoviesUseCaseImpl(MovieGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void execute(List<Movie> movies) {
        gateway.save(movies);
    }
}
