package br.com.outsera.worse_movie.application.usecase;

import br.com.outsera.worse_movie.application.UseCaseIn;
import br.com.outsera.worse_movie.domain.movie.MovieDomain;
import br.com.outsera.worse_movie.domain.movie.MovieGateway;
import br.com.outsera.worse_movie.infrastructure.file.dtos.MovieDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveWorseMovieUseCase implements UseCaseIn<List<MovieDto>> {

    private final MovieGateway movieGateway;

    public SaveWorseMovieUseCase(MovieGateway movieGateway) {
        this.movieGateway = movieGateway;
    }

    @Override
    public void execute(List<MovieDto> request) {
        List<MovieDomain> movieDomains = MovieDomain.fromDomainDtoList(request);
        movieDomains.forEach(movieGateway::save);
    }
}
