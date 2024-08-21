package com.asc.ts.gra.model.entity.container;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
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

public class MovieAwardsIntervalContainer extends AbstractContainer {
    @JsonProperty("min")    
    private List<IntervalContainer> minIntervals;
    
    @JsonProperty("max")
    private List<IntervalContainer> maxIntervals;

    public void addMinInterval(IntervalContainer intervalContainer) {
        if (minIntervals == null) {
            minIntervals = new ArrayList<>();
        }
        
        minIntervals.add(intervalContainer);
    }
    
    public void addMaxInterval(IntervalContainer intervalContainer) {
        if (maxIntervals == null) {
            maxIntervals = new ArrayList<>();
        }
        
        maxIntervals.add(intervalContainer);
    }
}
