package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.inside.persistence.DataBaseAcces;

public class EventInside {

	private String idEvent;
	private UserInside userCreator;
	private HowToBuy howToBuy;
	private Address address;
	private EventDate eventDate;
	private String nameEvent;
	private String descriptionEvent;
	private ArrayList<Image> gallery;
	private ArrayList<Interest> eventInterests;
	private ArrayList<Rule> regulations;

	public EventInside() {

	}

	public EventInside(String idEvent, UserInside userCreator, HowToBuy howToBuy, Address address, EventDate eventDate,
			String nameEvent, String descriptionEvent, ArrayList<Image> gallery, ArrayList<Interest> eventInterests,
			ArrayList<Rule> regulations) {
		this.idEvent = idEvent;
		this.userCreator = userCreator;
		this.howToBuy = howToBuy;
		this.address = address;
		this.eventDate = eventDate;
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
		this.gallery = new ArrayList<Image>();
		this.eventInterests = new ArrayList<Interest>();
		this.regulations = new ArrayList<Rule>();
		for (Image image : gallery) {
			this.gallery.add(image);
		}
		for (Interest interest : eventInterests) {
			this.eventInterests.add(interest);
		}
		for (Rule rule : regulations) {
			this.regulations.add(rule);
		}
	}

	public String getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}

	public UserInside getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(UserInside userCreator) {
		this.userCreator = userCreator;
	}

	public HowToBuy getHowToBuy() {
		return howToBuy;
	}

	public void setHowToBuy(HowToBuy howToBuy) {
		this.howToBuy = howToBuy;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EventDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(EventDate eventDate) {
		this.eventDate = eventDate;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public String getDescriptionEvent() {
		return descriptionEvent;
	}

	public void setDescriptionEvent(String descriptionEvent) {
		this.descriptionEvent = descriptionEvent;
	}

	public ArrayList<Image> getGallery() {
		return gallery;
	}

	public void setGallery(ArrayList<Image> gallery) {
		this.gallery = gallery;
	}

	public ArrayList<Interest> getEventInterests() {
		return eventInterests;
	}

	public void setEventInterests(ArrayList<Interest> eventInterests) {
		this.eventInterests = eventInterests;
	}

	public ArrayList<Rule> getRegulations() {
		return regulations;
	}

	public void setRegulations(ArrayList<Rule> regulations) {
		this.regulations = regulations;
	}

	@Override
	public String toString() {
		return "EventInside [idEvent=" + idEvent + ", userCreator=" + userCreator + ", howToBuy=" + howToBuy
				+ ", address=" + address + ", eventDate=" + eventDate + ", nameEvent=" + nameEvent
				+ ", descriptionEvent=" + descriptionEvent + ", gallery=" + gallery + ", eventInterests="
				+ eventInterests + ", regulations=" + regulations + "]";
	}

	public void insertIntoDataBase() throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("INSERT INTO EVENTS VALUES(?, ?, ?, ?, ?, ?, ?)");
		preparedStatement.setString(1, this.idEvent);
		preparedStatement.setString(2, this.userCreator.getIdUser());
		preparedStatement.setString(3, this.howToBuy.getIdHowToBuy());
		preparedStatement.setString(4, this.address.getIdAddress());
		preparedStatement.setString(5, this.eventDate.getIdDate());
		preparedStatement.setString(6, this.nameEvent);
		preparedStatement.setString(7, this.descriptionEvent);
		preparedStatement.execute();
		for (int i = 0; i < gallery.size(); i++) {
			insertGalleryIntoDatabase(gallery.get(i));
		}
		for (int i = 0; i < regulations.size(); i++) {
			insertRegulationIntoDatabase(regulations.get(i));
		}
		for (int i = 0; i < eventInterests.size(); i++) {
			insertEventInterestsIntoDatabase(eventInterests.get(i));
		}
	}

	public void insertRegulationIntoDatabase(Rule rule) throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("INSERT INTO REGULATIONS VALUES(?,?)");
		preparedStatement.setString(1, rule.getIdRule());
		preparedStatement.setString(2, this.idEvent);
		preparedStatement.execute();
	}

	public void insertGalleryIntoDatabase(Image image) throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("INSERT INTO GALLERY VALUES(?,?)");
		preparedStatement.setString(1, this.idEvent);
		preparedStatement.setString(2, image.getIdImage());
		preparedStatement.execute();
	}

	public void insertEventInterestsIntoDatabase(Interest interest) throws SQLException {
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("INSERT INTO EVENT_INTERESTES VALUES(?,?)");
		preparedStatement.setString(1, this.idEvent);
		preparedStatement.setString(2, interest.getIdInterest());
		preparedStatement.execute();
	}

	public static EventInside searchEventIntoDatabase(String codigo) throws SQLException {
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM EVENTS WHERE id_event='" + codigo + "'");
		EventInside event = new EventInside();
		while (resultSet.next()) {
			event.idEvent = resultSet.getString(1);
			event.nameEvent = resultSet.getString(6);
			event.descriptionEvent = resultSet.getString(7);
			String idHowToBuy = resultSet.getString(3);
			String idAdrress =  resultSet.getString(4);
			String idEventDate = resultSet.getString(5);
			String idGallery = resultSet.getString(5);

			//			event.userCreator = UserInside.searchUserIntoDatabase(resultSet.getString(2));
			event.howToBuy = HowToBuy.searchHowToBuyIntoDatabase(idHowToBuy, resultSet);
			event.address = Address.searchAddressIntoDatabase(idAdrress, resultSet);
			event.eventDate = EventDate.searchEventDateIntoDatabase(idEventDate, resultSet);
			
		
			
			
			break;
		}

		return event;
	}
	
	public static ArrayList<Image> searchGalleryIntoDatabase(String idEvent) throws SQLException{
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM GALLERY WHERE id_event='" + idEvent + "'");
		ArrayList<Image> gallery = new ArrayList<>();
		while (resultSet.next()) {
			Image img = new Image();
			img.setIdImage(resultSet.getString(2));
//			gallery.add(new Image())
			
			break;
		}
		return gallery;
	}
}
