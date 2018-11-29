package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inside.persistence.DataBaseAcces;

public class User {

	@JsonProperty("idUser")
	private String idUser;
	@JsonProperty("credential")
	private Credentials credential;
	@JsonProperty("image")
	private Image image;
	@JsonProperty("nameUser")
	private String nameUser;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("birthDate")
	private Date birthDate;
	@JsonProperty("nickname")
	private String nickname;
	@JsonProperty("userInteres")
	private ArrayList<Interest> userInteres;

	// -------------------------------Constructors------------------------------------------------------------------
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String idUser, Credentials credential, Image image, String nameUser, String lastName, Date birthDate,
			String nickname, ArrayList<Interest> userInteres) {
		this.idUser = idUser;
		this.credential = credential;
		this.image = image;
		this.nameUser = nameUser;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.nickname = nickname;
		this.userInteres = new ArrayList<Interest>();
		for (Interest interest : userInteres) {
			this.userInteres.add(interest);
		}
	}

	// -----------------------------inserts-en-bd-----------------------------------------------------------------
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("INSERT INTO USERS VALUES(?,?,?,?,?,?,?)");
		preparedStatement.setString(1, this.idUser);
		preparedStatement.setString(2, this.credential.getIdCredential());
		preparedStatement.setString(3, this.image.getIdImage());
		preparedStatement.setString(4, this.nameUser);
		preparedStatement.setString(5, this.lastName);
		preparedStatement.setDate(6, birthDate);
		preparedStatement.setString(7, this.nickname);
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
	
	// ----------------------------querys-en-bd---------------------------------------------------------------------
	public static ArrayList<User> listAllUsers() throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		ArrayList<String> idUsers = new ArrayList<>();
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement().executeQuery("SELECT * FROM USERS");
		while (resultSet.next()) {
			idUsers.add(resultSet.getString(1));
		}
		for (String idEvent : idUsers) {
			User user = searchUserIntoDatabase(idEvent);
			users.add(user);
		}
		return users;
	}

	public static User searchUserIntoDatabase(String codigo) throws SQLException {
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM USERS WHERE ID_USER='" + codigo + "'");
		User userInside = new User();
		while (resultSet.next()) {
			userInside.idUser = resultSet.getString(1);
			userInside.nameUser = resultSet.getString(4);
			userInside.lastName = resultSet.getString(5);
			userInside.birthDate = resultSet.getDate(6);
			userInside.nickname = resultSet.getString(7);
			String idCredential = resultSet.getString(2);
			String idImage = resultSet.getString(3);
			userInside.credential = Credentials.searchUserIntoDatabase(idCredential, resultSet);
			userInside.image = Image.searchUserIntoDatabase(idImage);
			userInside.userInteres = searchInterestsIntoDatabase(userInside.idUser);
			break;
		}

		return userInside;
	}

	public static ArrayList<Interest> searchInterestsIntoDatabase(String idUser) throws SQLException {
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM USER_INTERESTS WHERE ID_USER='" + idUser + "'");
		ArrayList<Interest> interests = new ArrayList<>();
		ArrayList<String> idInterests = new ArrayList<>();
		while (resultSet.next()) {
			idInterests.add(resultSet.getString(2));
		}
		for (String string : idInterests) {
			Interest interest = Interest.searchInterestIntoDatabase(string, resultSet);
			interests.add(interest);
		}
		return interests;
	}
	
	// -----------------------------------Remove-en-bd--------------------------------------------------
	public void removeFromDatabase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("DELETE FROM CREDENTIALS WHERE ID_CREDENTIAL=" + this.credential.getIdCredential());
		preparedStatement.execute();
		preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("DELETE FROM IMAGES WHERE ID_IMAGE=" + this.image.getIdImage());
		preparedStatement.execute();
		preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("DELETE FROM USERS WHERE USERS.ID_USER = " + this.idUser);
		preparedStatement.execute();
	}
	
	// ------------------------------------Editar-en-bd---------------------------------------------------
	public void edit(User userEdited) throws SQLException {
		PreparedStatement preparedStatement;
		if (!this.credential.equals(userEdited.getCredential())) {
			this.credential = userEdited.getCredential();
			// Actualiza la tabla CREDENTIALS
			preparedStatement = DataBaseAcces.getInstance().getConnection()
					.prepareStatement("UPDATE CREDENTIALS SET " + "USER_NAME='" + this.credential.getUserName() + "', "
							+ "PASSWORD_OR_TOKEN='" + this.credential.getPasswordOrToken() + "' "
							+ "WHERE ID_CREDENTIAL='" + this.credential.getIdCredential() + "'");
			preparedStatement.executeUpdate();
		}
		if (!this.image.equals(userEdited.getImage())) {
			this.image = userEdited.getImage();
			// Actualiza la tabla IMAGES
			preparedStatement = DataBaseAcces.getInstance().getConnection()
					.prepareStatement("UPDATE IMAGES SET " + "CONTENT='" + this.image.getContent() + "' "
							+ "WHERE ID_IMAGE='" + this.image.getIdImage() + "'");
			preparedStatement.executeUpdate();
		}

		if ((!this.nameUser.equals(userEdited.getNameUser())) || (!this.lastName.equals(userEdited.getLastName()))
				|| (!this.birthDate.equals(userEdited.getBirthDate()))
				|| (!this.nickname.equals(userEdited.getNickname()))) {
			this.nameUser = userEdited.getNameUser();
			this.lastName = userEdited.getLastName();
			/* this.birthDate = userEdited.getBirthDate(); */ // TODO
			this.nickname = userEdited.getNickname();
			// Actualiza la tabla USERS
			preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement(
					"UPDATE USERS SET " + "NAME_USER='" + this.nameUser + "', " + "LAST_NAME='" + this.lastName + "', "
					/* + "BIRTH_DATE='" + this.birthDate + "', " */
							+ "NICKNAME='" + this.nickname + "' " + "WHERE ID_USER='" + this.idUser + "'");
			preparedStatement.executeUpdate();
		}
		if (!this.userInteres.equals(userEdited.getUserInteres())) {
			this.userInteres = userEdited.getUserInteres();
			// TODO

		}
	}

	// ---------------------------Getters&Setters-------------------------------------------------------
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nikename) {
		this.nickname = nikename;
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
				+ nameUser + ", lastName=" + lastName + ", birthDate=" + birthDate + ", nickname=" + nickname
				+ ", userInteres=" + userInteres + "]";
	}
}