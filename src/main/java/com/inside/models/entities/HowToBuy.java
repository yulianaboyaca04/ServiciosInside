package com.inside.models.entities;

public class HowToBuy {

	private String idHowToBuy;
	private String descriptionHowToBuy;
	private boolean inPresence;
	private float price;

	public HowToBuy() {
		// TODO Auto-generated constructor stub
	}

	public HowToBuy(String idHowToBuy, String descriptionHowToBuy, boolean inPresence, float price) {
		super();
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
}
