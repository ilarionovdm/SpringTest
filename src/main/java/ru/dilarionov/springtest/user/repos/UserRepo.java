package ru.dilarionov.springtest.user.repos;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import ru.dilarionov.springtest.user.domain.User;

@Qualifier("user")
public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
