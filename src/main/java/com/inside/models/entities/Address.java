package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class Address {

	private String idAddress;
	private float latitude;
	private float longitude;
	private String nameCity;
	private String namePlace;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(String idAddress, float latitude, float longitude, String nameCity, String namePlace) {
		this.idAddress = idAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.nameCity = nameCity;
		this.namePlace = namePlace;
	}

	public String getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(String idAddress) {
		this.idAddress = idAddress;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getNameCity() {
		return nameCity;
	}

	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}

	public String getNamePlace() {
		return namePlace;
	}

	public void setNamePlace(String namePlace) {
		this.namePlace = namePlace;
	}

	@Override
	public String toString() {
		return "Address [idAddress=" + idAddress + ", latitude=" + latitude + ", longitude=" + longitude + ", nameCity="
				+ nameCity + ", namePlace=" + namePlace + "]";
	}
	
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO ADDRESS VALUES(?,?,?,?,?)");
		preparedStatement.setString(1, this.idAddress);
		preparedStatement.setFloat(2, this.latitude);
		preparedStatement.setFloat(3, this.longitude);
		preparedStatement.setString(4, this.nameCity);
		preparedStatement.setString(5, this.namePlace);
		preparedStatement.execute();
	}
}
