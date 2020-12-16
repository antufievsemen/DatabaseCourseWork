package ru.spbstu.antufievsemen.coursedatabase.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.spbstu.antufievsemen.coursedatabase.entity.Resource;
import ru.spbstu.antufievsemen.coursedatabase.exception.ResourceNotFoundException;
import ru.spbstu.antufievsemen.coursedatabase.repository.StoreRepository;

@Service
public class ResourceService {

  private final StoreRepository storeRepository;

  public ResourceService(StoreRepository storeRepository) {
    this.storeRepository = storeRepository;
  }

  public List<Resource> listResources() {
    return (List<Resource>) storeRepository.findAll();
  }

  public Resource findResourceById(long id) {
    Optional<Resource> optionalResource = storeRepository.findById(id);
    if (optionalResource.isPresent()) {
      return optionalResource.get();
    }
    throw new ResourceNotFoundException("Resource with id = " + id + "does not exist");
  }

  public boolean addResources(Resource resource) {
    if (!storeRepository.existsById(resource.getId())) {
      storeRepository.save(resource);
      return true;
    }
    return false;
  }

  public boolean updateResource(Resource resource) {
    if (storeRepository.existsById(resource.getId())) {
      storeRepository.save(resource);
      return true;
    }
    return false;
  }

  public Resource deleteResourceById(long id) {
    Optional<Resource> resource = storeRepository.findById(id);
    resource.ifPresent(value -> storeRepository.deleteById(value.getId()));
    return resource.get();
  }
}
