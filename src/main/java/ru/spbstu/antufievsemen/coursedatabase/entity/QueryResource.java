package ru.spbstu.antufievsemen.coursedatabase.entity;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "QueryResources")
public class QueryResource {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private long id;

  @ManyToOne
  private FactoryWorker factoryWorker;

  @ManyToOne
  private StoreWorker storeWorker;

  @ManyToOne(cascade = CascadeType.REFRESH)
  private Resource resource;

  private int countNeeded;

  private Date dateBegin;

  private int priority;

  public QueryResource(FactoryWorker idFactoryWorker, StoreWorker storeWorker, Resource idResource, int countNeeded, Date dateBegin, int priority) {
    this.factoryWorker = idFactoryWorker;
    this.storeWorker = storeWorker;
    this.resource = idResource;
    this.countNeeded = countNeeded;
    this.dateBegin = dateBegin;
    this.priority = priority;
  }

  public QueryResource() {
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setDateBegin(Date dateBegin) {
    this.dateBegin = dateBegin;
  }

  public long getId() {
    return id;
  }

  public FactoryWorker getFactoryWorker() {
    return factoryWorker;
  }

  public void setFactoryWorker(FactoryWorker idFactoryWorker) {
    this.factoryWorker = idFactoryWorker;
  }

  public StoreWorker getStoreWorker() {
    return storeWorker;
  }

  public void setStoreWorker(StoreWorker storeWorker) {
    this.storeWorker = storeWorker;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }

  public Resource getResource() {
    return resource;
  }

  public int getCountNeeded() {
    return countNeeded;
  }

  public void setCountNeeded(int countNeeded) {
    this.countNeeded = countNeeded;
  }

  public Date getDateBegin() {
    return dateBegin;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("QueryPart{");
    stringBuilder.append(" id = ")
            .append(id);
    if (factoryWorker == null) {
      stringBuilder.append(" factory worker doesnt exist");
    } else {
      stringBuilder.append(" factory worker")
              .append(factoryWorker.info());
    }
    if (storeWorker == null) {
      stringBuilder.append(" store worker doesnt exist");
    } else {
      stringBuilder.append(" store worker =")
              .append(storeWorker.info());
    }
    stringBuilder.append(" resource = ")
            .append(resource.toString())
            .append(" countNeeded = ")
            .append(countNeeded)
            .append(" date begin = ")
            .append(dateBegin)
            .append(" priority = ")
            .append(priority)
            .append(" }");
    return stringBuilder.toString();
  }
}
