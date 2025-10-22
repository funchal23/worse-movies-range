package br.com.outsera.worse_movie.infrastructure.rest.response;

import br.com.outsera.worse_movie.domain.range.RangeDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardRangeDetailResponse {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;


    private AwardRangeDetailResponse(RangeDomain diff){
        this.producer = diff.getProducer();
        this.interval = diff.getInterval();
        this.previousWin = diff.getPreviousWin();
        this.followingWin = diff.getFollowingWin();
    }

    public static List<AwardRangeDetailResponse> toResponse(List<RangeDomain> rangeDomains) {
        return rangeDomains.stream()
                .map(AwardRangeDetailResponse::new)
                .toList();
    }
}
