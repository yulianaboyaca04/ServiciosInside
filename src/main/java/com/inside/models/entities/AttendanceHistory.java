package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class AttendanceHistory {

	private UserInside user;
	private EventInside event;
	
	public AttendanceHistory() {
		// TODO Auto-generated constructor stub
	}
	
	public AttendanceHistory(UserInside user, EventInside event) {
		this.user = user;
		this.event = event;
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
		return "AttendanceHistory [user=" + user + ", event=" + event + "]";
	}
	
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO ATTENDANCE_HISTORY VALUES(?,?)");
		preparedStatement.setString(1, this.user.getIdUser());
		preparedStatement.setString(2, this.event.getIdEvent());
		preparedStatement.execute();
	}
}
