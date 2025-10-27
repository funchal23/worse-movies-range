package br.com.outsera.worse_movie.infrastructure.persistence.repository;

import br.com.outsera.worse_movie.infrastructure.persistence.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, String> {
    @Query("""
                SELECT DISTINCT m
                FROM MovieEntity m
                JOIN m.producers p
                WHERE m.winner = true
                  AND p IN (
                      SELECT p2
                      FROM MovieEntity m2
                      JOIN m2.producers p2
                      WHERE m2.winner = true
                      GROUP BY p2
                      HAVING COUNT(m2) > 1
                  )
            """)
    List<MovieEntity> findWinningMoviesWithRepeatedProducers();
}
