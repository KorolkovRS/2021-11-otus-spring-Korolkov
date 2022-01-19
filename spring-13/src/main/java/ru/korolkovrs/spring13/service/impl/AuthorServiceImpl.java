package ru.korolkovrs.spring13.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring13.repository.AuthorRepository;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.service.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Optional<Author> findById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findByName(String authorName) {
        return authorRepository.findAllByNameIgnoreCaseContaining(authorName);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
