package br.com.outsera.worse_movie.domain.range;

import java.util.List;

public record RangeResult(
    List<Range> minIntervals,
    List<Range> maxIntervals
) {
}