package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inside.persistence.DataBaseAcces;

/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar
 * 
 */
public class User {

	@JsonProperty("idUser")
	private String idUser;
	@JsonProperty("nameUser")
	private String nameUser;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("birthDate")
	private String birthDate;
	@JsonProperty("email")
	private String email;
	@JsonProperty("password")
	private String password;
	@JsonProperty("nickname")
	private String nickname;

	public User(String idUser, String nameUser, String lastName, String birthDate, String email, String password,
			String nickname) {
		this.idUser = idUser;
		this.nameUser = nameUser;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", nameUser=" + nameUser + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", email=" + email + ", password=" + password + ", nickname=" + nickname + "]";
	}
}