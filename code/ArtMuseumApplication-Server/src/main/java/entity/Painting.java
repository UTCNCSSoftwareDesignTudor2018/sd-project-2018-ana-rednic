package entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="painting")
public class Painting implements Serializable {

	private static final long serialVersionUID = 2477330562989680716L;
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int painting_id;
	@Column(name="image")
	private byte[] image;	
	@Column(name="title")
	private String title;
	@Column(name="author")
	private String author;
	@Column(name="description")
	private String description;
	@Column(name="qrcode")
	private byte[] qrcode; // QR CODE IMAGE
	
	@ManyToMany(mappedBy = "listOfPaintings",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Collection> listOfCollections = new LinkedList<Collection>();

	public List<Collection> getListOfCollections() {
		return listOfCollections;
	}
	public void setListOfCollections(List<Collection> listOfCollections) {
		this.listOfCollections = listOfCollections;
	}
	public Painting() {
	}
	public int getPainting_id() {
		return painting_id;
	}
	public void setPainting_id(int painting_id) {
		this.painting_id = painting_id;
	}
	public byte[] getQrcode() {
		return qrcode;
	}
	public void setQrcode(byte[] qrcode) {
		this.qrcode = qrcode;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] bFile) {
		this.image = bFile;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
