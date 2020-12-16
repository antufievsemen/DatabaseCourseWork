package ru.spbstu.antufievsemen.coursedatabase.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private long id;

  private String username;

  private String password;

  @ManyToMany
  private Set<Role> roles;

  public User(String username, String password, Set<Role> role) {
    this.username = username;
    this.password = password;
    this.roles = role;
  }

  public User() {
  }

  public long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String login) {
    this.username = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoleSet() {
    return roles;
  }

  public void setRoleSet(Set<Role> role) {
    this.roles = role;
  }

  @Override
  public String toString() {
    return "User{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
