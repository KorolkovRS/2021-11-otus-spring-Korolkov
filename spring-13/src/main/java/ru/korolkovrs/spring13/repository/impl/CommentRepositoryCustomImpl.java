package ru.korolkovrs.spring13.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.domain.Comment;
import ru.korolkovrs.spring13.repository.CommentRepositoryCustom;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final MongoTemplate template;

    @Override
    public void updateBook(Book book) {
        Query query = new Query();
        query.addCriteria(Criteria.where("book._id").is(book.getId()));
        Update update = new Update();
        update.set("book.title", book.getTitle());
        template.updateMulti(query, update, Comment.class);
    }
}
