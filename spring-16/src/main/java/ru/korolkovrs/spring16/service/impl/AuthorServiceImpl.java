package ru.korolkovrs.spring16.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring16.domain.Author;
import ru.korolkovrs.spring16.repository.AuthorRepository;
import ru.korolkovrs.spring16.service.AuthorService;

import java.util.List;
import java.util.Optional;

import static ru.korolkovrs.spring16.repository.spec.AuthorSpecification.*;
import static ru.korolkovrs.spring16.repository.spec.AuthorSpecification.likeName;

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
    public List<Author> findByName(String authorName) {
        Specification<Author> specification = Specification.where(likeName(authorName));
        return authorRepository.findAll(specification);
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
