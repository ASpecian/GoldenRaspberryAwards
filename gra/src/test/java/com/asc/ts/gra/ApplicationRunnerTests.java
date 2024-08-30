package com.asc.ts.gra;

import com.asc.ts.gra.model.repository.AbstractMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 */

@Profile("test")
@Component
public class ApplicationRunnerTests implements CommandLineRunner {
    @Autowired
    private AbstractMovieRepository movieRepository;
            
    @Override
    public void run(String... args) throws Exception {        
        movieRepository.saveAll(TestConstants.MOVIES);
    }    
}
