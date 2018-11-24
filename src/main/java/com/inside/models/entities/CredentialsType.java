package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class CredentialsType {
	
	private String idCredentials;
	private String name;
	
	public CredentialsType() {
		// TODO Auto-generated constructor stub
	}
	
	public CredentialsType(String idCredentials, String name) {
		this.idCredentials = idCredentials;
		this.name = name;
	}

	public String getIdCredentials() {
		return idCredentials;
	}

	public void setIdCredentials(String idCredentials) {
		this.idCredentials = idCredentials;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CredentialsType [idCredentials=" + idCredentials + ", name=" + name + "]";
	}	
	
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO CREDENTIALS_TYPE VALUES(?,?)");
		preparedStatement.setString(1, this.idCredentials);
		preparedStatement.setString(2, this.name);
		preparedStatement.execute();
	}
}
