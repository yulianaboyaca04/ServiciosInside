package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inside.persistence.DataBaseAcces;

public class EventDate {
	
	@JsonProperty("idDate")
	private String idDate;
	@JsonProperty("dateStart")
	private Timestamp dateStart;
	@JsonProperty("dateFinish")
	private Timestamp dateFinish;

	public EventDate() {
		// TODO Auto-generated constructor stub
	}

	public EventDate(String idDate, Timestamp dateStart, Timestamp dateFinish) {
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

	public Timestamp getDateStart() {
		return dateStart;
	}

	public void setDateStart(Timestamp dateStart) {
		this.dateStart = dateStart;
	}

	public Timestamp getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Timestamp dateFinish) {
		this.dateFinish = dateFinish;
	}

	@Override
	public String toString() {
		return "EventDate [idDate=" + idDate + ", dateStart=" + dateStart + ", dateFinish=" + dateFinish + "]";
	}

	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("INSERT INTO DATES VALUES(?,?,?)");
		preparedStatement.setString(1, this.idDate);
		preparedStatement.setTimestamp(2, this.dateStart);
		preparedStatement.setTimestamp(3, this.dateFinish);
		preparedStatement.execute();
	}

	public static EventDate searchEventDateIntoDatabase(String idEventDate, ResultSet resultSet) throws SQLException {
		resultSet.close();
		resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM DATES WHERE id_DATE='" + idEventDate + "'");
		EventDate eventDate = new EventDate();
		while (resultSet.next()) {
			eventDate.idDate = resultSet.getString(1);
			eventDate.dateStart = resultSet.getTimestamp(2);
			eventDate.dateFinish = resultSet.getTimestamp(3);
			break;
		}
		resultSet.close();
		return eventDate;
	}

}
