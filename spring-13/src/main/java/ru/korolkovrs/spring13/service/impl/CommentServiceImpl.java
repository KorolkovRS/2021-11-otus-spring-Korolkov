package ru.korolkovrs.spring13.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring13.repository.CommentRepository;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.domain.Comment;
import ru.korolkovrs.spring13.service.CommentService;
import ru.korolkovrs.spring13.util.CommentDataResolver;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentDataResolver dataResolver;

    @Override
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        comment = dataResolver.addOrUpdateDate(comment);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(String id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Комментраия с id=%d не найдено", id))
        );
        commentRepository.delete(comment);
    }

    @Override
    public List<Comment> findByBook(Book book) {
        return commentRepository.findAllByBook(book);
    }

    @Override
    public void deleteByBook(Book book) {
        commentRepository.deleteAllByBook(book);
    }

    @Override
    public void updateBook(Book book) {
        commentRepository.updateBook(book);
    }
}
