package ru.spbstu.antufievsemen.coursedatabase.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.spbstu.antufievsemen.coursedatabase.entity.GradeBeer;
import ru.spbstu.antufievsemen.coursedatabase.exception.GradeBeerNotFoundException;
import ru.spbstu.antufievsemen.coursedatabase.repository.GradeBeerRepository;

@Service
public class GradeBeerService {

  private final GradeBeerRepository gradeBeerRepository;

  public GradeBeerService(GradeBeerRepository gradeBeerRepository) {
    this.gradeBeerRepository = gradeBeerRepository;
  }

  public List<GradeBeer> listGradeBeers() {
    return (List<GradeBeer>) gradeBeerRepository.findAll();
  }

  public GradeBeer findGradeBeerById(long id) {
    Optional<GradeBeer> optionalGradeBeer = gradeBeerRepository.findById(id);
    if (optionalGradeBeer.isPresent()) {
      return optionalGradeBeer.get();
    }
    throw new GradeBeerNotFoundException("QueryPart with id = " + id + "does not exist");
  }

  public boolean addGradeBeer(GradeBeer gradeBeer) {
    if (!gradeBeerRepository.existsById(gradeBeer.getId())) {
      gradeBeerRepository.save(gradeBeer);
      return true;
    }
    return false;
  }

  public boolean updateGradeBeer(GradeBeer gradeBeer) {
    if (gradeBeerRepository.existsById(gradeBeer.getId())) {
      gradeBeerRepository.save(gradeBeer);
      return true;
    }
    return false;
  }

  public GradeBeer deleteGradeBeerById(long id) {
    Optional<GradeBeer> gradeBeer = gradeBeerRepository.findById(id);
    gradeBeer.ifPresent(value -> gradeBeerRepository.deleteById(value.getId()));
    return gradeBeer.get();
  }
}
