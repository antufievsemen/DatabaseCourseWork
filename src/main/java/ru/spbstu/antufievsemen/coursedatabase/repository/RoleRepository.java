package ru.spbstu.antufievsemen.coursedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.antufievsemen.coursedatabase.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
  Role findByName(String name);
}
