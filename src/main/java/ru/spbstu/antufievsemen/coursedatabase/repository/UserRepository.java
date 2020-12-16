package ru.spbstu.antufievsemen.coursedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.antufievsemen.coursedatabase.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

  boolean existsUserByUsername(String login);
  User findUserByUsername(String login);
  boolean existsUserByUsernameAndPassword(String login, String password);
}
