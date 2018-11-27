package com.inside.models.dto;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inside.models.entities.Address;
import com.inside.models.entities.EventDate;
import com.inside.models.entities.EventInside;
import com.inside.models.entities.HowToBuy;
import com.inside.models.entities.Image;
import com.inside.models.entities.Interest;
import com.inside.models.entities.Rule;
import com.inside.models.entities.UserInside;

public class EventDTO {

	@JsonProperty("idEvent")
	private String idEvent;
	@JsonProperty("userCreator")
	private String userCreator;
	@JsonProperty("howToBuy")
	private String howToBuy;
	@JsonProperty("address")
	private String address;
	@JsonProperty("eventDate")
	private String eventDate;
	@JsonProperty("nameEvent")
	private String nameEvent;
	@JsonProperty("descriptionEvent")
	private String descriptionEvent;
	
	public EventDTO() {
		// TODO Auto-generated constructor stub
	}

	public EventDTO(String idEvent, String userCreator, String howToBuy, String address, String eventDate,
			String nameEvent, String descriptionEvent) {
		this.idEvent = idEvent;
		this.userCreator = userCreator;
		this.howToBuy = howToBuy;
		this.address = address;
		this.eventDate = eventDate;
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
	}

	public String getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}

	public String getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(String userCreator) {
		this.userCreator = userCreator;
	}

	public String getHowToBuy() {
		return howToBuy;
	}

	public void setHowToBuy(String howToBuy) {
		this.howToBuy = howToBuy;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
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

	@Override
	public String toString() {
		return "EventDTO [idEvent=" + idEvent + ", userCreator=" + userCreator + ", howToBuy=" + howToBuy + ", address="
				+ address + ", eventDate=" + eventDate + ", nameEvent=" + nameEvent + ", descriptionEvent="
				+ descriptionEvent + "]";
	}
	
	public EventInside generateEvent() throws SQLException {
		UserInside userCreator = UserInside.searchUserIntoDatabase(this.userCreator);
		HowToBuy howToBuy = HowToBuy.searchHowToBuyIntoDatabase(this.howToBuy, null);
		Address adress = Address.searchAddressIntoDatabase(this.address, null);
		EventDate eventDate = EventDate.searchEventDateIntoDatabase(this.eventDate, null);
		EventInside event = new EventInside(idEvent,userCreator, howToBuy, adress, eventDate, nameEvent, descriptionEvent, new ArrayList<Image>(), new ArrayList<Interest>(), new ArrayList<Rule>());
		event.insertIntoDataBase();	
		return event;
	}
	
	

}
