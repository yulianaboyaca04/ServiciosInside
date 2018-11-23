package com.inside.models.entities;

public class Gallery {

	private String idGallery;
	private EventInside event;
	private Image image;
	
	public Gallery() {
		// TODO Auto-generated constructor stub
	}
	
	public Gallery(String idGallery, EventInside event, Image image) {
		super();
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
	
}
