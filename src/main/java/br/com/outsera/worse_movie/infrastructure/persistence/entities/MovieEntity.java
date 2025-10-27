package br.com.outsera.worse_movie.infrastructure.persistence.entities;


import br.com.outsera.worse_movie.domain.producer.Producer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    private String id;
    @Column(name = "movie_year")
    private int year;
    private String title;
    private String studios;
    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<ProducerEntity> producers;
    private boolean winner;

    public static MovieEntity create(String id, int year, String title, String studios, List<ProducerEntity> producers, boolean winner){
        return new MovieEntity(id, year, title, studios, producers, winner);
    }
}
