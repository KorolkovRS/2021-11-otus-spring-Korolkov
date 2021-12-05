package ru.korolkovrs.spring.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.domain.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final IOService ioService;

    @Override
    public User getUser() {
        User user = new User();
        ioService.out("Enter your name:");
        user.setName(ioService.input());
        ioService.out("Enter your surname:");
        user.setSurname(ioService.input());
        return user;
    }
}
