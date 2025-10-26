package br.com.outsera.worse_movie.utils.mappers;

import br.com.outsera.worse_movie.domain.producer.ProducerDomain;
import br.com.outsera.worse_movie.infrastructure.file.dtos.ProducerDto;
import br.com.outsera.worse_movie.infrastructure.persistence.entities.ProducerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProducerMapper {
    ProducerDto toDto(ProducerEntity entity);
    ProducerDomain toDomain(ProducerDto entity);
    List<ProducerDomain> toDomain(List<ProducerDto> entities);
    ProducerEntity toEntity(ProducerDomain domain);
    List<ProducerEntity> toEntity(List<ProducerDomain> entities);
}
