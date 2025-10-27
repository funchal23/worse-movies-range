package br.com.outsera.worse_movie.infrastructure.persistence.mappers;

import br.com.outsera.worse_movie.domain.producer.Producer;
import br.com.outsera.worse_movie.domain.producer.ProducerId;
import br.com.outsera.worse_movie.domain.shared.valueobject.ProducerName;
import br.com.outsera.worse_movie.infrastructure.persistence.entities.ProducerEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProducerMapper {

    public Producer toDomain(ProducerEntity entity) {
        return Producer.create(
                ProducerId.from(entity.getId()),
                ProducerName.from(entity.getName()));
    }

    public List<Producer> toDomainList(List<ProducerEntity> domains) {
        return domains.stream().map(this::toDomain).toList();
    }

    public ProducerEntity toEntity(Producer domain) {
        return ProducerEntity.create(
                domain.getId().value(),
                domain.getName().value());
    }

    public List<ProducerEntity> toEntityList(List<Producer> domains) {
        return domains.stream().map(this::toEntity).toList();
    }

}
