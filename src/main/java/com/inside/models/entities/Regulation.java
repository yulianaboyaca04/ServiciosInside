package com.inside.models.entities;

public class Regulation {

	private String idRegulation;
	private Rule rule;
	private Event event;
	
	public Regulation(String idRegulations, Rule rule, Event event) {
		super();
		this.idRegulation = idRegulations;
		this.rule = rule;
		this.event = event;
	}
	
	public String getIdRegulation() {
		return idRegulation;
	}
	public void setIdRegulation(String idRegulations) {
		this.idRegulation = idRegulations;
	}
	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
