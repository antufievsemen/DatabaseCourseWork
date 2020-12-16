package ru.spbstu.antufievsemen.coursedatabase.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import ru.spbstu.antufievsemen.coursedatabase.entity.Manager;
import ru.spbstu.antufievsemen.coursedatabase.entity.Role;
import ru.spbstu.antufievsemen.coursedatabase.exception.ManagerNotFoundException;
import ru.spbstu.antufievsemen.coursedatabase.repository.ManagerRepository;
import ru.spbstu.antufievsemen.coursedatabase.repository.RoleRepository;

@Service
public class ManagerService {

  private final ManagerRepository managerRepository;
  private final UserService userService;
  private final RoleRepository roleRepository;

  public ManagerService(ManagerRepository managerRepository, UserService userService, RoleRepository roleRepository) {
    this.managerRepository = managerRepository;
    this.userService = userService;
    this.roleRepository = roleRepository;
  }

  public List<Manager> listManagers() {
    return (List<Manager>) managerRepository.findAll();
  }

  public Manager findManagerById(long id) {
    Optional<Manager> optionalManager = managerRepository.findById(id);
    if (optionalManager.isPresent()) {
      return optionalManager.get();
    }
    throw new ManagerNotFoundException("QueryPart with id = " + id + "does not exist");
  }

  public void addManager(Manager manager) {
    Set<Role> roles = new HashSet<>();
    roles.add(roleRepository.findByName("FACTORY"));
    ;
    manager.getUser().setRoleSet(roles);
    userService.addUser(manager.getUser());
    managerRepository.save(manager);
  }

  public boolean updateManager(Manager manager) {
    if (managerRepository.existsById(manager.getId())) {
      managerRepository.save(manager);
      return true;
    }
    return false;
  }

  public Manager deleteManagerById(long id) {
    Optional<Manager> manager = managerRepository.findById(id);
    if (manager.isPresent()) {
      long idUser = manager.get().getId();
      managerRepository.deleteById(id);
//      userService.deleteUserById(idUser);
    }
    return manager.get();
  }

  public boolean existsByPassportNumber(String passportNumber) {
    return managerRepository.existsByPassportNumber(passportNumber);
  }

  public Manager getByPassportNumber(String passportNumber) {
    return managerRepository.getByPassportNumber(passportNumber);
  }
}
