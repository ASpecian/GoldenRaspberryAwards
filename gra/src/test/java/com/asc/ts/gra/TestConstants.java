/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.asc.ts.gra;

import com.asc.ts.gra.model.entity.Movie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author andre.cardoso
 * @data 30.08.2024
 */
public abstract class TestConstants {
    public static final List<Movie> MOVIES = new ArrayList<>(
                Arrays.asList(
                        Movie.builder()
                                .title("Django Livre")
                                .releaseYear(2010)
                                .producers(Arrays.asList(new String[]{"Reginald Hudlin", "Pilar Savone", "Stacey Sher"}))
                                .studios(Arrays.asList(new String[]{}))
                                .winner(true)
                                .build()
                        
                        , Movie.builder()
                                .title("O Príncipe das Mulheres")
                                .releaseYear(1992)
                                .producers(Arrays.asList(new String[]{"Warrington Hudlin", "Brian Grazer"}))
                                .studios(Arrays.asList(new String[]{}))
                                .winner(false)
                                .build()
                        
                        , Movie.builder()
                                .title("Herói da Liberdade")
                                .releaseYear(2020)
                                .producers(Arrays.asList(new String[]{"Reginald Hudlin", "Mark Amin", "Cami Winikoff"}))
                                .studios(Arrays.asList(new String[]{}))
                                .winner(true)
                                .build()
                        
                        , Movie.builder()
                                .title("Marshall")
                                .releaseYear(2017)
                                .producers(Arrays.asList(new String[]{"Reginald Hudlin", "Jonathan Sanger", "Paula Wagner", "Lauren Frost"}))
                                .studios(Arrays.asList(new String[]{}))
                                .winner(true)
                                .build()
                )
            );
}
