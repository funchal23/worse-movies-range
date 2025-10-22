package br.com.outsera.worse_movie.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardRangeResponse {
    private List<AwardRangeDetailResponse> min;
    private List<AwardRangeDetailResponse> max;
}
