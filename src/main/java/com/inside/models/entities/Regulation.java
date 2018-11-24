package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class Regulation {

	private String idRegulation;
	private Rule rule;
	
	public Regulation() {
		// TODO Auto-generated constructor stub
	}
	
	public Regulation(String idRegulation, Rule rule) {
		this.idRegulation = idRegulation;
		this.rule = rule;
	}

	public String getIdRegulation() {
		return idRegulation;
	}

	public void setIdRegulation(String idRegulation) {
		this.idRegulation = idRegulation;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public String toString() {
		return "Regulation [idRegulation=" + idRegulation + ", rule=" + rule + "]";
	}
	
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO REGULATIONS VALUES(?,?)");
		preparedStatement.setString(1, this.idRegulation);
		preparedStatement.setString(2, this.rule.getIdRule());
		preparedStatement.execute();
	}
}