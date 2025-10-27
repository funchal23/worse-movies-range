package br.com.outsera.worse_movie.application.usecase;

import br.com.outsera.worse_movie.application.port.GetOrSaveProducerUseCase;
import br.com.outsera.worse_movie.domain.producer.Producer;
import br.com.outsera.worse_movie.domain.producer.ProducerGateway;
import br.com.outsera.worse_movie.domain.producer.ProducerId;
import br.com.outsera.worse_movie.domain.shared.valueobject.ProducerName;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetOrSaveProducerUseCaseImpl implements GetOrSaveProducerUseCase {

    private final ProducerGateway producerGateway;

    public GetOrSaveProducerUseCaseImpl(ProducerGateway producerGateway) {
        this.producerGateway = producerGateway;
    }

    @Override
    public Producer execute(String producer) {
        Optional<Producer> producerDto = producerGateway.findByName(producer);
        return producerDto.orElseGet(() -> producerGateway.save(Producer.create(
                ProducerId.generate(),
                ProducerName.from(producer)
        )));
    }
}
