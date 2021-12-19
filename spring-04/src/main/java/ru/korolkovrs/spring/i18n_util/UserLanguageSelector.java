package ru.korolkovrs.spring.i18n_util;

import org.springframework.beans.factory.annotation.Value;
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

    private final Language defaultLanguage;

    public UserLanguageSelector(@Value("${locale-resolver.default-language}") String defaultLanguageCode) {
        this.defaultLanguage = new Language(defaultLanguageCode);
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    @Override
    public List<Language> getLanguages() {
        return languages;
    }

    @Override
    public Language getCurrentLanguage() {
        return currentLanguage != null ? currentLanguage: defaultLanguage;
    }

    @Override
    public void setCurrentLanguage(Language language) {
        if (!languages.contains(language)) {
            throw new IllegalArgumentException(String.format("The \"%s\" language is not supported", language.getLanguageCode()));
        }
        currentLanguage = language;
    }
}
