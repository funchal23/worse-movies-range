package br.com.outsera.worse_movie.domain.range;

import br.com.outsera.worse_movie.domain.movie.Movie;
import br.com.outsera.worse_movie.domain.shared.valueobject.ProducerName;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RangeRules {
    public static RangeResult toRangesResult(Map<ProducerName, List<Movie>> movieWinners) {
        List<Range> ranges = movieWinners.entrySet().stream()
                .flatMap(entry -> {
                    ProducerName producer = entry.getKey();
                    List<Integer> years = entry.getValue().stream()
                            .map(Movie::getValueYear)
                            .sorted()
                            .toList();

                    return IntStream.range(0, years.size() - 1)
                            .mapToObj(i -> {
                                int diff = years.get(i + 1) - years.get(i);
                                int previous = years.get(i);
                                int next = years.get(i + 1);
                                return new Range(producer.value(), diff, previous, next);
                            });
                })
                .toList();
        return findMinAndMaxIntervals(ranges);
    }

    private static RangeResult findMinAndMaxIntervals(List<Range> ranges) {
        IntSummaryStatistics statistics = ranges.stream().collect(Collectors.summarizingInt(Range::getInterval));
        int min = statistics.getMin();
        int max = statistics.getMax();
        List<Range> minRanges = ranges.stream()
                .filter(range -> range.getInterval() == min)
                .toList();
        List<Range> maxRanges = ranges.stream()
                .filter(range -> range.getInterval() == max)
                .toList();
        return new RangeResult(minRanges, maxRanges);
    }
}