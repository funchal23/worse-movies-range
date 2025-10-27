package br.com.outsera.worse_movie.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducerEntity {
    @Id
    private String id;
    private String name;

    public static ProducerEntity create(String id, String name){
        return new ProducerEntity(id, name);
    }
}
