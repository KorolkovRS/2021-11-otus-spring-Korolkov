package ru.korolkovrs.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.i18n_util.Internalizer;

@Service
@RequiredArgsConstructor
public class IOInternatiolizeServiceImpl implements IOInternatiolizeService {
    private final IOService ioService;
    private final Internalizer internalizer;

    @Override
    public String input() {
        return ioService.input();
    }

    @Override
    public void out(String s, Object... args) {
        ioService.out(s);
    }

    @Override
    public void outWithInternalize(String s, Object... args) {
        ioService.out(internalizer.internalizeMessage(s), args);
    }
}
