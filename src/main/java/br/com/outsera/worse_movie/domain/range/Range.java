package br.com.outsera.worse_movie.domain.range;

import lombok.Data;

@Data
public class Range {
    private String producerName;
    private int previousWin;
    private int followingWin;
    private int interval;

    public Range(String value, int interval, int previousWin, int followingWin) {
        this.producerName = value;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }
}