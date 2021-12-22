package ru.korolkovrs.spring.i18n_util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "locale-manager")
public class PropertiesFileResourcePathResolver implements ResourcePathResolver{
    private final LocaleContext localeContext;
    private final Locale defaultLocale;
    private Map<String, String> codes = new HashMap<>();

    public PropertiesFileResourcePathResolver(LocaleContext localeContext,
                                              @Value("${locale-resolver.default-language}") String defaultLanguageCode) {
        this.localeContext = localeContext;
        this.defaultLocale = new Locale(defaultLanguageCode);
    }

    @Override
    public String getResourcePath() {
        return codes.getOrDefault(localeContext.getLocale().getLanguage(), codes.get(defaultLocale.getLanguage()));
    }

    public void setCodes(Map<String, String> codes) {
        this.codes = codes;
    }

    public Map<String, String> getCodes() {
        return codes;
    }

    public Locale getDefaultLocale() {
        return defaultLocale;
    }
}
