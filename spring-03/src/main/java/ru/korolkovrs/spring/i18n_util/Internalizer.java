package ru.korolkovrs.spring.i18n_util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Internalizer {
    private final LocaleContext localeContext;
    private final MessageSource messageSource;

    public String internalizeMessage(String s) {
        return messageSource.getMessage(s, null, localeContext.getLocale());
    }
}
