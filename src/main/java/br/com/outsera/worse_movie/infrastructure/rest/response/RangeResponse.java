package br.com.outsera.worse_movie.infrastructure.rest.response;

import br.com.outsera.worse_movie.domain.range.RangeResult;
import lombok.Builder;

import java.util.List;

@Builder
public record RangeResponse(
        List<RangeDetailResponse> min,
        List<RangeDetailResponse> max
) {
    public static RangeResponse toResponse(RangeResult result) {
        return new RangeResponse(
                result.minIntervals().stream().map(RangeDetailResponse::create).toList(),
                result.maxIntervals().stream().map(RangeDetailResponse::create).toList()
        );
    }
}