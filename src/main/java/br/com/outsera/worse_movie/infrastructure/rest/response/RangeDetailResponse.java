package br.com.outsera.worse_movie.infrastructure.rest.response;

import br.com.outsera.worse_movie.domain.range.Range;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RangeDetailResponse {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;
    private RangeDetailResponse(Range range){
        this.producer = range.getProducerName();
        this.interval = range.getInterval();
        this.previousWin = range.getPreviousWin();
        this.followingWin = range.getFollowingWin();
    }
    public static RangeDetailResponse create(Range range){
        return new RangeDetailResponse(range);
    }
}
