package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.inside.persistence.DataBaseAcces;

public class Suscription {

	private User user;
	private Event event;
	
	public Suscription(User user, Event event) {
		this.user = user;
		this.event = event;
	}

	public Suscription() {
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
		return "Suscription [user=" + user + ", event=" + event + "]";
	}

	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO SUSCRIPTION VALUES(?,?)");
		preparedStatement.setString(1, this.user.getIdUser());
		preparedStatement.setString(2, this.event.getIdEvent());
		preparedStatement.execute();
	}
	
	public void deleteFromDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("DELETE FROM SUSCRIPTION WHERE ID_USER='" + this.user.getIdUser() + "' AND ID_EVENT='" + this.event.getIdEvent() + "' ");
		preparedStatement.execute();
	}
}
