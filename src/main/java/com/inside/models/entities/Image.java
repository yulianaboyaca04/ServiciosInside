package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class Image {

	private String idImage;
	private String content;
	
	public Image() {
		// TODO Auto-generated constructor stub
	}
	
	public Image(String idImage, String content) {
		this.idImage = idImage;
		this.content = content;
	}
	public String getIdImage() {
		return idImage;
	}
	public void setIdImage(String idImage) {
		this.idImage = idImage;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Image [idImage=" + idImage + ", content=" + content + "]";
	}
	
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO IMAGES VALUES(?,?)");
		preparedStatement.setString(1, this.idImage);
		preparedStatement.setString(2, this.content);
		preparedStatement.execute();
	}
	
}
