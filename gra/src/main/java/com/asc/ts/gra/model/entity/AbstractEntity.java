package com.asc.ts.gra.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import lombok.EqualsAndHashCode;

/**
 *
 * @author andre.cardoso
 * @param <E> Entity class
 * @data 17.08.2024
 */

@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class AbstractEntity<E extends AbstractEntity> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    public abstract void update(E obj);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    
}
