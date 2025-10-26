package br.com.outsera.worse_movie.domain.producer;

import br.com.outsera.worse_movie.infrastructure.file.dtos.ProducerDto;

import java.util.Optional;

public interface ProducerGateway {
    ProducerDto save(String name);
    Optional<ProducerDto> findByName(String name);
}
