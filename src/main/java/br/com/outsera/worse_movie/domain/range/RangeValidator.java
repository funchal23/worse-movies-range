package br.com.outsera.worse_movie.domain.range;

import br.com.outsera.worse_movie.exception.IllegalPropertiesRangeException;

public class RangeValidator {

    public void validate(RangeDomain range) {
        if (range.getProducer() == null || range.getProducer().trim().isEmpty()) {
            throw new IllegalPropertiesRangeException("Producer cannot be null or empty.");
        }
        if (range.getInterval() < 0) {
            throw new IllegalPropertiesRangeException("Interval must be a non-negative integer.");
        }
    }
}
