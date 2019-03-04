package ru.dilarionov.test.helper;

import ru.dilarionov.springtest.user.domain.User;

public class DefaultUser {
    public static User get() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        return user;
    }
}
