package com.asc.ts.gra.api.controller;

import com.asc.ts.gra.model.controller.AbstractMovieController;
import com.asc.ts.gra.model.entity.Movie;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 */

@RestController
@RequestMapping(value = "/movies")
public class MovieController extends AbstractMovieController {
    @PostMapping
    @Override
    public ResponseEntity<List<Movie>> create(@RequestBody List<Movie> obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }
    
    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<Movie> getId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getId(id));        
    }
    
    @GetMapping
    @Override
    public ResponseEntity<List<Movie>> getAll() {
        return ResponseEntity.ok().body(service.getAll());        
    }
    
    @PutMapping(value = "/{id}")
    @Override
    public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody Movie obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(service.update(obj)); 
    }
}
