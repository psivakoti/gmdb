package com.galvanize.gmdb;

import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.MovieDto;
import com.galvanize.gmdb.model.MovieEntity;
import com.galvanize.gmdb.repository.MovieRepository;
import com.galvanize.gmdb.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @InjectMocks
    MovieService movieService;

    @Mock
    MovieRepository movieRepository;

    @Test
    public void testAddMovie(){
        MovieDto movieDto = new MovieDto("The Avengers","Joss Whedon","Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth","2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",null);
        MovieEntity movieEntity = new MovieEntity(movieDto.getTitle(),movieDto.getDirector(),movieDto.getActors(),movieDto.getRelease(),movieDto.getDescription(),movieDto.getRating());
        movieService.addMovie(movieDto);
        verify(movieRepository, times(1)).save(movieEntity);
    }

    @Test
    public void testFindAllMovies(){
        MovieDto movieDto = new MovieDto("The Avengers","Joss Whedon","Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth","2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",null);
        List<MovieDto> expectedMovies = new ArrayList<>();
        expectedMovies.add(movieDto);

        MovieEntity movieEntity = new MovieEntity(movieDto.getTitle(),movieDto.getDirector(),movieDto.getActors(),movieDto.getRelease(),movieDto.getDescription(),movieDto.getRating());
        List<MovieEntity> movieEntitiesList = new ArrayList<>();
        movieEntitiesList.add(movieEntity);

        when(movieRepository.findAll()).thenReturn(movieEntitiesList);
        List<MovieDto> actualMovies = movieService.findAllMovies();
        assertThat(actualMovies).isEqualTo(expectedMovies);
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    public void testFindAMovieFound() throws MovieNotFoundException {
        MovieDto expectedMovie = new MovieDto("The Avengers", "Joss Whedon", "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth", "2012", "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.", null);

        MovieEntity movieEntity = new MovieEntity(expectedMovie.getTitle(), expectedMovie.getDirector(), expectedMovie.getActors(), expectedMovie.getRelease(), expectedMovie.getDescription(), expectedMovie.getRating());
        when(movieRepository.findById("The Avengers")).thenReturn(java.util.Optional.of(movieEntity));
        MovieDto actualMovie = movieService.findAMovie("The Avengers");
        assertThat(expectedMovie).isEqualTo(actualMovie);
        verify(movieRepository, times(1)).findById("The Avengers");
    }

    @Test
    public void testFindAMovieNotFound() {

        MovieNotFoundException exception = assertThrows(MovieNotFoundException.class, () -> {
            movieService.findAMovie("The Avengers"); });

        String expectedMessage = "Movie not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(movieRepository, times(1)).findById("The Avengers");
    }

}
