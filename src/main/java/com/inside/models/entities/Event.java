package com.inside.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar
 *
 */

public class Event {

	@JsonProperty("idEvent")
	private String idEvent;
	@JsonProperty("idCity")
	private String idCity;
	@JsonProperty("idUserCreator")
	private String idUserCreator;
	@JsonProperty("nameEvent")
	private String nameEvent;
	@JsonProperty("coordinates")
	private String coordinates;
	@JsonProperty("capacity")
	private String capacity;
	@JsonProperty("dateStart")
	private String dateStart;
	@JsonProperty("dateFinish")
	private String dateFinish;
	@JsonProperty("descriptionEvent")
	private String descriptionEvent;
	@JsonProperty("rules")
	private String rules;
	@JsonProperty("price")
	private String price;
	@JsonProperty("howToBuy")
	private String howToBuy;

	public Event(String idEvent, String idCity, String idUserCreator, String nameEvent, String coordinates,
			String capacity, String dateStart, String dateFinish, String descriptionEvent, String rules, String price,
			String howToBuy) {
		super();
		this.idEvent = idEvent;
		this.idCity = idCity;
		this.idUserCreator = idUserCreator;
		this.nameEvent = nameEvent;
		this.coordinates = coordinates;
		this.capacity = capacity;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.descriptionEvent = descriptionEvent;
		this.rules = rules;
		this.price = price;
		this.howToBuy = howToBuy;
	}

	public String getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}

	public String getIdCity() {
		return idCity;
	}

	public void setIdCity(String idCity) {
		this.idCity = idCity;
	}

	public String getIdUserCreator() {
		return idUserCreator;
	}

	public void setIdUserCreator(String idUserCreator) {
		this.idUserCreator = idUserCreator;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(String dateFinish) {
		this.dateFinish = dateFinish;
	}

	public String getDescriptionEvent() {
		return descriptionEvent;
	}

	public void setDescriptionEvent(String descriptionEvent) {
		this.descriptionEvent = descriptionEvent;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getHowToBuy() {
		return howToBuy;
	}

	public void setHowToBuy(String howToBuy) {
		this.howToBuy = howToBuy;
	}

	@Override
	public String toString() {
		return "Event [idEvent=" + idEvent + ", idCity=" + idCity + ", idUserCreator=" + idUserCreator + ", nameEvent="
				+ nameEvent + ", coordinates=" + coordinates + ", capacity=" + capacity + ", dateStart=" + dateStart
				+ ", dateFinish=" + dateFinish + ", descriptionEvent=" + descriptionEvent + ", rules=" + rules
				+ ", price=" + price + ", howToBuy=" + howToBuy + "]";
	}
}