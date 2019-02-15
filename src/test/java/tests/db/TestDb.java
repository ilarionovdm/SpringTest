package tests.db;

import config.TestUserDbConfig;
import helper.DefaultUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dilarionov.springtest.user.domain.User;
import ru.dilarionov.springtest.user.repos.UserRepo;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestUserDbConfig.class)
public class TestDb {


    @Mock
    @Autowired
    private UserRepo userRepo;

    @Test
    public void test01() {
        User user = DefaultUser.get();
        userRepo.save(user);
        User saved = userRepo.findByUsername("user");
        assertThat("Проверка получения пользователя", saved!=null);
        assertThat("Проверка создания пользователя", user.getUsername().equals(saved.getUsername()));
    }
}
