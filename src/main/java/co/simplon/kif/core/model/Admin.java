package co.simplon.kif.core.model;

public class Admin extends User {

	public Admin() {
		super();
	}

	public Admin(String username, String password, String email,Role role) {
		super(username, password, email,role);
	}
	
}
