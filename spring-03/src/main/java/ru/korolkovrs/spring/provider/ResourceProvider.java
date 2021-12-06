package ru.korolkovrs.spring.provider;

import java.io.InputStream;
import java.util.Locale;

public interface ResourceProvider {
    InputStream getResourceStream(Locale locale);
}
