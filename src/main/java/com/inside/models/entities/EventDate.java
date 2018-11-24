package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.inside.persistence.DataBaseAcces;

public class EventDate {

	private String idDate;
	private Date dateStart;
	private Date dateFinish;

	public EventDate() {
		// TODO Auto-generated constructor stub
	}

	public EventDate(String idDate, Date dateStart, Date dateFinish) {
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

	@Override
	public String toString() {
		return "EventDate [idDate=" + idDate + ", dateStart=" + dateStart + ", dateFinish=" + dateFinish + "]";
	}

	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO DATES VALUES(?,?,?)");
		preparedStatement.setString(1, this.idDate);
		preparedStatement.setDate(2, null);
		preparedStatement.setDate(3, null);
		preparedStatement.execute();
	}
}
