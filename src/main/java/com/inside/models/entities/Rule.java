package com.inside.models.entities;

public class Rule {

	private String idRule;
	private RulesType ruletype;
	private String valueRule;

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
	
}
