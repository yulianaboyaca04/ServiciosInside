package com.inside.models.entities;

public class Suscription {

	private String idSusciption;
	private UserInside user;
	private EventInside event;
	
	public Suscription(String idSusciption, UserInside user, EventInside event) {
		this.idSusciption = idSusciption;
		this.user = user;
		this.event = event;
	}

	public String getIdSusciption() {
		return idSusciption;
	}

	public void setIdSusciption(String idSusciption) {
		this.idSusciption = idSusciption;
	}

	public UserInside getUser() {
		return user;
	}

	public void setUser(UserInside user) {
		this.user = user;
	}

	public EventInside getEvent() {
		return event;
	}

	public void setEvent(EventInside event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Suscription [idSusciption=" + idSusciption + ", user=" + user + ", event=" + event + "]";
	}

}
