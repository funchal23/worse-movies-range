package br.com.outsera.worse_movie.infrastructure.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AwardRangeRequest {
    private boolean disregardMoreThanOneWinnerPerYear;
}
