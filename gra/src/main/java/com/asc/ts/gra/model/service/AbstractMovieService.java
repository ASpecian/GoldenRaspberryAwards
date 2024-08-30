package com.asc.ts.gra.model.service;

import com.asc.ts.gra.model.entity.Movie;
import com.asc.ts.gra.model.entity.container.IntervalContainer;
import com.asc.ts.gra.model.entity.container.MovieAwardsIntervalContainer;
import com.asc.ts.gra.model.repository.AbstractMovieRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 */
public abstract class AbstractMovieService extends AbstractService<Movie, AbstractMovieRepository> {
    public Movie findByTitle(String title) {
        return repository.findByTitle(title);
    }
    
    public List<Movie> deleteByTitle(String title) {
        return repository.deleteByTitle(title);
    }
    
    public MovieAwardsIntervalContainer getAwardsIntervals(Boolean onlyMinAndMax, String nameProducer) {
        MovieAwardsIntervalContainer intervals = new MovieAwardsIntervalContainer();
        
        Boolean isOnlyMinAndMax = ((nameProducer != null && !nameProducer.isEmpty()) || onlyMinAndMax == null) ? false : onlyMinAndMax;
        
        intervals.setMinIntervals(findAwardIntervals(true, isOnlyMinAndMax, nameProducer));
        intervals.setMaxIntervals(findAwardIntervals(false, isOnlyMinAndMax, nameProducer));
        
        return intervals;
    }
    
    private List<IntervalContainer> findAwardIntervals(boolean isMinInterval, boolean onlyMinAndMax, String nameProducer) {
        StringBuilder sb = new StringBuilder("WITH WINNER_MOVIES AS (\n")
                .append("  SELECT P.PRODUCER,\n")
                .append("         M.RELEASE_YEAR AS PREVIOUSWIN,\n")
                .append("         LEAD(M.RELEASE_YEAR) OVER (PARTITION BY P.PRODUCER ORDER BY M.RELEASE_YEAR) FOLLOWINGWIN,\n")
                .append("         LEAD(M.RELEASE_YEAR) OVER (PARTITION BY P.PRODUCER ORDER BY M.RELEASE_YEAR) - M.RELEASE_YEAR AS DIFF,\n")
                .append("         ROW_NUMBER() OVER (PARTITION BY P.PRODUCER ORDER BY M.RELEASE_YEAR) AS RN\n")
                .append("  FROM PRODUCER P\n")
                .append("       INNER JOIN MOVIE M ON (P.IDMOVIE = M.ID)\n")
                .append("  ORDER BY 1\n")
                .append("),\n\n")
                .append("INTERVALS AS (\n")
                .append(String.format("  SELECT %s(DIFF) AS DIFF%s\n", (isMinInterval ? "MIN" : "MAX"), (onlyMinAndMax ? "" : ",")))
                .append(String.format("%s", (onlyMinAndMax ? "" : "         PRODUCER\n")))
                .append("  FROM  WINNER_MOVIES\n")
                .append("  WHERE DIFF IS NOT NULL\n")
                .append("        AND PREVIOUSWIN <> FOLLOWINGWIN\n")
                .append(String.format("%s", (onlyMinAndMax ? "" : "  GROUP BY PRODUCER")))
                .append(")\n\n")
                .append("SELECT M.PRODUCER,\n")
                .append("       M.DIFF,\n")
                .append("       M.PREVIOUSWIN,\n")
                .append("       M.FOLLOWINGWIN\n")
                .append("FROM INTERVALS I\n")
                .append(String.format("     INNER JOIN WINNER_MOVIES M ON (M.DIFF = I.DIFF%s)", (onlyMinAndMax ? "" : " AND M.PRODUCER = I.PRODUCER\n")))
                .append(String.format("%s", (nameProducer == null || nameProducer.isBlank() ? "" : (String.format("WHERE M.PRODUCER ILIKE '%%%s%%'\n", nameProducer)))));

        System.out.println(String.format("\n\n--------------------------------------------\n> Params: \n> onlyMinAndMax: %s\n> nameProductor: %s\n> Script a executar:\n%s"
                , onlyMinAndMax, nameProducer == null ? "null" : nameProducer, sb.toString()));
        
        return em.createNativeQuery(sb.toString(), IntervalContainer.class).getResultList();
    }
}
