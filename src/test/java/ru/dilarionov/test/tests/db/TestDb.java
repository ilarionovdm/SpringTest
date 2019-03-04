package ru.dilarionov.test.tests.db;

import ru.dilarionov.test.config.TestUserDbConfig;
import ru.dilarionov.test.helper.DefaultUser;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.dilarionov.springtest.user.domain.User;
import ru.dilarionov.springtest.user.repos.UserRepo;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestUserDbConfig.class)
public class TestDb {


    @Mock
    @Autowired
    private UserRepo userRepo;

    @Test
    @Description("Тест работы заглушки БД user")
    public void test01() {
        User user = DefaultUser.get();
        userRepo.save(user);
        User saved = userRepo.findByUsername("user");
        assertThat("Проверка получения пользователя", saved!=null);
        assertThat("Проверка создания пользователя", user.getUsername().equals(saved.getUsername()));
    }
}
