package ru.korolkovrs.spring13.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.domain.Genre;
import ru.korolkovrs.spring13.repository.BookRepositoryCustom;

@RequiredArgsConstructor
@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    private final MongoTemplate template;

    @Override
    public void updateAuthor(Author author) {
        Query query = new Query();
        query.addCriteria(Criteria.where("author._id").is(author.getId()));
        Update update = new Update();
        update.set("author.name", author.getName());
        template.updateMulti(query, update, Book.class);
    }

    @Override
    public void updateGenre(Genre genre) {
        Query query = new Query();
        query.addCriteria(Criteria.where("genre._id").is(genre.getId()));
        Update update = new Update();
        update.set("genre.genreName", genre.getGenreName());
        template.updateMulti(query, update, Book.class);
    }
}
