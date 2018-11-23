package com.inside.models.entities;

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
	
}