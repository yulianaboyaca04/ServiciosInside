package com.inside.models.entities;

import java.util.Date;

public class EventDate {

	private String idDate;
	private Date dateStart;
	private Date dateFinish;
	public EventDate(String idDate, Date dateStart, Date dateFinish) {
		super();
		this.idDate = idDate;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
	}
	public String getIdDate() {
		return idDate;
	}
	public void setIdDate(String idDate) {
		this.idDate = idDate;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateFinish() {
		return dateFinish;
	}
	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}
	
	
}
