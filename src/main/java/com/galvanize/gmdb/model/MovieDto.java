package com.galvanize.gmdb.model;


import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class MovieDto {

    String title;
    String director;
    String actors;
    String release;
    String description;
    Integer rating;


}
