package ru.korolkovrs.spring16.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.korolkovrs.spring16.domain.Comment;
import ru.korolkovrs.spring16.dto.CommentDto;
import ru.korolkovrs.spring16.dto.converter.CommentDtoConverter;
import ru.korolkovrs.spring16.exception.NotFoundException;
import ru.korolkovrs.spring16.service.CommentService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final CommentDtoConverter commentDtoConverter;

    @PostMapping("/comment/add")
    public String addOrUpdateComment(CommentDto commentDto) {
        Comment comment = commentDtoConverter.toDomainObject(commentDto);
        commentService.save(comment);
        return "redirect:/book?id=" + comment.getBook().getId();
    }

    @PostMapping("/comment/edit")
    public String showEditCommentForm(Long id, Model model) {
        Comment comment = commentService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("comment", comment);
        return "edit_comment";
    }

    @DeleteMapping("/comment")
    public String removeComment(Long id) {
        Long bookId = commentService.findById(id).orElseThrow(NotFoundException::new).getBook().getId();
        commentService.deleteById(id);
        return "redirect:/book?id=" + bookId;
    }
}
