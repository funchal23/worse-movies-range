package br.com.outsera.worse_movie.application.usecase;

import br.com.outsera.worse_movie.application.UseCaseIn;
import br.com.outsera.worse_movie.domain.movie.MovieDomain;
import br.com.outsera.worse_movie.domain.movie.MovieGateway;
import br.com.outsera.worse_movie.infrastructure.file.dtos.MovieDto;
import br.com.outsera.worse_movie.utils.mappers.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveWorseMovieUseCase implements UseCaseIn<List<MovieDto>> {

    private final MovieGateway gateway;
    private final MovieMapper mapper;

    public SaveWorseMovieUseCase(MovieGateway gateway, MovieMapper mapper) {
        this.gateway = gateway;
        this.mapper = mapper;
    }

    @Override
    public void execute(List<MovieDto> request) {
        List<MovieDomain> domains = mapper.toDomainListByDto(request);
        MovieDomain.validate(domains);
        gateway.save(domains);
    }
}
