package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.inside.persistence.DataBaseAcces;

public class UserInside {
	
	private String idUser;
	private Credentials credential;
	private Image image;
	private String nameUser;
	private String lastName;
	private Date birthDate;
	private String nickename;
	private ArrayList<Interest> userInteres;
	
	public UserInside(String idUser, Credentials credential, Image image, String nameUser, String lastName,
			Date birthDate, String nickename, ArrayList<Interest> userInteres) {
		this.idUser = idUser;
		this.credential = credential;
		this.image = image;
		this.nameUser = nameUser;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.nickename = nickename;
		this.userInteres = new ArrayList<Interest>();
		for (Interest interest : userInteres) {
			this.userInteres.add(interest);
		}
	}
	
	public UserInside() {
		// TODO Auto-generated constructor stub
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Credentials getCredential() {
		return credential;
	}

	public void setCredential(Credentials credential) {
		this.credential = credential;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNikename() {
		return nickename;
	}

	public void setNikename(String nikename) {
		this.nickename = nikename;
	}

	public ArrayList<Interest> getUserInteres() {
		return userInteres;
	}

	public void setUserInteres(ArrayList<Interest> userInteres) {
		this.userInteres = userInteres;
	}

	@Override
	public String toString() {
		return "UserInside [idUser=" + idUser + ", credential=" + credential + ", image=" + image + ", nameUser="
				+ nameUser + ", lastName=" + lastName + ", birthDate=" + birthDate + ", nikename=" + nickename
				+ ", userInteres=" + userInteres + "]";
	}
	
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO USERS VALUES(?,?,?,?,?,?,?)");
		preparedStatement.setString(1, this.idUser);
		preparedStatement.setString(2, this.credential.getIdCredential());
		preparedStatement.setString(3, this.image.getIdImage());
		preparedStatement.setString(4, this.nameUser);
		preparedStatement.setString(5, this.lastName);
		preparedStatement.setDate(6, null);
		preparedStatement.setString(7, this.nickename);
		preparedStatement.execute();
		for (int i = 0; i < this.userInteres.size(); i++) {
			insertUserInterestsIntoDatabase(this.userInteres.get(i));
		}
	}
	
	public void insertUserInterestsIntoDatabase(Interest interest) throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("INSERT INTO USER_INTERESTS VALUES(?,?)");
		preparedStatement.setString(1, this.idUser);
		preparedStatement.setString(2, interest.getIdInterest());
		preparedStatement.execute();
	}
	
}