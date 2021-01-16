package com.galvanize.gmdb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {
    @Id
    private String title;
    private String director;
    private String actors;
    private String release;
    private String description;
    private Integer rating;


}
