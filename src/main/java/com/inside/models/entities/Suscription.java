package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

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

	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO SUSCRIPTION VALUES(?,?,?)");
		preparedStatement.setString(1, this.idSusciption);
		preparedStatement.setString(2, this.user.getIdUser());
		preparedStatement.setString(3, this.event.getIdEvent());
		preparedStatement.execute();
	}
}
