package ru.korolkovrs.spring23.rest.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.korolkovrs.spring23.domain.Book;
import ru.korolkovrs.spring23.domain.Comment;
import ru.korolkovrs.spring23.rest.dto.RequestCommentDto;
import ru.korolkovrs.spring23.rest.dto.ResponseCommentDto;
import ru.korolkovrs.spring23.rest.dto.converter.CommentDtoConverter;
import ru.korolkovrs.spring23.service.impl.CommentServiceImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CommentServiceImpl commentService;

    @MockBean
    private CommentDtoConverter commentDtoConverter;

    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    public void shouldCorrectSaveNewComment() throws Exception {
        RequestCommentDto requestCommentDto = new RequestCommentDto(null, "comment", 1L);
        Comment saveRequestComment = new Comment(null, "comment", null, null, new Book());
        Comment addedComment = new Comment(1L, "comment", new Date(), new Date(), new Book());
        ResponseCommentDto responseCommentDto = new ResponseCommentDto(1L, "comment", new Date(), new Date());

        given(commentDtoConverter.toDomainObject(requestCommentDto)).willReturn(saveRequestComment);
        given(commentService.save(saveRequestComment)).willReturn(addedComment);
        given(commentDtoConverter.toResponseCommentDto(addedComment)).willReturn(responseCommentDto);

        mockMvc.perform(post("/api/v1/comments").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestCommentDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(responseCommentDto)));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void shouldCorrectUpdateComment() throws Exception {
        RequestCommentDto requestCommentDto = new RequestCommentDto(1L, "comment", 1L);
        Comment saveRequestComment = new Comment(1L, "comment", null, null, new Book());
        Comment addedComment = new Comment(1L, "comment", new Date(), new Date(), new Book());
        ResponseCommentDto responseCommentDto = new ResponseCommentDto(1L, "comment", new Date(), new Date());

        given(commentDtoConverter.toDomainObject(requestCommentDto)).willReturn(saveRequestComment);
        given(commentService.save(saveRequestComment)).willReturn(addedComment);
        given(commentDtoConverter.toResponseCommentDto(addedComment)).willReturn(responseCommentDto);

        mockMvc.perform(put("/api/v1/comments").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestCommentDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(responseCommentDto)));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void shouldDeleteCommentById() throws Exception {
        mockMvc.perform(delete("/api/v1/comments/1"))
                .andExpect(status().isOk());

        verify(commentService, times(1)).deleteById(1L);
    }

    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void shouldNotSaveExistingComment() throws Exception {
        RequestCommentDto requestCommentDto = new RequestCommentDto(1L, "comment", 1L);

        mockMvc.perform(post("/api/v1/comments").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestCommentDto)));
        verify(commentService, times(0)).save(any());
    }

    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void shouldValidateSaveCommentRequest() {
        RequestCommentDto requestWithBlankText = new RequestCommentDto(1L, "", 1L);
        RequestCommentDto requestWithEmptyText = new RequestCommentDto(1L, null, 1L);
        RequestCommentDto requestWithEmptyBookId = new RequestCommentDto(1L, "comment", null);

        assertAll(
                () -> mockMvc.perform(post("/api/v1/comments")
                        .content(mapper.writeValueAsString(requestWithEmptyText)))
                        .andExpect(status().is4xxClientError()),
                () -> mockMvc.perform(post("/api/v1/comments")
                        .content(mapper.writeValueAsString(requestWithBlankText)))
                        .andExpect(status().is4xxClientError()),
                () -> mockMvc.perform(post("/api/v1/comments")
                        .content(mapper.writeValueAsString(requestWithEmptyBookId)))
                        .andExpect(status().is4xxClientError())
        );
    }
}
