package br.com.outsera.worse_movie.domain.range;

import br.com.outsera.worse_movie.domain.Domain;
import br.com.outsera.worse_movie.domain.movie.MovieDomain;
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

    public static List<RangeDomain> getMinRange(Map<String, List<MovieDomain>> movieWinners) {
        List<RangeDomain> ranges = extractRangesByProducer(movieWinners);
        int minDiff = ranges.stream()
                .mapToInt(RangeDomain::getInterval)
                .min()
                .orElse(0);
        return ranges.stream()
                .filter(v -> v.getInterval() == minDiff)
                .toList();
    }

    public static List<RangeDomain> getMaxRange(Map<String, List<MovieDomain>> movieWinners) {
        List<RangeDomain> ranges = extractRangesByProducer(movieWinners);
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
            RangeDomain rangeDomain = new RangeDomain();
            List<Integer> years = value.stream()
                    .map(MovieDomain::getYear)
                    .sorted()
                    .toList();
            for (int i = 0; i < years.size() - 1; i++) {
                int diff = years.get(i + 1) - years.get(i);
                if (diff > rangeDomain.getInterval()) {
                    rangeDomain.setProducer(key);
                    rangeDomain.setInterval(diff);
                    rangeDomain.setPreviousWin(years.get(i));
                    rangeDomain.setFollowingWin(years.get(i + 1));
                }
            }
            rangeDomain.validate();
            awardRangeDomain.add(rangeDomain);
        });
        return awardRangeDomain;
    }

    @Override
    public void validate() {
        new RangeValidator().validate(this);
    }
}
