package ru.spbstu.antufievsemen.coursedatabase.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import ru.spbstu.antufievsemen.coursedatabase.entity.Role;
import ru.spbstu.antufievsemen.coursedatabase.entity.StoreWorker;
import ru.spbstu.antufievsemen.coursedatabase.exception.StoreWorkerNotFoundException;
import ru.spbstu.antufievsemen.coursedatabase.repository.RoleRepository;
import ru.spbstu.antufievsemen.coursedatabase.repository.StoreWorkerRepository;

@Service
public class StoreWorkerService {

  private final StoreWorkerRepository storeWorkerRepository;
  private final UserService userService;
  private final RoleRepository roleRepository;

  public StoreWorkerService(StoreWorkerRepository storeWorkerRepository, UserService userService, RoleRepository roleRepository) {
    this.storeWorkerRepository = storeWorkerRepository;
    this.userService = userService;
    this.roleRepository = roleRepository;
  }

  public List<StoreWorker> listStoreWorker() {
    return (List<StoreWorker>) storeWorkerRepository.findAll();
  }

  public StoreWorker findStoreWorkerById(long id) {
    Optional<StoreWorker> optionalStoreWorker = storeWorkerRepository.findById(id);
    if (optionalStoreWorker.isPresent()) {
      return optionalStoreWorker.get();
    }
    throw new StoreWorkerNotFoundException("QueryPart with id = " + id + "does not exist");
  }

  public void addStoreWorker(StoreWorker storeWorker) {
    Set<Role> roles = new HashSet<>();
    roles.add(roleRepository.findByName("FACTORY"));
    storeWorker.getUser().setRoleSet(roles);
    userService.addUser(storeWorker.getUser());
    storeWorkerRepository.save(storeWorker);
  }

  public boolean updateStoreWorker(StoreWorker storeWorker) {
    if (storeWorkerRepository.existsById(storeWorker.getId())) {
      storeWorkerRepository.save(storeWorker);
      return true;
    }
    return false;
  }

  public StoreWorker deleteStoreWorkerById(long id) {
    Optional<StoreWorker> storeWorker = storeWorkerRepository.findById(id);

    if (storeWorker.isPresent()) {
      long idUser = storeWorker.get().getId();
      storeWorkerRepository.deleteById(id);
//      userService.deleteUserById(idUser);
    }
    return storeWorker.get();
  }

  public boolean existsByPassportNumber(String passportNumber) {
    return storeWorkerRepository.existsByPassportNumber(passportNumber);
  }

  public StoreWorker getByPassportNumber(String passportNumber) {
    return storeWorkerRepository.getByPassportNumber(passportNumber);
  }
}
