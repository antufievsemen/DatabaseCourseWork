package ru.spbstu.antufievsemen.coursedatabase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BeerGrades")
public class GradeBeer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private long id;

  private String name;

  private int cost;

  private String description;

  public GradeBeer(String name, int cost, String description) {
    this.name = name;
    this.cost = cost;
    this.description = description;
  }

  public GradeBeer() {}

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getIdAndName() {
    return id +  ". " + name;
  }

  @Override
  public String toString() {
    return "GradeBeer{" + '\n' +
            "id=" + id + '\n' +
            ", name='" + name + '\n' +
            ", cost=" + cost + '\n' +
            ", description='" + description + '\n' +
            '}';
  }
}
