package entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="collection")
public class Collection {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    public int collection_id;
	@Column(name="name")
	private String name;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "painting_collection", joinColumns = { @JoinColumn(name = "collection_id") }, inverseJoinColumns = { @JoinColumn(name = "painting_id") })
	private List<Painting> listOfPaintings = new LinkedList<Painting>();
	@ManyToOne
	private Visitor visitor;
	public int getCollection_id() {
		return collection_id;
	}
	public void setCollection_id(int collection_id) {
		this.collection_id = collection_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Painting> getListOfPaintings() {
		return listOfPaintings;
	}
	public void setListOfPaintings(List<Painting> listOfPaintings) {
		this.listOfPaintings = listOfPaintings;
	}
	public Visitor getVisitor() {
		return visitor;
	}
	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}
}
