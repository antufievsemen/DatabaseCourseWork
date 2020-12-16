package ru.spbstu.antufievsemen.coursedatabase.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.spbstu.antufievsemen.coursedatabase.entity.Director;
import ru.spbstu.antufievsemen.coursedatabase.repository.DirectorRepository;

@Service
public class DirectorService {

  private final DirectorRepository directorRepository;

  public DirectorService(DirectorRepository directorRepository) {
    this.directorRepository = directorRepository;
  }

  public Director getDirector() {
    Optional<Director> optionalDirector = directorRepository.findById((long) 1);
    return optionalDirector.get();
  }

  public void updateDirector(Director director) {
    directorRepository.save(director);
  }
}
