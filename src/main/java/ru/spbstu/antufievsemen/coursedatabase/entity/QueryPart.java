package ru.spbstu.antufievsemen.coursedatabase.entity;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Query;
import javax.persistence.Table;

@Entity
@Table(name = "QueryParts")
public class QueryPart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private long id;

  @ManyToOne
  private FactoryWorker factoryWorker;

  @ManyToOne
  private Manager manager;

  @ManyToOne(cascade = CascadeType.REFRESH)
  private GradeBeer gradeBeer;

  private int countNeeded;

  private Date dateBegin;

  private int priority;

  public QueryPart(FactoryWorker factoryWorker, Manager idManager, GradeBeer idGradeBeer, int countNeeded, Date dateBegin, int priority) {
    this.factoryWorker = factoryWorker;
    this.manager = idManager;
    this.gradeBeer = idGradeBeer;
    this.countNeeded = countNeeded;
    this.dateBegin = dateBegin;
    this.priority = priority;
  }

  public QueryPart() {
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

  public GradeBeer getGradeBeer() {
    return gradeBeer;
  }

  public void setGradeBeer(GradeBeer gradeBeer) {
    this.gradeBeer = gradeBeer;
  }

  public Date getDateBegin() {
    return dateBegin;
  }

  public FactoryWorker getFactoryWorker() {
    return factoryWorker;
  }

  public void setFactoryWorker(FactoryWorker factoryWorker) {
    this.factoryWorker = factoryWorker;
  }

  public Manager getManager() {
    return manager;
  }

  public void setManager(Manager idManager) {
    this.manager = idManager;
  }

  public int getCountNeeded() {
    return countNeeded;
  }

  public void setCountNeeded(int countNeeded) {
    this.countNeeded = countNeeded;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(" QueryPart{");
    stringBuilder.append("id = ")
            .append(id);
    if (factoryWorker == null) {
      stringBuilder.append(" factory worker doesnt exist");
    } else {
      stringBuilder.append(" factory worker")
              .append(factoryWorker.info());
    }
    if (manager == null) {
      stringBuilder.append(" manager doesnt exist");
    } else {
      stringBuilder.append(" manager =")
              .append(manager.info());
    }
    stringBuilder.append(" gradeBeer = ")
            .append(gradeBeer.toString())
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
