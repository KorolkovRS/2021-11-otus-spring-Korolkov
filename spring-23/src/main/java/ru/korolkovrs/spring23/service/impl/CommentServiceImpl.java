package ru.korolkovrs.spring23.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring23.domain.Book;
import ru.korolkovrs.spring23.domain.Comment;
import ru.korolkovrs.spring23.repository.CommentRepository;
import ru.korolkovrs.spring23.service.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
        return dao.save(comment);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByBook(Book book) {
        return dao.findByBookId(book.getId());
    }
}
