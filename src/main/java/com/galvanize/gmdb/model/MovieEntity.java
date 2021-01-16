package com.galvanize.gmdb.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class MovieEntity {
    @Id
    private String title;
    private String director;
    private String actors;
    private String release;
    private String description;
    private Integer rating;


}
