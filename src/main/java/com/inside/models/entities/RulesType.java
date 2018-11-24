package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class RulesType {

	private String idRulesType;
	private String nameRule;
	private String descriptionRule;
	
	public RulesType() {
		// TODO Auto-generated constructor stub
	}
	
	public RulesType(String idRulesType, String nameRule, String descriptionRule) {
		this.idRulesType = idRulesType;
		this.nameRule = nameRule;
		this.descriptionRule = descriptionRule;
	}

	public String getIdRulesType() {
		return idRulesType;
	}

	public void setIdRulesType(String idRulesType) {
		this.idRulesType = idRulesType;
	}

	public String getNameRule() {
		return nameRule;
	}

	public void setNameRule(String nameRule) {
		this.nameRule = nameRule;
	}

	public String getDescriptionRule() {
		return descriptionRule;
	}

	public void setDescriptionRule(String descriptionRule) {
		this.descriptionRule = descriptionRule;
	}

	@Override
	public String toString() {
		return "RulesType [idRulesType=" + idRulesType + ", nameRule=" + nameRule + ", descriptionRule="
				+ descriptionRule + "]";
	}
	
	
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO RULES_TYPE VALUES(?,?,?)");
		preparedStatement.setString(1, this.idRulesType);
		preparedStatement.setString(2, this.nameRule);
		preparedStatement.setString(3, this.descriptionRule);
		preparedStatement.execute();
	}
	
	
	
	
	
	
}
