package co.simplon.kif.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.gson.annotations.Expose;


@Entity
public class User {
	public enum Role {
		USER, ADMIN
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
  
	@Column(unique=true)
	@Expose
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	@Expose
	private Role role;
	@Expose
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
	
	public Boolean getEnabled() {
		return enabled;
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

  	public void setId(Integer id) {
  		this.id = id;
  	}

	public void setDisable() {
		this.enabled = false;
	}
}
