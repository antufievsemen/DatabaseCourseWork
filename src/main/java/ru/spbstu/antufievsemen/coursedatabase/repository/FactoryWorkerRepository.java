package ru.spbstu.antufievsemen.coursedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.jsf.FacesContextUtils;
import ru.spbstu.antufievsemen.coursedatabase.entity.FactoryWorker;
import ru.spbstu.antufievsemen.coursedatabase.entity.StoreWorker;

public interface FactoryWorkerRepository extends CrudRepository<FactoryWorker, Long> {
  boolean existsByNameAndSecondNameAndPassportNumber(String name, String secondName, String passportNumber);
  boolean existsByPassportNumber(String passportNumber);
  FactoryWorker getByPassportNumber(String passportNumber);
}
