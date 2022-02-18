package ru.korolkovrs.spring17.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.korolkovrs.spring17.domain.Comment;
import ru.korolkovrs.spring17.repository.CommentRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Comment dao")
@DataJpaTest
class CommentRepositoryTest {
    private final static Long EXISTED_BOOK_ID = 1L;
    private final static Integer EXPECTED_NUMBER_OF_COMMENT = 2;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("Должен вернуть все комментарии к книге")
    void shouldFindAllCommentByBookId() {
        List<Comment> comments = commentRepository.findByBookId(EXISTED_BOOK_ID);
        assertThat(comments).hasSize(EXPECTED_NUMBER_OF_COMMENT);
    }
}