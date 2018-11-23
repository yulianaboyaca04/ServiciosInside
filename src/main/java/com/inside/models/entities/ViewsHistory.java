package com.inside.models.entities;

import java.util.Date;

import javax.swing.text.View;

public class ViewsHistory {

	private UserInside user;
	private EventInside event;
	private Date viewDate;
	
	public ViewsHistory() {
		// TODO Auto-generated constructor stub
	}

	public ViewsHistory(UserInside user, EventInside event, Date viewDate) {
		super();
		this.user = user;
		this.event = event;
		this.viewDate = viewDate;
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

	public Date getViewDate() {
		return viewDate;
	}

	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

	@Override
	public String toString() {
		return "ViewsHistory [user=" + user + ", event=" + event + ", viewDate=" + viewDate + "]";
	}

}
