package com.galvanize.gmdb.controllers;

import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.MovieDto;
import com.galvanize.gmdb.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovie(@RequestBody MovieDto movieDto){
        movieService.addMovie(movieDto);
    }

    @GetMapping("/movies/{title}")
    public ResponseEntity findAMovie(@PathVariable String title) throws MovieNotFoundException {
        try{
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(movieService.findAMovie(title));
        }catch(MovieNotFoundException movieNotFoundException){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(movieNotFoundException.getMessage());
        }
    }
}
