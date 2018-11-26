package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.inside.persistence.DataBaseAcces;

public class Rule {

	private String idRule;
	private RulesType ruletype;
	private String valueRule;
	
	public Rule() {
	}

	public Rule(String idRule, RulesType ruletype, String valueRule) {
		this.idRule = idRule;
		this.ruletype = ruletype;
		this.valueRule = valueRule;
	}

	public String getIdRule() {
		return idRule;
	}

	public void setIdRule(String idRule) {
		this.idRule = idRule;
	}

	public RulesType getRuletype() {
		return ruletype;
	}

	public void setRuletype(RulesType ruletype) {
		this.ruletype = ruletype;
	}

	public String getValueRule() {
		return valueRule;
	}

	public void setValueRule(String valueRule) {
		this.valueRule = valueRule;
	}

	@Override
	public String toString() {
		return "Rule [idRule=" + idRule + ", ruletype=" + ruletype + ", valueRule=" + valueRule + "]";
	}
	
	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement("INSERT INTO RULES VALUES(?,?,?)");
		preparedStatement.setString(1, this.idRule);
		preparedStatement.setString(2, this.ruletype.getIdRulesType());
		preparedStatement.setString(3, this.valueRule);
		preparedStatement.execute();
	}

	public static Rule searchRuleIntoDatabase(String idRules, ResultSet resultSet) throws SQLException {
		resultSet.close();
		resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM RULES WHERE id_rules='" + idRules + "'");
		Rule rule = new Rule();
		while (resultSet.next()) {
			rule.idRule = resultSet.getString(1);
			rule.valueRule = resultSet.getString(3);
			String idRuleType = resultSet.getString(2);
			rule.ruletype = RulesType.searchRulesTypeIntoDatabase(idRuleType, resultSet);
			break;
		}
		return rule;
	}
	
}
