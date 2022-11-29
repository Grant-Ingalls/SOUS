package application;

public class User {
	
	private String id; 
	private String ingID;
	
	public User(String id, String ingID) {
		this.id = id; 
		this.ingID = ingID;
	}

	public String getID() {
		return id;
	}
	
	public String getIngID() {
		return ingID;
	}


}
