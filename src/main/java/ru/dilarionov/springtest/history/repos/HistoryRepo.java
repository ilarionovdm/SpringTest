package ru.dilarionov.springtest.history.repos;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import ru.dilarionov.springtest.history.domain.History;

@Qualifier("history")
public interface HistoryRepo extends CrudRepository<History, Long> {

}
