package entity;

import java.io.Serializable;

public class Painting implements Serializable {

	private static final long serialVersionUID = 2477330562989680716L;
	public int painting_id;
	private byte[] image; // PAINTING IMAGE 
	private String title;
	private String author;
	private String description;
	private byte[] qrcode; // QR CODE IMAGE
	public Painting() {
	}
	public int getPainting_id() {
		return painting_id;
	}
	public void setPainting_id(int painting_id) {
		this.painting_id = painting_id;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
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
	public byte[] getQrcode() {
		return qrcode;
	}
	public void setQrcode(byte[] qrcode) {
		this.qrcode = qrcode;
	}
}
