package br.com.outsera.worse_movie.application.usecase;

import br.com.outsera.worse_movie.application.UseCaseOut;
import br.com.outsera.worse_movie.domain.movie.MovieDomain;
import br.com.outsera.worse_movie.domain.movie.MovieGateway;
import br.com.outsera.worse_movie.domain.range.RangeDomain;
import br.com.outsera.worse_movie.exception.MoviesNotFoundException;
import br.com.outsera.worse_movie.infrastructure.rest.response.AwardRangeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetAwardRangeUseCaseOut implements UseCaseOut<AwardRangeResponse> {

    private final MovieGateway gateway;

    public GetAwardRangeUseCaseOut(MovieGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public AwardRangeResponse execute() {
        List<MovieDomain> all = gateway.findByWinners();
        if(all.isEmpty()){
            throw new MoviesNotFoundException();
        }
        Map<String, List<MovieDomain>> moviesWinner = MovieDomain.extractMapProducerMovies(all);
        return RangeDomain.toResponse(moviesWinner);
    }
}
