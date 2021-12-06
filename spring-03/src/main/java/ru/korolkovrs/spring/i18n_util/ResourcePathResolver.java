package ru.korolkovrs.spring.i18n_util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Component
@ConfigurationProperties(prefix = "codes")

public class ResourcePathResolver {
    private Map<String, String> codes = new HashMap<>();

    private final static Locale DEFAULT_LOCALE = new Locale("en", "EN");

    public String getResourcePath(Locale locale) {
        return codes.getOrDefault(locale.getLanguage(), codes.get(DEFAULT_LOCALE.getLanguage()));
    }

    public void setCodes(Map<String, String> codes) {
        this.codes = codes;
    }
}
