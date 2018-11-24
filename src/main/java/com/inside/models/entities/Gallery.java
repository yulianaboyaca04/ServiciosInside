package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class Gallery {

	private String idGallery;
	private EventInside event;
	private Image image;
	
	public Gallery() {
		// TODO Auto-generated constructor stub
	}
	
	public Gallery(String idGallery, EventInside event, Image image) {
		this.idGallery = idGallery;
		this.event = event;
		this.image = image;
	}

	public String getIdGallery() {
		return idGallery;
	}


	public void setIdGallery(String idGallery) {
		this.idGallery = idGallery;
	}


	public EventInside getEvent() {
		return event;
	}


	public void setEvent(EventInside event) {
		this.event = event;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Gallery [idGallery=" + idGallery + ", event=" + event + ", image=" + image + "]";
	}
	
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO GALLERY VALUES(?,?,?)");
		preparedStatement.setString(1, this.idGallery);
		preparedStatement.setString(2, this.event.getIdEvent());
		preparedStatement.setString(3, this.image.getIdImage());
		preparedStatement.execute();
	}
}
