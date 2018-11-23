package com.inside.models.entities;

public class Credentials {

	private String idCredential;
	private CredentialsType credentialsType;
	private String user;
	private String passwordOrToken;

	public Credentials() {
		// TODO Auto-generated constructor stub
	}

	public Credentials(String idCredential, CredentialsType credentialsType, String user, String passwordOrToken) {
		this.idCredential = idCredential;
		this.credentialsType = credentialsType;
		this.user = user;
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswordOrToken() {
		return passwordOrToken;
	}

	public void setPasswordOrToken(String passwordOrToken) {
		this.passwordOrToken = passwordOrToken;
	}

	@Override
	public String toString() {
		return "Credentials [idCredential=" + idCredential + ", credentialsType=" + credentialsType + ", user=" + user
				+ ", passwordOrToken=" + passwordOrToken + "]";
	}

}
