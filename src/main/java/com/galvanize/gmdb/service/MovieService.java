package com.galvanize.gmdb.service;


import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.MovieDto;
import com.galvanize.gmdb.model.MovieEntity;
import com.galvanize.gmdb.repository.MovieRepository;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDto> findAllMovies() {

        return movieRepository.findAll()
                .stream()
                .map(movieEntity -> new MovieDto(movieEntity.getTitle(), movieEntity.getDirector(), movieEntity.getActors(), movieEntity.getRelease(), movieEntity.getDescription(),movieEntity.getRating() ))
                .collect(Collectors.toList());
    }

    public void addMovie(MovieDto movieDto) {
        movieRepository.save(new MovieEntity(movieDto.getTitle(),movieDto.getDirector(),movieDto.getActors(),movieDto.getRelease(),movieDto.getDescription(),movieDto.getRating()));
    }

    public MovieDto findAMovie(String title) throws MovieNotFoundException {
        Optional<MovieEntity> movieEntity = movieRepository.findById(title);
        Optional<MovieDto> movieDto = movieEntity.map(movie ->new MovieDto(movie.getTitle(), movie.getDirector(), movie.getActors(), movie.getRelease(), movie.getDescription(),movie.getRating()));
        if(movieDto.isPresent()){
            return movieDto.get();
        }
        else{
           throw new MovieNotFoundException();
        }

    }
}
