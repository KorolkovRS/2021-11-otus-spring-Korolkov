package ru.korolkovrs.spring17.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring17.domain.Author;
import ru.korolkovrs.spring17.repository.AuthorRepository;
import ru.korolkovrs.spring17.service.AuthorService;
import ru.korolkovrs.spring17.repository.spec.AuthorSpecification;

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
    public List<Author> findByName(String authorName) {
        Specification<Author> specification = Specification.where(AuthorSpecification.likeName(authorName));
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
