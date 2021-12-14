package ru.korolkovrs.spring.i18n_util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "locale-manager")
public class ResourcePathResolver {
    private final LocaleContext localeContext;


    private Map<String, String> codes = new HashMap<>();

    public ResourcePathResolver(LocaleContext localeContext) {
        this.localeContext = localeContext;
    }

    private final static Locale DEFAULT_LOCALE = new Locale("en", "EN");

    public String getResourcePath() {
        return codes.getOrDefault(localeContext.getLocale().getLanguage(), codes.get(DEFAULT_LOCALE.getLanguage()));
    }

    public void setCodes(Map<String, String> codes) {
        this.codes = codes;
    }

    public Map<String, String> getCodes() {
        return codes;
    }

    public static Locale getDefaultLocale() {
        return DEFAULT_LOCALE;
    }
}
