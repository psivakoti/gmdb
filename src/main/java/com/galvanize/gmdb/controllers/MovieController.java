package com.galvanize.gmdb.controllers;

import com.galvanize.gmdb.model.MovieDto;
import com.galvanize.gmdb.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    MovieService movieService;
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<MovieDto> findAllMovies() {

        return movieService.findAllMovies();
    }
}
