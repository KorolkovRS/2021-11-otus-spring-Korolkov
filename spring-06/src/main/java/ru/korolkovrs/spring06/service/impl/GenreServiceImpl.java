package ru.korolkovrs.spring06.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring06.dao.GenreRepository;
import ru.korolkovrs.spring06.domain.Genre;
import ru.korolkovrs.spring06.service.GenreService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findById(Long id) {
        genreRepository.findById(id);
        return genreRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }
}
