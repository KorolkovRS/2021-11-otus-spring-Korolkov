package ru.korolkovrs.spring23.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.korolkovrs.spring23.domain.Genre;
import ru.korolkovrs.spring23.rest.dto.GenreDto;
import ru.korolkovrs.spring23.rest.dto.converter.GenreDtoConverter;
import ru.korolkovrs.spring23.service.GenreService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private GenreService genreService;

    @MockBean
    private GenreDtoConverter genreDtoConverter;

    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void shouldGetAllGenresOnUser() throws Exception {
        Genre existGenre1 = new Genre();
        Genre existGenre2 = new Genre();
        GenreDto existGenreDto1 = new GenreDto();
        GenreDto existGenreDto2 = new GenreDto();
        List<Genre> existGenres = List.of(existGenre1, existGenre2);
        List<GenreDto> expectedResponse = List.of(existGenreDto1, existGenreDto2);

        given(genreService.findAll()).willReturn(existGenres);
        given(genreDtoConverter.toDto(existGenre1)).willReturn(existGenreDto1);
        given(genreDtoConverter.toDto(existGenre2)).willReturn(existGenreDto2);

        mockMvc.perform(get("/api/v1/genres"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));
    }
}