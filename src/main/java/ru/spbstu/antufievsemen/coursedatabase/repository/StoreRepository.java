package ru.spbstu.antufievsemen.coursedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.antufievsemen.coursedatabase.entity.Resource;

public interface StoreRepository extends CrudRepository<Resource, Long> {
}
