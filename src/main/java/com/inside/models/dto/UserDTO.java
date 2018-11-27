package com.inside.models.dto;

import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inside.models.entities.Credentials;
import com.inside.models.entities.Image;
import com.inside.models.entities.UserInside;

public class UserDTO {

	@JsonProperty("idUser")
	private String idUser;
	@JsonProperty("credential")
	private String credential;
	@JsonProperty("image")
	private String image;
	@JsonProperty("nameUser")
	private String nameUser;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("birthDate")
	private String birthDate;
	@JsonProperty("nickname")
	private String nickname;

	public UserDTO(String idUser, String credential, String image, String nameUser, String lastName, String birthDate,
			String nickname) {
		this.idUser = idUser;
		this.credential = credential;
		this.image = image;
		this.nameUser = nameUser;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.nickname = nickname;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "UserDTO [idUser=" + idUser + ", credential=" + credential + ", image=" + image + ", nameUser="
				+ nameUser + ", lastName=" + lastName + ", birthDate=" + birthDate + ", nickname=" + nickname + "]";
	}

	public UserInside generateEvent() throws SQLException {
		Credentials credential = Credentials.searchUserIntoDatabase(this.credential, null);
		Image image = Image.searchUserIntoDatabase(this.image);
		UserInside user =  new UserInside(idUser, credential, image, nameUser, lastName, null, nickname, null);
		user.insertIntoDataBase();
		return user;
	}
	
	

}
