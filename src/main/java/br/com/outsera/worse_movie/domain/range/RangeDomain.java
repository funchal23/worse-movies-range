package br.com.outsera.worse_movie.domain.range;

import br.com.outsera.worse_movie.domain.Domain;
import br.com.outsera.worse_movie.domain.movie.MovieDomain;
import br.com.outsera.worse_movie.infrastructure.rest.response.AwardRangeDetailResponse;
import br.com.outsera.worse_movie.infrastructure.rest.response.AwardRangeResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class RangeDomain extends Domain {
    private String producer;
    private int interval = 0;
    private int previousWin = 0;
    private int followingWin = 0;

    public static AwardRangeResponse toResponse(Map<String, List<MovieDomain>> movieWinners) {
        List<RangeDomain> ranges = extractRangesByProducer(movieWinners);
        return AwardRangeResponse.builder()
                .max(AwardRangeDetailResponse.toResponse(RangeDomain.getMaxRange(ranges)))
                .min(AwardRangeDetailResponse.toResponse(RangeDomain.getMinRange(ranges)))
                .build();
    }

    public static List<RangeDomain> getMinRange(List<RangeDomain> ranges) {
        int minDiff = ranges.stream()
                .mapToInt(RangeDomain::getInterval)
                .min()
                .orElse(0);
        return ranges.stream()
                .filter(v -> v.getInterval() == minDiff)
                .toList();
    }

    public static List<RangeDomain> getMaxRange(List<RangeDomain> ranges) {
        int maxDiff = ranges.stream()
                .mapToInt(RangeDomain::getInterval)
                .max()
                .orElse(0);
        return ranges.stream()
                .filter(v -> v.getInterval() == maxDiff)
                .toList();
    }

    private static List<RangeDomain> extractRangesByProducer(Map<String, List<MovieDomain>> movieWinners) {
        List<RangeDomain> awardRangeDomain = new ArrayList<>();
        movieWinners.forEach((key, value) -> {
            List<Integer> years = value.stream()
                    .map(MovieDomain::getYear)
                    .sorted()
                    .toList();
            for (int i = 0; i < years.size() - 1; i++) {
                RangeDomain rangeDomain = new RangeDomain();
                int diff = years.get(i + 1) - years.get(i);
                if (diff >= rangeDomain.getInterval()) {
                    rangeDomain.setProducer(key);
                    rangeDomain.setInterval(diff);
                    rangeDomain.setPreviousWin(years.get(i));
                    rangeDomain.setFollowingWin(years.get(i + 1));
                }
                rangeDomain.validate();
                awardRangeDomain.add(rangeDomain);
            }
        });
        return awardRangeDomain;
    }

    @Override
    public void validate() {
        new RangeValidator().validate(this);
    }
}
