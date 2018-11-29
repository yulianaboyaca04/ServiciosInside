package com.inside.models.entities;

import java.util.Date;

public class ViewsHistory {

	private User user;
	private Event event;
	private Date viewDate;
	
	public ViewsHistory() {
		// TODO Auto-generated constructor stub
	}

	public ViewsHistory(User user, Event event, Date viewDate) {
		super();
		this.user = user;
		this.event = event;
		this.viewDate = viewDate;
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

	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

	@Override
	public String toString() {
		return "ViewsHistory [user=" + user + ", event=" + event + ", viewDate=" + viewDate + "]";
	}

}
