package ru.korolkovrs.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.domain.User;
import ru.korolkovrs.spring.i18n_util.Internalizer;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final IOInternatiolizeService ioService;

    @Override
    public User getUser() {
        User user = new User();
        ioService.outWithInternalize("userService.enter_name");
        user.setName(ioService.input());
        ioService.outWithInternalize("userService.enter_surname");
        user.setSurname(ioService.input());
        return user;
    }
}
