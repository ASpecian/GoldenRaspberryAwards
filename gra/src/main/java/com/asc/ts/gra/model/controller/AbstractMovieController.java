package com.asc.ts.gra.model.controller;

import com.asc.ts.gra.model.entity.Movie;
import com.asc.ts.gra.model.entity.container.MovieAwardsIntervalContainer;
import com.asc.ts.gra.model.service.AbstractMovieService;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 */

public abstract class AbstractMovieController extends AbstractController<Movie, AbstractMovieService> {
    public abstract ResponseEntity<MovieAwardsIntervalContainer> getAwardsIntervals();
}
