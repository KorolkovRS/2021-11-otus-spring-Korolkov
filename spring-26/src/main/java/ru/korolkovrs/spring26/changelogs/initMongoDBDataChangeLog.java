package ru.korolkovrs.spring26.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.korolkovrs.spring26.domain.Author;
import ru.korolkovrs.spring26.domain.Book;
import ru.korolkovrs.spring26.domain.Comment;
import ru.korolkovrs.spring26.domain.Genre;
import ru.korolkovrs.spring26.util.CommentDataResolver;


@ChangeLog(order = "001")
public class initMongoDBDataChangeLog {

    private Author author1;
    private Author author2;
    private Author author3;

    private Genre genre1;
    private Genre genre2;

    private Book book1;
    private Book book2;
    private Book book3;

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
        author3 = template.save(new Author("Роджер Желязны"));
    }

    @ChangeSet(order = "002", id = "addGenres", author = "korolkovrs", runAlways = true)
    public void addGenres(MongockTemplate template) {
        genre1 = template.save(new Genre("Русская классика"));
        genre2 = template.save(new Genre("Фантастика"));
    }

    @ChangeSet(order = "003", id = "addBooks", author = "korolkovrs", runAlways = true)
    public void addBooks(MongockTemplate template) {
        book1 = template.save(new Book("Преступление и наказание", author1, genre1));
        book2 = template.save(new Book("Война и мир", author2, genre1));
        book3 = template.save(new Book("Хроники Амбера", author3, genre2));
    }

    @ChangeSet(order = "004", id = "addComments", author = "korolkovrs", runAlways = true)
    public void addComments(MongockTemplate template) {
        CommentDataResolver dataResolver = new CommentDataResolver();
        comment1 = template.save(
                dataResolver.addOrUpdateDate(new Comment("Русская классика это страдание. Страдает главный герой, автор или читатель. " +
                        "Если страдают все трое, то это шедевр русской классики.", book2)
                )
        );
        comment2 = template.save(
                dataResolver.addOrUpdateDate(new Comment("Описание природы", book2))
        );
        comment3 = template.save(
                dataResolver.addOrUpdateDate(new Comment("Золотая коллекция зарубежной фантазии", book3))
        );
    }
}