package entity;

import java.util.LinkedList;
import java.util.List;

public class Collection {
	public int collection_id;
	private String name;
	private List<Painting> listOfPaintings = new LinkedList<Painting>();
	private Visitor visitor;
	
	public Collection() {
	}

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
