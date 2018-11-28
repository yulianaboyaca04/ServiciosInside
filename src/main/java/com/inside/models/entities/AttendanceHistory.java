package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class AttendanceHistory {

	private User user;
	private Event event;
	
	public AttendanceHistory() {
		// TODO Auto-generated constructor stub
	}
	
	public AttendanceHistory(User user, Event event) {
		this.user = user;
		this.event = event;
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
		return "AttendanceHistory [user=" + user + ", event=" + event + "]";
	}
	
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO ATTENDANCE_HISTORY VALUES(?,?)");
		preparedStatement.setString(1, this.user.getIdUser());
		preparedStatement.setString(2, this.event.getIdEvent());
		preparedStatement.execute();
	}
}
