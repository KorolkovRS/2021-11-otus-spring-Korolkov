package ru.korolkovrs.spring.i18n_util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.domain.Language;

import java.util.ArrayList;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "locale-resolver")
public class UserLanguageSelector implements LanguageSelector {
    private List<Language> languages = new ArrayList<>();
    private Language currentLanguage;

    private static final Language DEFAULT_LANGUAGE = new Language("en");

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    @Override
    public List<Language> getLanguages() {
        return languages;
    }

    @Override
    public Language getCurrentLanguage() {
        return currentLanguage != null ? currentLanguage: DEFAULT_LANGUAGE;
    }

    @Override
    public void setCurrentLanguage(Language language) {
        if (!languages.contains(language)) {
            throw new IllegalArgumentException(String.format("The \"%s\" language is not supported", language.getLanguageCode()));
        }
        currentLanguage = language;
    }
}
