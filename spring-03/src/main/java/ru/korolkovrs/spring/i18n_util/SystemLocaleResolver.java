package ru.korolkovrs.spring.i18n_util;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SystemLocaleResolver implements LocaleContext {
    @Override
    public Locale getLocale() {
        return Locale.getDefault();
    }
}
