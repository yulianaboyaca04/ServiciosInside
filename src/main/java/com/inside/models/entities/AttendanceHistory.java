package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.inside.persistence.DataBaseAcces;

public class AttendanceHistory {

	private UserInside user;
	private EventInside event;
	private Date attendanceDate;
	
	public AttendanceHistory() {
		// TODO Auto-generated constructor stub
	}
	
	public AttendanceHistory(UserInside user, EventInside event, Date attendanceDate) {
		super();
		this.user = user;
		this.event = event;
		this.attendanceDate = attendanceDate;
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

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	@Override
	public String toString() {
		return "AttendanceHistory [user=" + user + ", event=" + event + ", attendanceDate=" + attendanceDate + "]";
	}
	
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO INTERESTS VALUES(?,?)");
		preparedStatement.setString(1, this.user.getIdUser());
	//	preparedStatement.setString(2,  this.());
		preparedStatement.execute();
	}
}
