package ru.korolkovrs.spring.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.korolkovrs.spring.domain.Question;
import ru.korolkovrs.spring.service.ResourceProviderService;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    private final ResourceProviderService resourceDiscoverService;

    @Override
    public List<Question> getAll() {
        List<Question> beans = null;
        try(Reader in = resourceDiscoverService.getResourceReader()) {
            beans = new CsvToBeanBuilder(in)
                    .withType(Question.class).build().parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beans;
    }
}
