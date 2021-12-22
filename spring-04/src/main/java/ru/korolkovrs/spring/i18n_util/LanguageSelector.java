package ru.korolkovrs.spring.i18n_util;

import ru.korolkovrs.spring.domain.Language;

import java.util.List;

public interface LanguageSelector {
    List<Language> getLanguages();
    Language getCurrentLanguage();
    void setCurrentLanguage(Language language);

}
