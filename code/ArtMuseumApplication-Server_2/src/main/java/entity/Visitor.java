package entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="visitor")
public class Visitor {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    public int id;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private String age;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@OneToMany(mappedBy="visitor",cascade=CascadeType.ALL)
	private List<Collection> listOfCollections = new LinkedList<Collection>();
	public Visitor() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setListOfArticles(List<Collection> listOfCollections) {
		this.listOfCollections = listOfCollections;
	}
	public int getId() {
		return id;
	}
	public String toString(){
	      return "Writer [ name: "+name+", username: "+ username+ ", password: "+password+" ]";
	   }
}
