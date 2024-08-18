package com.asc.ts.gra.model.controller;

import com.asc.ts.gra.model.entity.AbstractEntity;
import com.asc.ts.gra.model.service.AbstractService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 * 
 * @param <E> Entity Class
 * @param <S> Service Class
 */
public abstract class AbstractController<E extends AbstractEntity, S extends AbstractService> {
    @Autowired
    protected S service;
        
    public abstract ResponseEntity<List<E>> create(@RequestBody List<E> obj);    
    public abstract ResponseEntity<Void> delete(@PathVariable Long id);    
    public abstract ResponseEntity<E> getId(@PathVariable Long id);    
    public abstract ResponseEntity<List<E>> getAll();    
    public abstract ResponseEntity<E> update(@PathVariable Long id, @RequestBody E obj);
}
