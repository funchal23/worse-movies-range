package br.com.outsera.worse_movie.infrastructure.persistence.entities;


import br.com.outsera.worse_movie.domain.movie.MovieDomain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "movie_year")
    private Integer year;
    private String title;
    private List<String> studios;
    private List<String> producers;
    private Boolean winner;

    public MovieEntity(MovieDomain domain) {
        this.title = domain.getTitle();
        this.year = domain.getYear();
        this.studios = domain.getStudios();
        this.producers = domain.getProducers();
        this.winner = domain.isWinner();
    }

    public static MovieEntity fromEntity(MovieDomain domain) {
        return new MovieEntity(domain);
    }
}
