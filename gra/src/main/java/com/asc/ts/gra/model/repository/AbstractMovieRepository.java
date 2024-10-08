package com.asc.ts.gra.model.repository;

import com.asc.ts.gra.model.entity.Movie;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 */

@Repository
public interface AbstractMovieRepository extends IRepository<Movie> {
    public Movie findByTitle(String title);
    public List<Movie> deleteByTitle(String title);
}
