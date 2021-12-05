package ru.korolkovrs.spring.provider;

import java.io.InputStream;

public interface ResourceProvider {
    InputStream getResourceStream();
}
