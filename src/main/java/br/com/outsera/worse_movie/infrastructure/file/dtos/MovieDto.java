package br.com.outsera.worse_movie.infrastructure.file.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MovieDto {
    private Integer year;
    private String title;
    private List<String> studios;
    private List<String> producer;
    private boolean winner;
    private int line;
}
