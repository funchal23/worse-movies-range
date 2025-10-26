package br.com.outsera.worse_movie.utils.mappers;

import br.com.outsera.worse_movie.domain.movie.MovieDomain;
import br.com.outsera.worse_movie.infrastructure.file.dtos.MovieDto;
import br.com.outsera.worse_movie.infrastructure.persistence.entities.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProducerMapper.class})
public interface MovieMapper {

    @Mapping(target = "valid", ignore = true)
    @Mapping(target = "errors", ignore = true)
    @Mapping(target = "line", ignore = true)
    MovieDomain toDomain(MovieEntity entity);

    @Mapping(target = "id", ignore = true)
    MovieEntity toEntity(MovieDomain domain);

    List<MovieDomain> toDomainListByEntity(List<MovieEntity> entities);
    List<MovieEntity> toEntityList(List<MovieDomain> domains);

    @Mapping(target = "valid", constant = "true")
    @Mapping(target = "errors", expression = "java(new ArrayList<>())")
    MovieDomain toDomain(MovieDto dto);
    List<MovieDomain> toDomainListByDto(List<MovieDto> movies);
}
