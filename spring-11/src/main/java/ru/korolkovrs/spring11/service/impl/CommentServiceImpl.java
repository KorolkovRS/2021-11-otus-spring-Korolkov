package ru.korolkovrs.spring11.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring11.repository.CommentRepository;
import ru.korolkovrs.spring11.domain.Book;
import ru.korolkovrs.spring11.domain.Comment;
import ru.korolkovrs.spring11.service.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository dao;

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        log.warn("do: " + comment.toString());
        comment = dao.save(comment);
        log.warn("posle: " + comment.toString());
        return comment;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Comment comment = dao.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Комментраия с id=%d не найдено", id))
        );
        dao.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByBook(Book book) {
        return dao.findByBookId(book.getId());
    }
}
