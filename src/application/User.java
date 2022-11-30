package application;

public class User {

	private String id;
	private String ingID;
	private int admin;

	public User(String id, String ingID, int admin) {
		this.id = id;
		this.ingID = ingID;
		this.admin = admin;
	}

	public String getID() {
		return id;
	}

	public String getIngID() {
		return ingID;
	}

	public int getAdmin() {
		return admin;
	}

}
