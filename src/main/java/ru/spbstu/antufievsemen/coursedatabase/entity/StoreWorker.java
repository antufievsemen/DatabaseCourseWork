package ru.spbstu.antufievsemen.coursedatabase.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "StoreWorkers")
public class StoreWorker {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private long id;

  private String name;

  private String secondName;

  private int salary;

  private String workExperience;

  private long phone;

  private String passportNumber;

  private int completedOrders;

  @OneToOne(cascade = CascadeType.ALL)
  private User user;

  public StoreWorker(String name, String secondName, int salary, String workExperience, long phone, String passportNumber, int completedOrders, User idUser) {
    this.name = name;
    this.secondName = secondName;
    this.salary = salary;
    this.workExperience = workExperience;
    this.phone = phone;
    this.passportNumber = passportNumber;
    this.completedOrders = completedOrders;
    this.user = idUser;
  }

  public StoreWorker() {
  }

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

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public String getWorkExperience() {
    return workExperience;
  }

  public void setWorkExperience(String workExperience) {
    this.workExperience = workExperience;
  }

  public long getPhone() {
    return phone;
  }

  public void setPhone(long phone) {
    this.phone = phone;
  }

  public String getPassportNumber() {
    return passportNumber;
  }

  public void setPassportNumber(String passportNumber) {
    this.passportNumber = passportNumber;
  }

  public int getCompletedOrders() {
    return completedOrders;
  }

  public void setCompletedOrders(int completedOrders) {
    this.completedOrders = completedOrders;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User idUser) {
    this.user = idUser;
  }

  public String getFullName() {
    return name + secondName;
  }

  public String fullInfo() {
    return "StoreWorker{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", secondName='" + secondName + '\'' +
            ", salary=" + salary +
            ", workExperience='" + workExperience + '\'' +
            ", phone=" + phone +
            ", passportNumber='" + passportNumber + '\'' +
            ", completedOrders=" + completedOrders +
            ", user=" + user +
            '}';
  }

  public String info() {
    return "StoreWorker{" + '\n' +
            "name='" + name + '\n' +
            ", secondName='" + secondName + '\n' +
            ", workExperience='" + workExperience + '\n' +
            ", phone=" + phone + '\n' +
            ", passportNumber='" + passportNumber + '\n' +
            ", completedOrders=" + completedOrders + '\n' +
            '}';
  }

}
