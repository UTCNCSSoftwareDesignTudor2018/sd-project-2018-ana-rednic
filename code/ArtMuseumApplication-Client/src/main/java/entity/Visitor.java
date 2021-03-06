package entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Visitor implements Serializable {

	private static final long serialVersionUID = 7357414341849009573L;
	public int id;
	private String name;
	private String age;
	private String username;
	private String password;
	private List<Collection> listOfCollections = new LinkedList<Collection>();
	public Visitor() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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
	public List<Collection> getListOfCollections() {
		return listOfCollections;
	}
	public void setListOfCollections(List<Collection> listOfCollections) {
		this.listOfCollections = listOfCollections;
	}
	
}
