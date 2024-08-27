package com.asc.ts.gra.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author andre.cardoso
 * @data 17.08.2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "movie")
public class Movie extends AbstractEntity<Movie> {
    @JsonProperty("year")
    @CsvBindByName(column = "year")
    private Integer releaseYear;
    
    @JsonProperty("title")
    @CsvBindByName(column = "title")
    private String title;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "studio", joinColumns = @JoinColumn(name = "idmovie"))
    @Column(name = "studio")
    @JsonProperty("studios")
    @CsvBindAndSplitByName(column = "studios", elementType = String.class, splitOn = ",")
    private List<String> studios;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "producer", joinColumns = @JoinColumn(name = "idmovie"))
    @Column(name = "producer")
    @JsonProperty("producers")
    @CsvBindAndSplitByName(column = "producers", elementType = String.class, splitOn = ",")
    private List<String> producers;
    
    @JsonProperty("winner")
    @CsvBindByName(column = "winner")
    private Boolean winner = false;

    public Movie(String title, Integer releaseYear) {
        this.releaseYear = releaseYear;
        this.title = title;
    }
    
    public void setWinner(Boolean winner) {        
        this.winner = (winner == null ? false : winner);
    }
    
    @Override
    public void update(Movie obj) {
        this.title = obj.getTitle();
        this.releaseYear = obj.getReleaseYear();
        this.winner = obj.getWinner();
        this.producers = obj.getProducers();
        this.studios = obj.getStudios();
    }
}
