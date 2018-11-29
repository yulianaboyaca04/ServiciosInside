package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.inside.persistence.DataBaseAcces;

public class ViewsHistory {

	private User user;
	private Event event;
	private Timestamp viewDate;
	
	public ViewsHistory() {
		// TODO Auto-generated constructor stub
	}

	public ViewsHistory(User user, Event event) {
		super();
		this.user = user;
		this.event = event;
		this.viewDate = new Timestamp(System.currentTimeMillis());
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

	public Date getViewDate() {
		return viewDate;
	}

	public void setViewDate(Timestamp viewDate) {
		this.viewDate = viewDate;
	}

	@Override
	public String toString() {
		return "ViewsHistory [user=" + user + ", event=" + event + ", viewDate=" + viewDate + "]";
	}
	
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO VIEWS_HISTORY VALUES(?,?,?)");
		preparedStatement.setString(1, this.user.getIdUser());
		preparedStatement.setString(2, this.event.getIdEvent());
		preparedStatement.setTimestamp(2, this.viewDate);
		preparedStatement.execute();
	}

}
