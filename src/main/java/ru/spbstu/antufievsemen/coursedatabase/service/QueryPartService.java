package ru.spbstu.antufievsemen.coursedatabase.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.spbstu.antufievsemen.coursedatabase.entity.QueryPart;
import ru.spbstu.antufievsemen.coursedatabase.exception.QueryPartNotFoundException;
import ru.spbstu.antufievsemen.coursedatabase.repository.QueryPartRepository;

@Service
public class QueryPartService {

  private final QueryPartRepository queryPartRepository;

  public QueryPartService(QueryPartRepository queryPartRepository) {
    this.queryPartRepository = queryPartRepository;
  }

  public List<QueryPart> listQueryParts() {
    return (List<QueryPart>) queryPartRepository.findAll();
  }

  public QueryPart findQueryPartById(long id) {
    Optional<QueryPart> optionalQueryPart = queryPartRepository.findById(id);
    if (optionalQueryPart.isPresent()) {
      return optionalQueryPart.get();
    }
    throw new QueryPartNotFoundException("QueryPart with id = " + id + "does not exist");
  }

  public boolean addQueryPart(QueryPart queryPart) {
    if (!queryPartRepository.existsById(queryPart.getId())) {
      queryPartRepository.save(queryPart);
      return true;
    }
    return false;
  }

  public boolean updateQueryPart(QueryPart queryPart) {
    if (queryPartRepository.existsById(queryPart.getId())) {
      queryPartRepository.save(queryPart);
      return true;
    }
    return false;
  }

  public QueryPart deleteQueryPartById(long id) {
    Optional<QueryPart> queryPart = queryPartRepository.findById(id);
    queryPart.ifPresent(value -> queryPartRepository.deleteById(value.getId()));
    return queryPart.get();
  }
}
