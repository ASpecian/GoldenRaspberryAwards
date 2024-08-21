package com.asc.ts.gra.model.entity.container;

import com.fasterxml.jackson.annotation.JsonProperty;
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

public class IntervalContainer {
    @JsonProperty("producer")
    private String producer;
    
    @JsonProperty("interval")
    private Integer interval;
    
    @JsonProperty("previousWin")
    private Integer previousWin;
    
    @JsonProperty("followingWin")
    private Integer followingWin;
}
