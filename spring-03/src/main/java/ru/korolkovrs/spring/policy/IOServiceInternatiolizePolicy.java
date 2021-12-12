package ru.korolkovrs.spring.policy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring.i18n_util.Internalizer;
import ru.korolkovrs.spring.service.IOServiceImpl;

@Component
@RequiredArgsConstructor
public class IOServiceInternatiolizePolicy {
    private final IOServiceImpl ioService;
    private final Internalizer internalizer;

    public String input() {
        return ioService.input();
    }

    public void out(String s, Object... args) {
        ioService.out(s);
    }

    public void outWithInternalize(String s, Object... args) {
        ioService.out(internalizer.internalizeMessage(s), args);
    }
}
