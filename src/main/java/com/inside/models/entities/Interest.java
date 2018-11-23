package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class Interest {

	private String idInterest;
	private String nameInterests;

	public Interest() {
		// TODO Auto-generated constructor stub
	}

	public Interest(String idInterest, String nameInterests) {
		this.idInterest = idInterest;
		this.nameInterests = nameInterests;
	}

	public String getIdInterest() {
		return idInterest;
	}

	public void setIdInterest(String idInterest) {
		this.idInterest = idInterest;
	}

	public String getNameInterests() {
		return nameInterests;
	}

	public void setNameInterests(String nameInterests) {
		this.nameInterests = nameInterests;
	}

	@Override
	public String toString() {
		return "Interest [idInterest=" + idInterest + ", nameInterests=" + nameInterests + "]";
	}

	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("INSERT INTO INTERESTS VALUES(?,?)");
		preparedStatement.setString(1, this.idInterest);
		preparedStatement.setString(2, this.nameInterests);
		preparedStatement.execute();
	}
}
