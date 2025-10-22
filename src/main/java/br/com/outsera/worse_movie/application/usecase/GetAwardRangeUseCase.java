package br.com.outsera.worse_movie.application.usecase;

import br.com.outsera.worse_movie.application.UseCase;
import br.com.outsera.worse_movie.exception.MoviesNotFoundException;
import br.com.outsera.worse_movie.domain.movie.MovieDomain;
import br.com.outsera.worse_movie.domain.movie.MovieGateway;
import br.com.outsera.worse_movie.domain.range.RangeDomain;
import br.com.outsera.worse_movie.infrastructure.rest.request.AwardRangeRequest;
import br.com.outsera.worse_movie.infrastructure.rest.response.AwardRangeDetailResponse;
import br.com.outsera.worse_movie.infrastructure.rest.response.AwardRangeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetAwardRangeUseCase implements UseCase<AwardRangeResponse, AwardRangeRequest> {

    private final MovieGateway movieGateway;

    public GetAwardRangeUseCase(MovieGateway movieGateway) {
        this.movieGateway = movieGateway;
    }

    @Override
    public AwardRangeResponse execute(AwardRangeRequest request) {
        List<MovieDomain> all = movieGateway.findAll();
        if(all.isEmpty()){
            throw new MoviesNotFoundException();
        }
        if (request.isDisregardMoreThanOneWinnerPerYear()){
            all = MovieDomain.filterMoreThanOneWinnerPerYear(all);
        }
        Map<String, List<MovieDomain>> moviesWinner = MovieDomain.getMoviesWinner(all);
        return AwardRangeResponse.builder()
                .max(AwardRangeDetailResponse
                        .toResponse(RangeDomain.getMaxRange(moviesWinner)))
                .min(AwardRangeDetailResponse
                        .toResponse(RangeDomain.getMinRange(moviesWinner)))
                .build();
    }
}
