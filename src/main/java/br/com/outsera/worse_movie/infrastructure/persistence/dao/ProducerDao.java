package br.com.outsera.worse_movie.infrastructure.persistence.dao;

import br.com.outsera.worse_movie.domain.producer.Producer;
import br.com.outsera.worse_movie.domain.producer.ProducerGateway;
import br.com.outsera.worse_movie.infrastructure.persistence.entities.ProducerEntity;
import br.com.outsera.worse_movie.infrastructure.persistence.repository.ProducerRepository;
import br.com.outsera.worse_movie.infrastructure.persistence.mappers.ProducerMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProducerDao implements ProducerGateway {

    private final ProducerRepository repository;
    private final ProducerMapper mapper;

    public ProducerDao(ProducerRepository repository, ProducerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Producer save(Producer producer) {
        ProducerEntity entity = repository.save(
                ProducerEntity.create(
                        producer.getId().value(),
                        producer.getName().value()
                ));
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Producer> findByName(String name) {
        Optional<ProducerEntity> entity = repository.findByName(name);
        return entity.map(mapper::toDomain);
    }
}
