package com.inside.models.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inside.persistence.DataBaseAcces;

public class Event {

	@JsonProperty("idEvent")
	private String idEvent;
	@JsonProperty("userCreator")
	private User userCreator;
	@JsonProperty("howToBuy")
	private HowToBuy howToBuy;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("eventDate")
	private EventDate eventDate;
	@JsonProperty("nameEvent")
	private String nameEvent;
	@JsonProperty("descriptionEvent")
	private String descriptionEvent;
	@JsonProperty("gallery")
	private ArrayList<Image> gallery;
	@JsonProperty("eventInterests")
	private ArrayList<Interest> eventInterests;
	@JsonProperty("regulations")
	private ArrayList<Rule> regulations;

	// -------------------------------Constructors------------------------------------------------------------------
	public Event() {

	}

	public Event(String idEvent, User userCreator, HowToBuy howToBuy, Address address, EventDate eventDate,
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

	// -----------------------------inserts-en-bd-----------------------------------------------------------------
	public void insertIntoDataBase() throws SQLException {
		insertEventIntoDatabaseBasic();
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

	public void insertEventIntoDatabaseBasic() throws SQLException {
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

	// ----------------------------querys-en-bd---------------------------------------------------------------------
	public static ArrayList<Event> listAllEvents() throws SQLException {
		ArrayList<Event> events = new ArrayList<>();
		ArrayList<String> idEvents = new ArrayList<>();
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement().executeQuery("SELECT * FROM EVENTS");
		while (resultSet.next()) {
			idEvents.add(resultSet.getString(1));
		}
		for (String idEvent : idEvents) {
			Event event = searchEventIntoDatabase(idEvent);
			events.add(event);
		}
		return events;
	}

	public static Event searchEventIntoDatabase(String codigo) throws SQLException {
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM EVENTS WHERE id_event='" + codigo + "'");
		Event event = new Event();
		while (resultSet.next()) {
			event = generateEventFromResultSet(resultSet);
			break;
		}
		return event;
	}

	private static Event generateEventFromResultSet(ResultSet resultSet) throws SQLException {
		Event event = new Event();

		event.idEvent = resultSet.getString(1);
		event.nameEvent = resultSet.getString(6);
		event.descriptionEvent = resultSet.getString(7);
		String idHowToBuy = resultSet.getString(3);
		String idAdrress = resultSet.getString(4);
		String idEventDate = resultSet.getString(5);

		event.userCreator = User.searchUserIntoDatabase(resultSet.getString(2));
		event.howToBuy = HowToBuy.searchHowToBuyIntoDatabase(idHowToBuy, resultSet);
		event.address = Address.searchAddressIntoDatabase(idAdrress, resultSet);
		event.eventDate = EventDate.searchEventDateIntoDatabase(idEventDate, resultSet);

		event.gallery = searchGalleryIntoDatabase(event.idEvent);
		event.eventInterests = searchEventInterestsIntoDatabase(event.idEvent);
		event.regulations = searchRegulationsIntoDatabase(event.idEvent);
		return event;
	}

	public static ArrayList<Image> searchGalleryIntoDatabase(String idEvent) throws SQLException {
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM GALLERY WHERE id_event='" + idEvent + "'");
		ArrayList<Image> gallery = new ArrayList<>();
		ArrayList<String> idImages = new ArrayList<>();
		while (resultSet.next()) {
			idImages.add(resultSet.getString(2));
		}
		for (String image : idImages) {
			Image img = Image.searchUserIntoDatabase(image);
			gallery.add(img);
		}
		return gallery;
	}

	public static ArrayList<Interest> searchEventInterestsIntoDatabase(String idEvent) throws SQLException {
		ArrayList<Interest> eventInterest = new ArrayList<>();
		ArrayList<String> idInterests = new ArrayList<>();
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM EVENT_INTERESTES WHERE id_event='" + idEvent + "'");
		while (resultSet.next()) {
			idInterests.add(resultSet.getString(2));
		}
		for (String idInterest : idInterests) {
			Interest interest = Interest.searchInterestIntoDatabase(idInterest, resultSet);
			eventInterest.add(interest);
		}
		return eventInterest;
	}

	public static ArrayList<Rule> searchRegulationsIntoDatabase(String idEvent) throws SQLException {
		ArrayList<Rule> regulation = new ArrayList<>();
		ArrayList<String> idRules = new ArrayList<>();
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM REGULATIONS WHERE id_event='" + idEvent + "'");
		while (resultSet.next()) {
			idRules.add(resultSet.getString(1));
		}
		for (String idRule : idRules) {
			Rule rule = Rule.searchRuleIntoDatabase(idRule, resultSet);
			regulation.add(rule);
		}

		return regulation;
	}

	// -----------------------------------Remove-en-bd--------------------------------------------------

	public void removeFromDatabase() throws SQLException {
		System.out.println("Deleting event...");
		PreparedStatement preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("DELETE FROM HOW_TO_BUY WHERE ID_HOW_TO_BUY='" + this.howToBuy.getIdHowToBuy() + "'");
		preparedStatement.execute();
		preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("DELETE FROM ADDRESS WHERE ID_ADDRESS='" + this.address.getIdAddress() + "'");
		preparedStatement.execute();
		preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("DELETE FROM DATES WHERE ID_DATE='" + this.eventDate.getIdDate() + "'");
		preparedStatement.execute();
		preparedStatement = DataBaseAcces.getInstance().getConnection()
				.prepareStatement("DELETE FROM EVENTS WHERE ID_EVENT='" + this.idEvent + "'");
		preparedStatement.execute();
		System.out.println("Event deleted.");
	}

	// ------------------------------------Editar-en-bd---------------------------------------------------

	public void edit(Event eventEdited) throws SQLException {
		PreparedStatement preparedStatement;
		if (!this.howToBuy.equals(eventEdited.howToBuy)) {
			this.howToBuy = eventEdited.howToBuy;
			// Actualiza la tabla howToBuy
			preparedStatement = DataBaseAcces.getInstance().getConnection()
					.prepareStatement("UPDATE HOW_TO_BUY SET " + "DESCRIPTION_HOW_TO_BUY='"
							+ this.howToBuy.getDescriptionHowToBuy() + "', " + "IN_PRESENCE='"
							+ (this.howToBuy.isInPresence() == true ? 1 : 0) + "', " + "PRICE='"
							+ this.howToBuy.getPrice() + "' "

							+ "WHERE ID_HOW_TO_BUY='" + this.howToBuy.getIdHowToBuy() + "'");
			preparedStatement.executeUpdate();
		}
		if (!this.address.equals(eventEdited.address)) {
			this.address = eventEdited.address;
			// Actualiza la tabla address
			preparedStatement = DataBaseAcces.getInstance().getConnection()
					.prepareStatement("UPDATE ADDRESS SET " + "LATITUDE='" + this.address.getLatitude() + "', "
							+ "LONGITUDE='" + this.address.getLongitude() + "', " + "NAME_CITY='"
							+ this.address.getNameCity() + "', " + "NAME_PLACE='" + this.address.getNamePlace() + "' "

							+ "WHERE ID_ADDRESS='" + this.address.getIdAddress() + "'");
			preparedStatement.executeUpdate();
		}
		if (!this.eventDate.equals(eventEdited.eventDate)) {
			/*
			 * this.eventDate = eventEdited.eventDate; //TODO //Actualiza la tabla address
			 * preparedStatement =
			 * DataBaseAcces.getInstance().getConnection().prepareStatement(
			 * "UPDATE DATES SET " + "DATE_START='" + this.eventDate.getDateStart() + "', "
			 * + "DATE_FINISH='"+ this.eventDate.getDateFinish() + "'"
			 * 
			 * + "WHERE ID_DATE='" + this.eventDate.getIdDate() + "'");
			 * preparedStatement.executeUpdate();
			 */
		}
		if ((!this.nameEvent.equals(eventEdited.nameEvent))
				|| (!this.descriptionEvent.equals(eventEdited.descriptionEvent))) {
			this.nameEvent = eventEdited.nameEvent;
			this.descriptionEvent = eventEdited.descriptionEvent;
			// Actualiza la tabla events
			preparedStatement = DataBaseAcces.getInstance().getConnection().prepareStatement(
					"UPDATE EVENTS SET " + "NAME_EVENT='" + this.nameEvent + "', " + "DESCRIPTION_EVENT='"
							+ this.descriptionEvent + "' " + "WHERE ID_EVENT='" + this.idEvent + "'");
			preparedStatement.executeUpdate();
		}
		if (!this.gallery.equals(eventEdited.gallery)) {
			this.gallery = eventEdited.gallery;
			// TODO
		}
		if (!this.eventInterests.equals(eventEdited.eventInterests)) {
			this.eventInterests = eventEdited.eventInterests;
			// TODO
		}
		if (!this.regulations.equals(eventEdited.regulations)) {
			this.regulations = eventEdited.regulations;
			// TODO
		}
	}

	// ---------------------------Getters&Setters-------------------------------------------------------
	public String getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}

	public User getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(User userCreator) {
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

}