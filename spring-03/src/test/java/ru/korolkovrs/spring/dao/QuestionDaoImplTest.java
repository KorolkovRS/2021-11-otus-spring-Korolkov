package ru.korolkovrs.spring.dao;

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
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
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

    @Test
    @DisplayName("Should return a list of questions")
    void shouldReturnListOfQuestions() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("test_questions.csv");
        given(resourceProvider.getResourceStream(any())).willReturn(is);
        List<Question> questions = questionDao.getAllWithLocale(Locale.ENGLISH);
        assertThat(questions)
                .isNotEmpty()
                .hasSize(5);
        is.close();
    }

    @Test
    @DisplayName("Must request a data stream from the resource from the ResourceProvider")
    void shouldRequestInputStream() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("test_questions.csv");
        Locale locale = new Locale("ru", "RU");
        given(resourceProvider.getResourceStream(locale)).willReturn(is);
        questionDao.getAllWithLocale(locale);
        verify(resourceProvider, times(1)).getResourceStream(locale);
        is.close();
    }
}

