package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class HowToBuy {

	private String idHowToBuy;
	private String descriptionHowToBuy;
	private boolean inPresence;
	private float price;

	public HowToBuy() {
		// TODO Auto-generated constructor stub
	}

	public HowToBuy(String idHowToBuy, String descriptionHowToBuy, boolean inPresence, float price) {
		this.idHowToBuy = idHowToBuy;
		this.descriptionHowToBuy = descriptionHowToBuy;
		this.inPresence = inPresence;
		this.price = price;
	}

	public String getIdHowToBuy() {
		return idHowToBuy;
	}

	public void setIdHowToBuy(String idHowToBuy) {
		this.idHowToBuy = idHowToBuy;
	}

	public String getDescriptionHowToBuy() {
		return descriptionHowToBuy;
	}

	public void setDescriptionHowToBuy(String descriptionHowToBuy) {
		this.descriptionHowToBuy = descriptionHowToBuy;
	}

	public boolean isInPresence() {
		return inPresence;
	}

	public void setInPresence(boolean inPresence) {
		this.inPresence = inPresence;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "HowToBuy [idHowToBuy=" + idHowToBuy + ", descriptionHowToBuy=" + descriptionHowToBuy + ", inPresence="
				+ inPresence + ", price=" + price + "]";
	}

	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("INSERT INTO HOW_TO_BUY VALUES(?,?,?,?)");
		preparedStatement.setString(1, this.idHowToBuy);
		preparedStatement.setString(2, this.descriptionHowToBuy);
		preparedStatement.setBoolean(3, this.inPresence);
		preparedStatement.setFloat(4, this.price);
		preparedStatement.execute();
	}

	public static HowToBuy searchHowToBuyIntoDatabase(String idHowToBuy, ResultSet resultSet) throws SQLException {
		resultSet.close();
		 resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM HOW_TO_BUY WHERE id_how_to_buy='" + idHowToBuy + "'");
		HowToBuy howToBuy = new HowToBuy();
		while (resultSet.next()) {
			howToBuy.idHowToBuy = resultSet.getString(1);
			howToBuy.descriptionHowToBuy = resultSet.getString(2);
			howToBuy.inPresence = resultSet.getBoolean(3);
			howToBuy.price = resultSet.getFloat(4);
			break;
		}
		return howToBuy;
	}
}
