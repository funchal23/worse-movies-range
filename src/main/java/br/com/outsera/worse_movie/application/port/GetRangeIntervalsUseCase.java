package br.com.outsera.worse_movie.application.port;

import br.com.outsera.worse_movie.domain.range.RangeResult;

public interface GetRangeIntervalsUseCase {
    RangeResult execute();
}
