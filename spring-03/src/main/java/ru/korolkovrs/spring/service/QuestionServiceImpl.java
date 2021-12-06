package ru.korolkovrs.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.dao.QuestionDao;
import ru.korolkovrs.spring.domain.Question;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;

    @Override
    public List<Question> getAllWithLocale(Locale locale) {
        return dao.getAllWithLocale(locale);
    }
}
