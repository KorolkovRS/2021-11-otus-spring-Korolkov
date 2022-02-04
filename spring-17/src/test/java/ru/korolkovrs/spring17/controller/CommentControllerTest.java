package ru.korolkovrs.spring17.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.korolkovrs.spring17.domain.Author;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.domain.Comment;
import ru.korolkovrs.spring17.domain.Genre;
import ru.korolkovrs.spring17.rest.dto.RequestCommentDto;
import ru.korolkovrs.spring17.rest.dto.converter.CommentDtoConverter;
import ru.korolkovrs.spring17.service.impl.CommentServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {
    private Book book;
    private Comment comment;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentServiceImpl commentService;

    @MockBean
    private CommentDtoConverter dtoConverter;

    @PostConstruct
    public void init() {
        book = new Book(1L, "book", new Author(), new Genre(), null);
        comment = new Comment(1L, "comment", null, null, book);
    }

    @Test
    @DisplayName("Должен возвращать форму добавления комментария")
    public void shouldReturnCommentEditForm() throws Exception {
        given(commentService.findById(1L)).willReturn(Optional.of(comment));

        mockMvc.perform(
                post("/comment/edit")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit_comment"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("comment"));
    }

    @Test
    @DisplayName("Должен корректно добавлять/обновлять комментарий")
    public void shouldCorrectAddComment() throws Exception {
        RequestCommentDto dto = new RequestCommentDto(comment.getId(), comment.getText(), comment.getBook().getId());

        given(dtoConverter.toDomainObject(dto)).willReturn(comment);
        given(commentService.findById(1L)).willReturn(Optional.of(comment));

        mockMvc.perform(
                post("/comment/add?")
                        .param("id", String.valueOf(comment.getId()))
                        .param("text", comment.getText())
                        .param("bookId", String.valueOf(comment.getBook().getId())))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/book?id=" + comment.getBook().getId()));

        verify(commentService, times(1)).save(comment);
    }

    @Test
    @DisplayName("Должен удалять комментарий по id")
    public void shouldCorrectRemoveCommentById() throws Exception {
        given(commentService.findById(1L)).willReturn(Optional.of(comment));

        mockMvc.perform(
                delete("/comment").param("id", "1"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/book?id=" + comment.getBook().getId()));

        verify(commentService, times(1)).deleteById(comment.getBook().getId());
    }
}
