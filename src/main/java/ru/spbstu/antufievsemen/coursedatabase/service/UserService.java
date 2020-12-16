package ru.spbstu.antufievsemen.coursedatabase.service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.spbstu.antufievsemen.coursedatabase.entity.User;
import ru.spbstu.antufievsemen.coursedatabase.exception.ResourceNotFoundException;
import ru.spbstu.antufievsemen.coursedatabase.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public List<User> listUsers() {
    return (List<User>) userRepository.findAll();
  }

  public User findUserById(long id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      return optionalUser.get();
    }
    throw new ResourceNotFoundException("User with id = " + id + "does not exist");
  }

  public boolean addUser(User user) {
    if (userRepository.existsUserByUsername(user.getUsername())) {
      return false;
    }
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return true;
  }

  public boolean updateUser(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return true;
  }

  public User findUserByUsername(String login) {
    return userRepository.findUserByUsername(login);
  }

  public User deleteUserById(long id) {
    Optional<User> user = userRepository.findById(id);
    user.ifPresent(value -> userRepository.deleteById(value.getId()));
    return user.get();
  }

  public boolean existsUserByLoginAndPassword(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return userRepository.existsUserByUsernameAndPassword(user.getUsername(), user.getPassword());
  }
}
