package br.com.outsera.worse_movie.domain.movie;

import br.com.outsera.worse_movie.domain.producer.Producer;
import br.com.outsera.worse_movie.domain.shared.valueobject.Studio;
import br.com.outsera.worse_movie.domain.shared.valueobject.Title;
import br.com.outsera.worse_movie.domain.shared.valueobject.Year;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class Movie {
    private final MovieId id;
    private final Title title;
    private final Year year;
    private final Studio studios;
    private final List<Producer> producers;
    private final boolean winner;

    private Movie(MovieId id, Title title, Year year, Studio studios, List<Producer> producers, boolean winner) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    public static Movie create(MovieId id, Title title, Year year, Studio studio, List<Producer> producers, boolean winner) {
        return new Movie(id, title, year, studio, producers, winner);
    }

    public int getValueYear(){
        return year.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}