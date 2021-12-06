package ru.korolkovrs.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.domain.User;
import ru.korolkovrs.spring.i18n_util.Internalizer;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final IOService ioService;
    private final Internalizer internalizer;

    @Override
    public User getUser() {
        User user = new User();
        ioService.out(internalizer.internalizeMessage("userService.enter_name"));
        user.setName(ioService.input());
        ioService.out(internalizer.internalizeMessage("userService.enter_surname"));
        user.setSurname(ioService.input());
        return user;
    }
}
