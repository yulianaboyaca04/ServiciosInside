package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.inside.persistence.DataBaseAcces;

public class EventInside {

	private String idEvent;
	private UserInside userCreator;
	private HowToBuy howToBuy;
	private Address address;
	private EventDate eventDate;
	private String nameEvent;
	private String descriptionEvent;
	private Gallery gallery;
	private ArrayList<Interest> eventInterests;
	private ArrayList<Regulation> regulations;

	public EventInside() {
		// TODO Auto-generated constructor stub
	}

	public EventInside(String idEvent, UserInside userCreator, HowToBuy howToBuy, Address address, EventDate eventDate,
			String nameEvent, String descriptionEvent, Gallery gallery, ArrayList<Interest> eventInterests,
			ArrayList<Regulation> regulations) {
		super();
		this.idEvent = idEvent;
		this.userCreator = userCreator;
		this.howToBuy = howToBuy;
		this.address = address;
		this.eventDate = eventDate;
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
		this.gallery = gallery;
		this.eventInterests = eventInterests;
		this.regulations = regulations;
	}

	public String getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}

	public UserInside getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(UserInside userCreator) {
		this.userCreator = userCreator;
	}

	public HowToBuy getHowToBuy() {
		return howToBuy;
	}

	public void setHowToBuy(HowToBuy howToBuy) {
		this.howToBuy = howToBuy;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EventDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(EventDate eventDate) {
		this.eventDate = eventDate;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public String getDescriptionEvent() {
		return descriptionEvent;
	}

	public void setDescriptionEvent(String descriptionEvent) {
		this.descriptionEvent = descriptionEvent;
	}

	public Gallery getGallery() {
		return gallery;
	}

	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}

	public ArrayList<Interest> getEventInterests() {
		return eventInterests;
	}

	public void setEventInterests(ArrayList<Interest> eventInterests) {
		this.eventInterests = eventInterests;
	}

	public ArrayList<Regulation> getRegulations() {
		return regulations;
	}

	public void setRegulations(ArrayList<Regulation> regulations) {
		this.regulations = regulations;
	}

	@Override
	public String toString() {
		return "EventInside [idEvent=" + idEvent + ", userCreator=" + userCreator + ", howToBuy=" + howToBuy
				+ ", address=" + address + ", eventDate=" + eventDate + ", nameEvent=" + nameEvent
				+ ", descriptionEvent=" + descriptionEvent + ", gallery=" + gallery + ", eventInterests="
				+ eventInterests + ", regulations=" + regulations + "]";
	}

	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("INSERT INTO EVENTS VALUES((?, ?, ?, ?, ?, ?, ?)");
		preparedStatement.setString(1, this.idEvent);
		preparedStatement.setString(2, this.userCreator.getIdUser());
		preparedStatement.setString(3, this.howToBuy.getIdHowToBuy());
		preparedStatement.setString(4, this.address.getIdAddress());
		preparedStatement.setString(5, this.eventDate.getIdDate());
		preparedStatement.setString(6, this.nameEvent);
		preparedStatement.setString(7, this.descriptionEvent);
		preparedStatement.setString(7, this.gallery.getIdGallery());
		preparedStatement.execute();
	}
}
