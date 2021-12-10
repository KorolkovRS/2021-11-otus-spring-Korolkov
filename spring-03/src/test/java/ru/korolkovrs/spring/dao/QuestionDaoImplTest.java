package ru.korolkovrs.spring.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korolkovrs.spring.domain.Question;
import ru.korolkovrs.spring.provider.ResourceProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@DisplayName("QuestionDaoImpl")
@ExtendWith(MockitoExtension.class)
class QuestionDaoImplTest {
    @InjectMocks
    private QuestionDaoImpl questionDao;

    @Mock
    private ResourceProvider resourceProvider = mock(ResourceProvider.class);

    private InputStream is;

    @BeforeEach
    void initData() {
        is = getClass().getClassLoader().getResourceAsStream("test_questions.csv");
    }

    @AfterEach
    void closeResources() throws IOException {
        is.close();
    }

    @Test
    @DisplayName("Should return a list of questions")
    void shouldReturnListOfQuestions() {
        given(resourceProvider.getResourceStream()).willReturn(is);
        List<Question> questions = questionDao.getAll();
        assertThat(questions)
                .isNotEmpty()
                .hasSize(5);
    }

    @Test
    @DisplayName("Must request a data stream from the resource from the ResourceProvider")
    void shouldRequestInputStream() {
        given(resourceProvider.getResourceStream()).willReturn(is);
        questionDao.getAll();
        verify(resourceProvider, times(1)).getResourceStream();
    }
}

