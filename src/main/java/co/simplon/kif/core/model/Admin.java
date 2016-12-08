package co.simplon.kif.core.model;

public class Admin extends User {

	public Admin(String username, String password, String email, User.Role role) {
		
		// TODO Auto-generated constructor stub
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String username, String password, Role role, Boolean enabled) {
		super(username, password, role, enabled);
		// TODO Auto-generated constructor stub
	}

}
