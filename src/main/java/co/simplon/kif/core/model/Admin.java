package co.simplon.kif.core.model;

public class Admin extends User {

	public Admin() {
		super();
	}

	public Admin(String username, String password, String email) {
		super(username, password, email);
		this.role = User.Role.ADMIN;
	}
	
}
