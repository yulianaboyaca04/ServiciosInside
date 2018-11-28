package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class Suscription {

	private String idSusciption;
	private User user;
	private Event event;
	
	public Suscription(String idSusciption, User user, Event event) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Suscription [idSusciption=" + idSusciption + ", user=" + user + ", event=" + event + "]";
	}

	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO SUSCRIPTION VALUES(?,?,?)");
		preparedStatement.setString(1, this.idSusciption);
		preparedStatement.setString(2, this.user.getIdUser());
		preparedStatement.setString(3, this.event.getIdEvent());
		preparedStatement.execute();
	}
}
