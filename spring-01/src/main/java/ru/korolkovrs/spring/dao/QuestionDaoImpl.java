package ru.korolkovrs.spring.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.korolkovrs.spring.domain.Question;
import ru.korolkovrs.spring.provider.ResourceProvider;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    private final ResourceProvider resourceProvider;

    @Override
    public List<Question> getAll() {
        List<Question> beans = null;
        try(Reader in = new InputStreamReader(resourceProvider.getResourceStream())) {
            beans = new CsvToBeanBuilder<Question>(in)
                    .withType(Question.class).build().parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beans;
    }
}
