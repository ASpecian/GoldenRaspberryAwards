package com.asc.ts.gra.model.repository;

import com.asc.ts.gra.model.entity.Movie;
import com.asc.ts.gra.model.entity.container.IntervalContainer;
import com.asc.ts.gra.model.entity.container.MovieAwardsIntervalContainer;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 */

@Repository
public interface AbstractMovieRepository extends IRepository<Movie> {

    @Query(value = "WITH WINNER_MOVIES AS (                                                 \n" +
                   " SELECT P.PRODUCER,                                                     \n" +
                   "        M.RELEASE_YEAR                                                  \n" +
                   " FROM PRODUCER P                                                        \n" +
                   "      INNER JOIN MOVIE M ON (P.IDMOVIE = M.ID)                          \n" +
                   " --WHERE M.WINNER = TRUE                                                \n" +
                   "),                                                                      \n" +
                   "                                                                        \n" +
                   "YEAR_INTERVAL AS (                                                      \n" +
                   " SELECT W1.PRODUCER,                                                    \n" +
                   "        W1.RELEASE_YEAR AS PREVIOUSWIN,                                 \n" +
                   "        W2.RELEASE_YEAR AS FOLLOWINGWIN,                                \n" +
                   "        ABS(W1.RELEASE_YEAR - W2.RELEASE_YEAR) AS \"INTERVAL\"          \n" +
                   " FROM WINNER_MOVIES W1                                                  \n" +
                   "      INNER JOIN WINNER_MOVIES W2 ON (W1.PRODUCER = W2.PRODUCER)        \n" +
                   " WHERE W1.RELEASE_YEAR < W2.RELEASE_YEAR                                \n" +
                   "),                                                                      \n" +
                   "                                                                        \n" +
                   "MIN_INTERVALS AS (                                                      \n" +
                   "  SELECT DISTINCT ON (PRODUCER)                                         \n" +
                   "         PRODUCER,                                                      \n" +
                   "         PREVIOUSWIN AS PREVIOUSWIN_MIN,                                \n" +
                   "         FOLLOWINGWIN AS FOLLOWINGWIN_MIN,                              \n" +
                   "         MIN(\"INTERVAL\") AS INTERVAL_MIN                              \n" +
                   "  FROM YEAR_INTERVAL                                                    \n" +
                   "  GROUP BY  PRODUCER, PREVIOUSWIN, FOLLOWINGWIN                         \n" +
                   "  ORDER BY PRODUCER, INTERVAL_MIN                                       \n" +
                   "),                                                                      \n" +
                   "                                                                        \n" +
                   "MAX_INTERVALS AS (                                                      \n" +
                   "  SELECT DISTINCT ON (PRODUCER)                                         \n" +
                   "         PRODUCER,                                                      \n" +
                   "         PREVIOUSWIN AS PREVIOUSWIN_MAX,                                \n" +
                   "         FOLLOWINGWIN AS FOLLOWINGWIN_MAX,                              \n" +
                   "         MAX(\"INTERVAL\") AS INTERVAL_MAX                              \n" +
                   "  FROM YEAR_INTERVAL                                                    \n" +
                   "  GROUP BY  PRODUCER, PREVIOUSWIN, FOLLOWINGWIN                         \n" +
                   "  ORDER BY PRODUCER, INTERVAL_MAX DESC                                  \n" +
                   ")                                                                       \n" +
                   "                                                                        \n" +
                   "SELECT MIN.PRODUCER,                                                    \n" +
                   "          MIN.PREVIOUSWIN_MIN,                                          \n" +
                   "          MIN.FOLLOWINGWIN_MIN,                                         \n" +
                   "          MIN.INTERVAL_MIN,                                             \n" +
                   "          MAX.PREVIOUSWIN_MAX,                                          \n" +
                   "          MAX.FOLLOWINGWIN_MAX,                                         \n" +
                   "          MAX.INTERVAL_MAX                                              \n" +
                   "FROM MIN_INTERVALS MIN                                                  \n" +
                   "     INNER JOIN MAX_INTERVALS MAX ON (MIN.PRODUCER = MAX.PRODUCER)        ", nativeQuery = true)
//    @Query(value = "SELECT new com.asc.ts.gra.model.entity.container.IntervalContainer(" +
//           "m1.producers[0], " + // Supondo que há pelo menos um produtor
//           "ABS(m1.releaseYear - m2.releaseYear), " +
//           "m1.releaseYear, " +
//           "m2.releaseYear) " +
//           "FROM Movie m1 " +
//           "JOIN Movie m2 ON m1.producers = m2.producers " +
//           "WHERE m1.winner = true AND m2.winner = true AND m1.id <> m2.id " +
//           "ORDER BY ABS(m1.releaseYear - m2.releaseYear) ASC", nativeQuery = true)
    public List<IntervalContainer> findAwardIntervalsMin();
    
//    @Query("SELECT m1 " +
//           "FROM Movie m1 " +
//           "JOIN Movie m2 ON m1.producers = m2.producers " +
//           "WHERE m1.winner = true AND m2.winner = true AND m1.id <> m2.id " +
//           "ORDER BY ABS(m1.releaseYear - m2.releaseYear) ASC")
//    public MovieAwardsIntervalContainer getAwardsIntervals();
//    
    
//@Query("SELECT new com.asc.ts.gra.model.entity.container.IntervalContainer(" +
//           "m1.producers[0], " + // Supondo que há pelo menos um produtor
//           "ABS(m1.releaseYear - m2.releaseYear), " +
//           "m1.releaseYear, " +
//           "m2.releaseYear) " +
//           "FROM Movie m1 " +
//           "JOIN Movie m2 ON m1.producers = m2.producers " +
//           "WHERE m1.winner = true AND m2.winner = true AND m1.id <> m2.id " +
//           "ORDER BY ABS(m1.releaseYear - m2.releaseYear) ASC")
//    List<IntervalContainer> findAwardIntervalsMin();    

    @Query(value = "SELECT m.* FROM movie m", nativeQuery = true)
    public List<IntervalContainer> findAwardIntervalsMax();
}
