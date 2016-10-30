package co.simplon.kif.core.model;

import javax.persistence.*;

@Entity
public class User {
  public enum Role {
	 USER, ADMIN
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String username;
  private String password;
  private String email;
  
  @Enumerated(EnumType.STRING)
  private Role role;

  public User() {
    super();
  }

  public User(String username, String password, String email, Role role) {
    super();
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;
  }

  public Integer getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
