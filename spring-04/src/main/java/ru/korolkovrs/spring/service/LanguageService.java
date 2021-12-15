package ru.korolkovrs.spring.service;

import ru.korolkovrs.spring.domain.Language;

import java.util.List;

public interface LanguageService {
    List<Language> getLanguages();
    void setLanguage(Language language);
}
