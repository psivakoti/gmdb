package com.galvanize.gmdb.controllers;

import com.galvanize.gmdb.model.MovieDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @GetMapping("/movies")
    public List<MovieDto> findAllMovies() {
        return new ArrayList<>();
    }
}
