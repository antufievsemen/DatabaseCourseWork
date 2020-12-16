package ru.spbstu.antufievsemen.coursedatabase.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Director")
public class Director {

  @Id
  @Column(nullable = false, unique = true)
  private long id;

  private String name;

  private String secondName;

  @OneToOne(cascade = CascadeType.REMOVE)
  private User idUser;

  public Director(String name, String secondName, User idUser) {
    this.name = name;
    this.secondName = secondName;
    this.idUser = idUser;
  }

  public Director(){}

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public User getIdUser() {
    return idUser;
  }
}
