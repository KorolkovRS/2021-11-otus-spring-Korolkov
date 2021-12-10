package ru.korolkovrs.spring.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring.domain.Question;
import ru.korolkovrs.spring.exception.QuestionLoadingException;
import ru.korolkovrs.spring.i18n_util.ResourcePathResolver;
import ru.korolkovrs.spring.provider.ResourceProvider;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Repository
@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    private final ResourceProvider resourceProvider;

    @Override
    public List<Question> getAll() {
        List<Question> beans;
        try (Reader in = new InputStreamReader(resourceProvider.getResourceStream())) {
            beans = new CsvToBeanBuilder<Question>(in)
                    .withSeparator(';')
                    .withType(Question.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new QuestionLoadingException("Exception during reading questions file", e);
        }
        return beans != null ? beans : Collections.emptyList();
    }
}
