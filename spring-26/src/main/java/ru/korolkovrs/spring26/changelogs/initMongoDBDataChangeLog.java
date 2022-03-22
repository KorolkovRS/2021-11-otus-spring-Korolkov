package ru.korolkovrs.spring26.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.korolkovrs.spring26.domain.jpa.AuthorJpa;
import ru.korolkovrs.spring26.domain.jpa.BookJpa;
import ru.korolkovrs.spring26.domain.jpa.CommentJpa;
import ru.korolkovrs.spring26.domain.jpa.GenreJpa;
import ru.korolkovrs.spring26.util.CommentDataResolver;


@ChangeLog(order = "001")
public class initMongoDBDataChangeLog {

    private AuthorJpa authorJpa1;
    private AuthorJpa authorJpa2;
    private AuthorJpa authorJpa3;

    private GenreJpa genreJpa1;
    private GenreJpa genreJpa2;

    private BookJpa bookJpa1;
    private BookJpa bookJpa2;
    private BookJpa bookJpa3;

    private CommentJpa commentJpa1;
    private CommentJpa commentJpa2;
    private CommentJpa commentJpa3;

    @ChangeSet(order = "000", id = "dropDB", author = "korolkovrs", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "addAuthors", author = "korolkovrs", runAlways = true)
    public void addAuthors(MongockTemplate template) {
        authorJpa1 = template.save(new AuthorJpa("Ф.М. Достоевский"));
        authorJpa2 = template.save(new AuthorJpa("Л.Н. Толстой"));
        authorJpa3 = template.save(new AuthorJpa("Роджер Желязны"));
    }

    @ChangeSet(order = "002", id = "addGenres", author = "korolkovrs", runAlways = true)
    public void addGenres(MongockTemplate template) {
        genreJpa1 = template.save(new GenreJpa("Русская классика"));
        genreJpa2 = template.save(new GenreJpa("Фантастика"));
    }

    @ChangeSet(order = "003", id = "addBooks", author = "korolkovrs", runAlways = true)
    public void addBooks(MongockTemplate template) {
        bookJpa1 = template.save(new BookJpa("Преступление и наказание", authorJpa1, genreJpa1));
        bookJpa2 = template.save(new BookJpa("Война и мир", authorJpa2, genreJpa1));
        bookJpa3 = template.save(new BookJpa("Хроники Амбера", authorJpa3, genreJpa2));
    }

    @ChangeSet(order = "004", id = "addComments", author = "korolkovrs", runAlways = true)
    public void addComments(MongockTemplate template) {
        CommentDataResolver dataResolver = new CommentDataResolver();
        commentJpa1 = template.save(
                dataResolver.addOrUpdateDate(new CommentJpa("Русская классика это страдание. Страдает главный герой, автор или читатель. " +
                        "Если страдают все трое, то это шедевр русской классики.", bookJpa2)
                )
        );
        commentJpa2 = template.save(
                dataResolver.addOrUpdateDate(new CommentJpa("Описание природы", bookJpa2))
        );
        commentJpa3 = template.save(
                dataResolver.addOrUpdateDate(new CommentJpa("Золотая коллекция зарубежной фантазии", bookJpa3))
        );
    }
}