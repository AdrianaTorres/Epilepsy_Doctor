package mainMethodDoctor;

public class UserProfile {
	private String name;
	private String surname;
	//private String userName;
	
	public UserProfile (String name, String surname) {
		this.name = name;
		this.surname = surname;
		//this.userName = userName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	/*public String getUserName () {
		return this.userName();
	}*/
}
