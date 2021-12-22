package ru.korolkovrs.spring.i18n_util;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Primary;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Primary
public class UserSelectionLocaleResolver implements LocaleContext {
    private final LanguageSelector languageSelector;

    @Override
    public Locale getLocale() {
        return new Locale(languageSelector.getCurrentLanguage().getLanguageCode());
    }
}
