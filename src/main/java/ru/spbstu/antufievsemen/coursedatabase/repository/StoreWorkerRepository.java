package ru.spbstu.antufievsemen.coursedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.antufievsemen.coursedatabase.entity.StoreWorker;

public interface StoreWorkerRepository extends CrudRepository<StoreWorker, Long> {
  boolean existsByNameAndSecondNameAndPassportNumber(String name, String secondName, String passportNumber);
  boolean existsByPassportNumber(String passportNumber);
  StoreWorker getByPassportNumber(String passportNumber);
}
