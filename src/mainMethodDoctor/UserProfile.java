package mainMethodDoctor;

public class UserProfile {
	private String name;
	private String macAddress;
	
	public UserProfile (String name) {
		this.name = name;
		this.macAddress = "";
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getMacAddress() {
		return this.macAddress;
	}
}
