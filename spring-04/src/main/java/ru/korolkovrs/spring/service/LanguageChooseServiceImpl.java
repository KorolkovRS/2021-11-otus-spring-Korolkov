package ru.korolkovrs.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.domain.Language;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageChooseServiceImpl implements LanguageChooseService {
    private final LanguageService languageService;
    private final IOInternatiolizeService ioService;
    @Override
    public void choose() {
        List<Language> languages = languageService.getLanguages();
        ioService.outWithInternalize("applicationEventsCommand.available_languages");
        languages.stream().forEach((l) -> ioService.out("-" + l.getLanguageCode()));
        ioService.outWithInternalize("applicationEventsCommand.choose_language");
        String languageCode = ioService.input();
        Language language = new Language(languageCode);
        languageService.setLanguage(language);
        ioService.outWithInternalize("applicationEventsCommand.successful", language.getLanguageCode());
    }
}
