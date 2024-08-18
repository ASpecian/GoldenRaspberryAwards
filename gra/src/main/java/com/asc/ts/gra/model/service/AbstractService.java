package com.asc.ts.gra.model.service;

import com.asc.ts.gra.model.entity.AbstractEntity;
import com.asc.ts.gra.model.repository.IRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 *
 * @param <E> Entity Class
 * @param <R> Repository Class
 */
public abstract class AbstractService<E extends AbstractEntity, R extends IRepository> {
    @Autowired
    protected R repository;

    protected void setRepository(R repository) {
        this.repository = repository;
    }
    
    public List<E> create(List<E> obj) {
        if (obj == null || obj.isEmpty()) {
            return new ArrayList<>();
        }
        
        for(E o : obj) {
            repository.save(o);
        }
        
        return obj;
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    public E getId(Long id) {
        Optional<E> obj = repository.findById(id);
        return obj.get();
    }
    
    public List<E> getAll() {
        return repository.findAll();
    }
    
    public E update(E obj) {
       E newObj = (E) repository.findById(obj.getId()).get();
       
       newObj.update(obj);
       return (E) repository.save(newObj);
    }
}
