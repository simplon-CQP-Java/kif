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
  
  @Column(unique=true)
  private String username;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role role;
  private Boolean enabled;

  public User() {
    super();
  }

  public User(String username, String password, Role role, Boolean enabled) {
    super();
    this.username = username;
    this.password = password;
    this.role = role;
    this.enabled = enabled;
  }

  public Boolean isEnabled() {
	return enabled;
  }

  public void setEnabled(Boolean enabled) {
	this.enabled = enabled;
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
