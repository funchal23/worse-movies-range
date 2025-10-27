package br.com.outsera.worse_movie.domain.producer;

import br.com.outsera.worse_movie.domain.shared.valueobject.ProducerName;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Producer {
    private final ProducerId id;
    private final ProducerName name;

    private Producer(ProducerId id, ProducerName name) {
        this.id = id;
        this.name = name;
    }

    public static Producer create(ProducerId id, ProducerName name) {
        return new Producer(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(id, producer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}