package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class Credentials {

	private String idCredential;
	private CredentialsType credentialsType;
	private String userName;
	private String passwordOrToken;

	public Credentials() {
		// TODO Auto-generated constructor stub
	}

	public Credentials(String idCredential, CredentialsType credentialsType, String user, String passwordOrToken) {
		this.idCredential = idCredential;
		this.credentialsType = credentialsType;
		this.userName = user;
		this.passwordOrToken = passwordOrToken;
	}

	public String getIdCredential() {
		return idCredential;
	}

	public void setIdCredential(String idCredential) {
		this.idCredential = idCredential;
	}

	public CredentialsType getCredentialsType() {
		return credentialsType;
	}

	public void setCredentialsType(CredentialsType credentialsType) {
		this.credentialsType = credentialsType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String user) {
		this.userName = user;
	}

	public String getPasswordOrToken() {
		return passwordOrToken;
	}

	public void setPasswordOrToken(String passwordOrToken) {
		this.passwordOrToken = passwordOrToken;
	}

	@Override
	public String toString() {
		return "Credentials [idCredential=" + idCredential + ", credentialsType=" + credentialsType + ", user=" + userName
				+ ", passwordOrToken=" + passwordOrToken + "]";
	}

	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO CREDENTIALS VALUES(?,?,?,?)");
		preparedStatement.setString(1, this.idCredential);
		preparedStatement.setString(2, this.credentialsType.getIdCredentials());
		preparedStatement.setString(3, this.userName);
		preparedStatement.setString(4, this.passwordOrToken);
		preparedStatement.execute();
	}
	
	public static Credentials searchUserIntoDatabase(String codigo, ResultSet resultSet2) throws SQLException {
		resultSet2.close();
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM CREDENTIALS WHERE ID_CREDENTIAL='" + codigo + "'");
		Credentials credentials = new Credentials();
		while (resultSet.next()) {
			credentials.idCredential = resultSet.getString(1);
			credentials.credentialsType = CredentialsType.searchCredentialsTypeIntoDatabase(resultSet.getString(2));
			credentials.userName = resultSet.getString(3);
			credentials.passwordOrToken = resultSet.getString(4);
			break;
		}

		return credentials;
	}
}
