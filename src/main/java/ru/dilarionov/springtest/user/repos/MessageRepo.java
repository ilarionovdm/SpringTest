package ru.dilarionov.springtest.user.repos;

import org.springframework.data.repository.CrudRepository;
import ru.dilarionov.springtest.user.domain.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
