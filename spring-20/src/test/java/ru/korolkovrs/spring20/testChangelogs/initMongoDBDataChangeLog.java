package ru.korolkovrs.spring20.testChangelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.korolkovrs.spring20.domain.Author;
import ru.korolkovrs.spring20.domain.Book;
import ru.korolkovrs.spring20.domain.Comment;
import ru.korolkovrs.spring20.domain.Genre;

@ChangeLog(order = "001")
public class initMongoDBDataChangeLog {
    private Author author1;
    private Author author2;

    private Genre genre1;

    private Book book1;
    private Book book2;

    private Comment comment1;
    private Comment comment2;
    private Comment comment3;

    @ChangeSet(order = "000", id = "dropDB", author = "korolkovrs", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "addAuthors", author = "korolkovrs", runAlways = true)
    public void addAuthors(MongockTemplate template) {
        author1 = template.save(new Author("Ф.М. Достоевский"));
        author2 = template.save(new Author("Л.Н. Толстой"));
    }

    @ChangeSet(order = "002", id = "addGenres", author = "korolkovrs", runAlways = true)
    public void addGenres(MongockTemplate template) {
        genre1 = template.save(new Genre("Русская классика"));
    }

    @ChangeSet(order = "003", id = "addBooks", author = "korolkovrs", runAlways = true)
    public void addBooks(MongockTemplate template) {
        book1 = template.save(new Book("Книга 1", author1, genre1));
        book2 = template.save(new Book("Книга 2", author2, genre1));
    }

    @ChangeSet(order = "004", id = "addComments", author = "korolkovrs", runAlways = true)
    public void addComments(MongockTemplate template) {
        comment1 = template.save(new Comment("комментарий 1", book2));
        comment2 = template.save(new Comment("комментарий 2", book2));
    }
}
