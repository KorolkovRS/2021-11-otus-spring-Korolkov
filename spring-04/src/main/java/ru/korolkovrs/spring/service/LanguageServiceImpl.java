package ru.korolkovrs.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.i18n_util.LanguageSelector;
import ru.korolkovrs.spring.domain.Language;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageSelector selectorService;

    @Override
    public List<Language> getLanguages() {
        return selectorService.getLanguages();
    }

    @Override
    public void setLanguage(Language language) {
        selectorService.setCurrentLanguage(language);
    }
}
