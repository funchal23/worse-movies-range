package br.com.outsera.worse_movie.domain.producer;

import br.com.outsera.worse_movie.domain.Domain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class ProducerDomain extends Domain {
    private Long id;
    private String name;

    @Override
    public void validate() {
    }
}
