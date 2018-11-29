package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.inside.exceptions.EventDoesntExists;
import com.inside.exceptions.UserDoesntExists;
import com.inside.models.dao.InsideManager;
import com.inside.persistence.DataBaseAcces;

public class Suscription {

	private User user;
	private Event event;
	
	public Suscription(User user, Event event) {
		this.user = user;
		this.event = event;
	}

	public Suscription() {
		// TODO Auto-generated constructor stub
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
}
