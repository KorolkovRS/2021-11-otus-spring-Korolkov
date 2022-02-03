package ru.korolkovrs.spring17.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.domain.Comment;
import ru.korolkovrs.spring17.exception.NotFoundException;
import ru.korolkovrs.spring17.repository.CommentRepository;
import ru.korolkovrs.spring17.service.CommentService;

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
        Comment comment = dao.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Комментраия с id=%d не найдено", id))
        );
        dao.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByBook(Book book) {
        return dao.findByBookId(book.getId());
    }
}
