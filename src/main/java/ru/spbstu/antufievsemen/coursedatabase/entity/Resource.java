package ru.spbstu.antufievsemen.coursedatabase.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Store")
public class Resource {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private long id;

  private String name;

  private int count;

  private int cost;

  private Date lastSupply;

  public Resource(String name, int count, int cost, Date lastSupply) {
    this.name = name;
    this.count = count;
    this.cost = cost;
    this.lastSupply = lastSupply;
  }

  public Resource(){}

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

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public Date getLastSupply() {
    return lastSupply;
  }

  public void setLastSupply(Date lastSupply) {
    this.lastSupply = lastSupply;
  }

  public String getIdAndName() {
    return id + ". " + name;
  }

  @Override
  public String toString() {
    return "Resource{" + '\n' +
            "id=" + id + '\n' +
            ", name='" + name + '\n' +
            ", count=" + count + '\n' +
            ", cost=" + cost + '\n' +
            ", lastSupply=" + lastSupply + '\n' +
            '}';
  }
}
