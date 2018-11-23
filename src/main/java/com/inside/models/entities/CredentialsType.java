package com.inside.models.entities;

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
}
