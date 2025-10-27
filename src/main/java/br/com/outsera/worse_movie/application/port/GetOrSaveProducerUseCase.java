package br.com.outsera.worse_movie.application.port;

import br.com.outsera.worse_movie.domain.producer.Producer;

public interface GetOrSaveProducerUseCase {

    Producer execute(String producer);
}
