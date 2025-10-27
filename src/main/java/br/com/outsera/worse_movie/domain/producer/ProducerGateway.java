package br.com.outsera.worse_movie.domain.producer;

import java.util.Optional;

public interface ProducerGateway {
    Producer save(Producer name);
    Optional<Producer> findByName(String name);
}
