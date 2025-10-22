package br.com.outsera.worse_movie.infrastructure.persistence.repository;

import br.com.outsera.worse_movie.infrastructure.persistence.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
