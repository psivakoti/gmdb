package com.galvanize.gmdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.model.MovieDto;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MovieControllerITTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void testFindAllMovies_EmptyList() throws Exception {
                mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void testFindAllMovies_WithData() throws Exception {

        MovieDto movieDto = new MovieDto("The Avengers","Joss Whedon","Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth","2012","Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",null);
        mockMvc.perform(post("/movies")
                .content(objectMapper.writeValueAsString(movieDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("The Avengers"))
                .andExpect(jsonPath("$[0].director").value("Joss Whedon"))
                .andExpect(jsonPath("$[0].actors").value("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth"))
                .andExpect(jsonPath("$[0].release").value("2012"))
                .andExpect(jsonPath("$[0].description").value("Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity."))
                .andExpect(jsonPath("$[0].rating").value(IsNull.nullValue()));

    }

}
