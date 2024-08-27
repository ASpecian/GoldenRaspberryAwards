package com.asc.ts.gra;

import com.asc.ts.gra.model.entity.Movie;
import com.asc.ts.gra.model.repository.AbstractMovieRepository;
import com.asc.ts.gra.utils.CsvReader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 */

@Component
//public class ApplicationRunner {
public class ApplicationRunner implements CommandLineRunner {
    @Autowired
    private AbstractMovieRepository movieRepository;
    
    private static String path = "files";
    private static String file = "movies";
    
    @Override
    public void run(String... args) throws Exception {
        if (!readParams(args)) {
            return;
        }
        
        List<Movie> registers;
                
        try {
            registers = CsvReader.load(path, file, Movie.class);
        } catch (Exception e) {
            throw new Exception(String.format("> Erro ao carregar o arquivo!\n  > Erro: %s", e.getMessage()));
        }
        
        movieRepository.saveAll(registers);
    }
    
    private Boolean readParams(String[] args) throws Exception {
        for (String arg : args) {
            String[] _arg = arg.split("=");
            
            String nameParam = _arg[0].toLowerCase();
            String valueParam = (_arg.length > 1 ? _arg[1] : null);
            
            if (valueParam == null) {
                throw new Exception(String.format("> Valor do parametro não encontrado: %s", arg));
            }
            
            switch (nameParam) {
                case "path" -> path = valueParam;
                case "file" -> file = valueParam;
                default -> {
                    throw new Exception(String.format("> Nome do parametro nao aceito [path | file]: %s", arg));
                }
                    
            }
        }
        
        return true;
    }
}
