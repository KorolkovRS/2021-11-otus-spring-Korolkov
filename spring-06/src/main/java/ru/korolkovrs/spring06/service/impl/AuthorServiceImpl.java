package ru.korolkovrs.spring06.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring06.dao.AuthorRepository;
import ru.korolkovrs.spring06.domain.Author;
import ru.korolkovrs.spring06.service.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
