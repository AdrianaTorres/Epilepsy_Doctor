package mainMethodDoctor;

public class UserProfilePatient {
	private String name;
	private String surname;
	private int age;
	private int weight;
	private char gender;
	
	public UserProfilePatient(String name, String surname, int weight, int age, char gender) {
		this.name=name;
		this.surname=surname;
		this.age=age;
		this.weight=weight;
		this.gender=gender;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public char getGender() {
		return gender;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
}
