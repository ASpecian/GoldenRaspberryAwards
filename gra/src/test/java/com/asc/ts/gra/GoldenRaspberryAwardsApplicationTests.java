package com.asc.ts.gra;

import static com.asc.ts.gra.TestConstants.MOVIES;
import com.asc.ts.gra.api.service.MovieService;
import com.asc.ts.gra.model.entity.Movie;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class GoldenRaspberryAwardsApplicationTests {
        @Autowired
        private MovieService service;        
    
	@Test
	public void doCheckTotalMovies() {
            List<Movie> returnMovies = service.getAll();
            Assertions.assertEquals(MOVIES.size(), returnMovies.size(), "> Total movies incorrect!");
	}
        
        @Test
	public void doSelectMovieByTitleReturnFound() {
            Movie movieSelected = service.findByTitle(MOVIES.get(0).getTitle());            
            Assertions.assertEquals(MOVIES.get(0).toString(), movieSelected.toString(), "> Movie not found!");
	}
        
        @Test
	public void doSelectMovieByTitleReturnNotFound() {
            Movie movieSelected = service.findByTitle("Matrix");
            Assertions.assertEquals(null, movieSelected, "> Found movie!");
	}

        @Test
	public void doDeleteMovieByTitleReturnDeleted() {
            String title = MOVIES.get(0).getTitle();
            
            service.deleteByTitle(title);
            Movie movieSelected = service.findByTitle(title);
            
            Assertions.assertEquals(null, movieSelected, String.format("> Found movie! Movie %s deleted!", title));
	}
}
