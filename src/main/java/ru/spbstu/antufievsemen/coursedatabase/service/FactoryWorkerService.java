package ru.spbstu.antufievsemen.coursedatabase.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import ru.spbstu.antufievsemen.coursedatabase.entity.FactoryWorker;
import ru.spbstu.antufievsemen.coursedatabase.entity.Role;
import ru.spbstu.antufievsemen.coursedatabase.exception.FactoryWorkerNotFoundException;
import ru.spbstu.antufievsemen.coursedatabase.repository.FactoryWorkerRepository;
import ru.spbstu.antufievsemen.coursedatabase.repository.RoleRepository;

@Service
public class FactoryWorkerService {

  private final FactoryWorkerRepository factoryWorkerRepository;
  private final UserService userService;
  private final RoleRepository roleRepository;

  public FactoryWorkerService(FactoryWorkerRepository factoryWorkerRepository, UserService userService, RoleRepository roleRepository) {
    this.factoryWorkerRepository = factoryWorkerRepository;
    this.userService = userService;
    this.roleRepository = roleRepository;
  }

  public List<FactoryWorker> listFactoryWorkers() {
    return (List<FactoryWorker>) factoryWorkerRepository.findAll();
  }

  public FactoryWorker findFactoryWorkerById(long id) {
    Optional<FactoryWorker> optionalFactoryWorker = factoryWorkerRepository.findById(id);
    if (optionalFactoryWorker.isPresent()) {
      return optionalFactoryWorker.get();
    }
    throw new FactoryWorkerNotFoundException("QueryPart with id = " + id + "does not exist");
  }

  public void addFactoryWorker(FactoryWorker factoryWorker) {
    Set<Role> roles = new HashSet<>();
    roles.add(roleRepository.findByName("FACTORY"));
    factoryWorker.getUser().setRoleSet(roles);
    userService.addUser(factoryWorker.getUser());
    factoryWorkerRepository.save(factoryWorker);
  }

  public boolean updateFactoryWorker(FactoryWorker factoryWorker) {
    if (factoryWorkerRepository.existsById(factoryWorker.getId())) {
      factoryWorkerRepository.save(factoryWorker);
      return true;
    }
    return false;
  }

  public FactoryWorker deleteFactoryWorkerById(long id) {
    Optional<FactoryWorker> factoryWorker = factoryWorkerRepository.findById(id);
    if (factoryWorker.isPresent()) {
      long idUser = factoryWorker.get().getId();
      factoryWorkerRepository.deleteById(id);
//      userService.deleteUserById(idUser);
    }
    return factoryWorker.get();
  }

  public boolean existsByPassportNumber(String passportNumber) {
    return factoryWorkerRepository.existsByPassportNumber(passportNumber);
  }

  public FactoryWorker getByPassportNumber(String passportNumber) {
    return factoryWorkerRepository.getByPassportNumber(passportNumber);
  }
}
