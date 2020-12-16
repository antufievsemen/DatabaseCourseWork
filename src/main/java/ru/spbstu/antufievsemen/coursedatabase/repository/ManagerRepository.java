package ru.spbstu.antufievsemen.coursedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.antufievsemen.coursedatabase.entity.Manager;
import ru.spbstu.antufievsemen.coursedatabase.entity.StoreWorker;

public interface ManagerRepository extends CrudRepository<Manager, Long> {
  boolean existsByNameAndSecondNameAndPassportNumber(String name, String secondName, String passportNumber);
  boolean existsByPassportNumber(String passportNumber);
  Manager getByPassportNumber(String passportNumber);
}
