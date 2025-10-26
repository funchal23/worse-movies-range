package br.com.outsera.worse_movie.infrastructure.file.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProducerDto {
    private Long id;
    private String name;
}
