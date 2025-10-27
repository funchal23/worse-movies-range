package br.com.outsera.worse_movie.application.usecase;

import br.com.outsera.worse_movie.application.port.GetRangeIntervalsUseCase;
import br.com.outsera.worse_movie.domain.movie.Movie;
import br.com.outsera.worse_movie.domain.movie.MovieGateway;
import br.com.outsera.worse_movie.domain.movie.MovieRules;
import br.com.outsera.worse_movie.domain.range.RangeResult;
import br.com.outsera.worse_movie.domain.range.RangeRules;
import br.com.outsera.worse_movie.domain.shared.exception.MoviesNotFoundException;
import br.com.outsera.worse_movie.domain.shared.valueobject.ProducerName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetRangeIntervalsUseCaseImpl implements GetRangeIntervalsUseCase {

    private final MovieGateway gateway;
    public GetRangeIntervalsUseCaseImpl(MovieGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public RangeResult execute() {
        List<Movie> all = gateway.findByWinners();
        if(all.isEmpty()){
            throw new MoviesNotFoundException();
        }
        Map<ProducerName, List<Movie>> moviesWinner = MovieRules.extractMapProducerMovies(all);
        return RangeRules.toRangesResult(moviesWinner);
    }
}
