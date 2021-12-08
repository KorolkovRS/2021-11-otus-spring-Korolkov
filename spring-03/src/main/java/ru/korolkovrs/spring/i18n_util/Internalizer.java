package ru.korolkovrs.spring.i18n_util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class Internalizer {
    private final LocaleContext localeContext;

    private final String baseName;

    public Internalizer(LocaleContext localeContext, @Value("${spring.messages.basename}")String baseName) {
        this.localeContext = localeContext;
        this.baseName = baseName;
    }

    public String internalizeMessage(String s) {
        return ResourceBundle.getBundle(baseName, localeContext.getLocale()).getString(s);
    }
}
