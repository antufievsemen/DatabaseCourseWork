package ru.spbstu.antufievsemen.coursedatabase.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.spbstu.antufievsemen.coursedatabase.entity.QueryResource;
import ru.spbstu.antufievsemen.coursedatabase.exception.QueryResourceNotFoundException;
import ru.spbstu.antufievsemen.coursedatabase.repository.QueryResourceRepository;

@Service
public class QueryResourceService {

  private QueryResourceRepository queryResourceRepository;

  public QueryResourceService(QueryResourceRepository queryResourceRepository) {
    this.queryResourceRepository = queryResourceRepository;
  }

  public List<QueryResource> listQueryResources() {
    return (List<QueryResource>) queryResourceRepository.findAll();
  }

  public QueryResource findQueryResourceById(long id) {
    Optional<QueryResource> optionalQueryResource = queryResourceRepository.findById(id);
    if (optionalQueryResource.isPresent()) {
      return optionalQueryResource.get();
    }
    throw new QueryResourceNotFoundException("QueryPart with id = " + id + "does not exist");
  }

  public boolean addQueryResource(QueryResource queryResource) {
    if (!queryResourceRepository.existsById(queryResource.getId())) {
      queryResourceRepository.save(queryResource);
      return true;
    }
    return false;
  }

  public boolean updateQueryResource(QueryResource queryResource) {
    if (queryResourceRepository.existsById(queryResource.getId())) {
      queryResourceRepository.save(queryResource);
      return true;
    }
    return false;
  }

  public QueryResource deleteQueryResourceById(long id) {
    Optional<QueryResource> queryResource = queryResourceRepository.findById(id);
    queryResource.ifPresent(value -> queryResourceRepository.deleteById(value.getId()));
    return queryResource.get();
  }
}
