package com.asc.ts.gra.model.service;

import com.asc.ts.gra.model.entity.Movie;
import com.asc.ts.gra.model.entity.container.IntervalContainer;
import com.asc.ts.gra.model.entity.container.MovieAwardsIntervalContainer;
import com.asc.ts.gra.model.repository.AbstractMovieRepository;
import java.util.List;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 */
public abstract class AbstractMovieService extends AbstractService<Movie, AbstractMovieRepository> {
    public MovieAwardsIntervalContainer getAwardsIntervals() {
        MovieAwardsIntervalContainer intervals = new MovieAwardsIntervalContainer();
        
        List<IntervalContainer> minIntervals = repository.findAwardIntervalsMin();
        List<IntervalContainer> maxIntervals = repository.findAwardIntervalsMax();
//        
//        for (Object[] result : results) {
//            Movie movie1 = (Movie) result[0];
//            Movie movie2 = (Movie) result[1];
//            Integer interval = (Integer) result[2];
//
//            IntervalContainer intervalContainer = new IntervalContainer();
//            intervalContainer.setProducer(movie1.getProducers().get(0)); // Assumindo que há pelo menos um produtor
//            intervalContainer.setInterval(interval);
//            intervalContainer.setPreviousWin(movie1.getReleaseYear());
//            intervalContainer.setFollowingWin(movie2.getReleaseYear());
//
//            intervals.addMinInterval(intervalContainer);
//        }
        
        return intervals;
    }
}
