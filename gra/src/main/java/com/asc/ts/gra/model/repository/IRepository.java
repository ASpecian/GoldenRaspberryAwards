package com.asc.ts.gra.model.repository;

import com.asc.ts.gra.model.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 * 
 * @param <E> Entity Class
 */

public interface IRepository <E extends AbstractEntity> extends JpaRepository<E, Long>{

}
