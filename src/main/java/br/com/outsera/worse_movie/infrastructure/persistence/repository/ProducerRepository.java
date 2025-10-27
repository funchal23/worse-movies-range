package br.com.outsera.worse_movie.infrastructure.persistence.repository;

import br.com.outsera.worse_movie.infrastructure.persistence.entities.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProducerRepository extends JpaRepository<ProducerEntity, String> {
    Optional<ProducerEntity> findByName(String name);
}
