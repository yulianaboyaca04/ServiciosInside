package com.inside.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.inside.exceptions.EventDoesntExists;
import com.inside.exceptions.SuscriptionDoesntExists;
import com.inside.exceptions.UserDoesntExists;
import com.inside.models.entities.Address;
import com.inside.models.entities.AttendanceHistory;
import com.inside.models.entities.Credentials;
import com.inside.models.entities.CredentialsType;
import com.inside.models.entities.Event;
import com.inside.models.entities.Event.EventCard;
import com.inside.models.entities.EventDate;
import com.inside.models.entities.HowToBuy;
import com.inside.models.entities.Image;
import com.inside.models.entities.Interest;
import com.inside.models.entities.Rule;
import com.inside.models.entities.Suscription;
import com.inside.models.entities.User;
import com.inside.models.entities.ViewsHistory;
import com.inside.persistence.DataBaseAcces;

public class InsideManager {

	private ArrayList<Event> events;
	private ArrayList<User> users;
	private ArrayList<Suscription> suscriptions;
	private ArrayList<AttendanceHistory> attendanceHistory;
	private ArrayList<ViewsHistory> viewsHistory;
	private ArrayList<Interest> interests;

	// --------------------------------Singleton----------------------------------------------
	private static InsideManager insideManger;

	public static InsideManager getInstance() {
		if (insideManger == null) {
			insideManger = new InsideManager();
		}
		return insideManger;
	}
	// -------------------------------Constructor---------------------------------------------

	private InsideManager() {
		try {
			System.out.println("Loading users...");
			users = listAllUsers();
			System.out.println("Users loaded.");
		} catch (SQLException | UserDoesntExists e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Loading events...");
			events = listAllEvents();
			System.out.println("Events loaded.");

		} catch (SQLException | UserDoesntExists e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Loading interests...");
			interests = Interest.listAllInterests();
			System.out.println("Interests loaded.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Loading suscriptions...");
			listAllSuscriptions();
			System.out.println("Suscriptions loaded.");
		} catch (SQLException | UserDoesntExists | EventDoesntExists e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Loading attendance history...");
			listAllAttendanceHistory();
			System.out.println("Attendance history loaded.");
		} catch (SQLException | UserDoesntExists | EventDoesntExists e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Loading views history...");
			listAllViewsHistory();
			System.out.println("Views history loaded.");
		} catch (SQLException | UserDoesntExists | EventDoesntExists e) {
			e.printStackTrace();
		}
	}

	// -----------------------------------user------------------------------------------------
	public User registerUser(User userInside) throws SQLException {
		String userId = DataBaseAcces.getKeyNextVal("USERS", "ID_USER");
		userInside.setIdUser(userId);
		userInside.getCredential().setIdCredential(userId);
		String imageId = DataBaseAcces.getKeyNextVal("IMAGES", "ID_IMAGE");
		Image img = new Image(imageId, userInside.getImage().getContent());
		userInside.setImage(img);

		userInside.getCredential().insertIntoDataBase();
		userInside.getImage().insertIntoDataBase();
		userInside.insertIntoDataBase();
		users.add(userInside);
		return userInside;
	}

	public User editUser(User userEdited) throws UserDoesntExists, SQLException {
		User us = searchUser(userEdited.getIdUser());
		us.edit(userEdited);
		for (Event event : events) {
			if (event.getUserCreator().getIdUser().equals(userEdited.getIdUser())) {
				event.setUserCreator(userEdited);
			}
		}
		return us;
	}

	public void deleteUser(String idUser) throws SQLException, UserDoesntExists {
		User user = searchUser(idUser);
		user.removeFromDatabase();
		users.remove(user);
		removeUserFromAnaywhere(idUser);
	}

	private void removeUserFromAnaywhere(String idUser) {
		// remove from suscriptions
		ArrayList<Suscription> suscriptionstoDelete = new ArrayList<Suscription>();
		for (Suscription suscription : suscriptions) {
			if (suscription.getUser().getIdUser().equals(idUser)) {
				suscriptionstoDelete.add(suscription);
			}
		}
		for (Suscription suscription : suscriptionstoDelete) {
			suscriptions.remove(suscription);
		}

		// remove from events
		ArrayList<Event> eventstoDelete = new ArrayList<Event>();
		for (Event event : events) {
			if (event.getUserCreator().getIdUser().equals(idUser)) {
				eventstoDelete.add(event);
			}
		}
		for (Event event : eventstoDelete) {
			events.remove(event);
		}

		// remove from attendance
		ArrayList<AttendanceHistory> attendanceToDelete = new ArrayList<AttendanceHistory>();
		for (AttendanceHistory attendanceHistoryRegistry : attendanceHistory) {
			if (attendanceHistoryRegistry.getUser().getIdUser().equals(idUser)) {
				attendanceToDelete.add(attendanceHistoryRegistry);
			}
		}
		for (AttendanceHistory attendanceHistoryRegistry : attendanceToDelete) {
			attendanceHistory.remove(attendanceHistoryRegistry);
		}

		// remove from view
		ArrayList<ViewsHistory> viewToDelete = new ArrayList<ViewsHistory>();
		for (ViewsHistory viewsHistoryRegistry : viewsHistory) {
			if (viewsHistoryRegistry.getUser().getIdUser().equals(idUser)) {
				viewToDelete.add(viewsHistoryRegistry);
			}
		}
		for (ViewsHistory viewsHistoryRegistry : viewToDelete) {
			viewsHistory.remove(viewsHistoryRegistry);
		}
	}

	public User searchUser(String idUser) throws UserDoesntExists {
		for (User userInside : users) {
			if (userInside.getIdUser().equals(idUser)) {
				return userInside;
			}
		}
		throw new UserDoesntExists();
	}

	public void deactivateUser() {

	}

	public User autenthicate(Credentials credentials) throws UserDoesntExists {
		for (User user : users) {
			if (user.getCredential().getCredentialsType().getIdCredentials()
					.equals(credentials.getCredentialsType().getIdCredentials())
					&& user.getCredential().getUserName().equals(credentials.getUserName())
					&& user.getCredential().getPasswordOrToken().equals(credentials.getPasswordOrToken())) {

				return user;
			}
		}
		throw new UserDoesntExists();
	}

	public ArrayList<String> getEventsCreatedAndInsides(String idUser) {
		ArrayList<String> content = new ArrayList<String>();
		int counterEvents = 0;
		int counterInsides = 0;
		for (Event event : events) {
			if (event.getUserCreator().getIdUser().equals(idUser)) {
				counterEvents++;
			}
		}
		for (Suscription suscription : suscriptions) {
			if (suscription.getUser().getIdUser().equals(idUser)) {
				counterInsides++;
			}
		}
		content.add(counterEvents + " Evento(s) creado(s).");
		content.add(counterInsides + " Inside(s).");
		return content;
	}
	
	// ---------------------------------event-------------------------------------------------

	public Event createEvent(Event event) throws SQLException {
		String idEvent = DataBaseAcces.getKeyNextVal("EVENTS", "ID_EVENT");
		String idHowToBuy = DataBaseAcces.getKeyNextVal("HOW_TO_BUY", "ID_HOW_TO_BUY");
		String idAddress = DataBaseAcces.getKeyNextVal("ADDRESS", "ID_ADDRESS");
		String idDate = DataBaseAcces.getKeyNextVal("DATES", "ID_DATE");

		event.setIdEvent(idEvent);
		event.getHowToBuy().setIdHowToBuy(idHowToBuy);
		event.getAddress().setIdAddress(idAddress);
		event.getEventDate().setIdDate(idDate);

		event.getGallery().add(new Image(DataBaseAcces.getKeyNextVal("IMAGES", "ID_IMAGE"),"https://s3.amazonaws.com/nside-20181215124008-deployment/public/"+idEvent+"imageEvent.jpg"));
		event.getAddress().insertIntoDataBase();
		event.getHowToBuy().insertIntoDataBase();
		event.getEventDate().insertIntoDataBase();
		event.insertIntoDataBase();
		events.add(event);
		return event;
	}

	public Event editEvent(Event eventEdited) throws EventDoesntExists, SQLException {
		Event ev = searchEvent(eventEdited.getIdEvent());
		ev.edit(eventEdited);
		for (Suscription suscriptions : suscriptions) {
			if (suscriptions.getEvent().getIdEvent().equals(eventEdited.getIdEvent())) {
				suscriptions.setEvent(eventEdited);
			}
		}
		return eventEdited;
	}

	public void deleteEvent(String idEvent) throws EventDoesntExists, SQLException {
		Event ev = searchEvent(idEvent);
		events.remove(ev);
		ev.removeFromDatabase();
		removeEventFromAnywhere(idEvent);
	}

	private void removeEventFromAnywhere(String idEvent) {
		// remove from suscriptions
		ArrayList<Suscription> suscriptionstoDelete = new ArrayList<Suscription>();
		for (Suscription suscription : suscriptions) {
			if (suscription.getEvent().getIdEvent().equals(idEvent)) {
				suscriptionstoDelete.add(suscription);
			}
		}
		for (Suscription suscription : suscriptionstoDelete) {
			suscriptions.remove(suscription);
		}
		// remove from attendance
		ArrayList<AttendanceHistory> attendanceToDelete = new ArrayList<AttendanceHistory>();
		for (AttendanceHistory attendanceHistoryRegistry : attendanceHistory) {
			if (attendanceHistoryRegistry.getEvent().getIdEvent().equals(idEvent)) {
				attendanceToDelete.add(attendanceHistoryRegistry);
			}
		}
		for (AttendanceHistory attendanceHistoryRegistry : attendanceToDelete) {
			attendanceHistory.remove(attendanceHistoryRegistry);
		}

		// remove from view
		ArrayList<ViewsHistory> viewToDelete = new ArrayList<ViewsHistory>();
		for (ViewsHistory viewsHistoryRegistry : viewsHistory) {
			if (viewsHistoryRegistry.getEvent().getIdEvent().equals(idEvent)) {
				viewToDelete.add(viewsHistoryRegistry);
			}
		}
		for (ViewsHistory viewsHistoryRegistry : viewToDelete) {
			viewsHistory.remove(viewsHistoryRegistry);
		}

	}

	public Event searchEvent(String idEvent) throws EventDoesntExists {
		for (Event event : events) {
			if (event.getIdEvent().equals(idEvent)) {
				return event;
			}
		}
		throw new EventDoesntExists();
	}

	// ------------------------------------rompimientos--------------------------------------

	public Suscription subscribeToEvent(String idUser, String idEvent)
			throws SQLException, UserDoesntExists, EventDoesntExists {
		User user = searchUser(idUser);
		Event event = searchEvent(idEvent);
		Suscription suscription = new Suscription(user, event);
		suscription.insertIntoDataBase();
		this.suscriptions.add(suscription);
		return suscription;
	}

	public String unSubscribeToEvent(String idUser, String idEvent)
			throws UserDoesntExists, EventDoesntExists, SQLException {
		User user = searchUser(idUser);
		Event event = searchEvent(idEvent);
		Suscription suscriptionToDelete = null;
		for (Suscription suscription : suscriptions) {
			if (suscription.getEvent().getIdEvent().equals(event.getIdEvent())
					&& suscription.getUser().getIdUser().equals(user.getIdUser())) {
				suscription.deleteFromDataBase();
				suscriptionToDelete = suscription;
			}
		}
		suscriptions.remove(suscriptionToDelete);
		return "SuscriptionDeleted";
	}

	public Suscription checkIfSubscribed(String idUser, String idEvent) throws SuscriptionDoesntExists {
		for (Suscription suscription : suscriptions) {
			if (suscription.getUser().getIdUser().equals(idUser)
					&& suscription.getEvent().getIdEvent().equals(idEvent)) {
				return suscription;
			}
		}
		throw new SuscriptionDoesntExists();
	}

	public void registerAttendanceToEvent(String idUser, String idEvent)
			throws UserDoesntExists, EventDoesntExists, SQLException {
		User user = searchUser(idUser);
		Event event = searchEvent(idEvent);
		AttendanceHistory attendanceHistory = new AttendanceHistory(user, event);
		attendanceHistory.insertIntoDataBase();
		this.attendanceHistory.add(attendanceHistory);
	}

	public void registerViewToEvent(String idUser, String idEvent)
			throws UserDoesntExists, EventDoesntExists, SQLException {
		User user = searchUser(idUser);
		Event event = searchEvent(idEvent);
		ViewsHistory view = new ViewsHistory(user, event);
		view.insertIntoDataBase();
		this.viewsHistory.add(view);
	}

	// --------------------------------------intereses----------------------------------------

	public void addInterest(Interest interest) throws SQLException {
		interest.insertIntoDataBase();
		this.interests.add(interest);
	}

	// ---------------------------------------filtros------------------------------------------

	public ArrayList<Event> getEventsByNearnes(float userLatittude, float userLongitude) {
		ArrayList<Event> sponsoredEvents = new ArrayList<Event>();
		ArrayList<Event> unSponsoredEvents = new ArrayList<Event>();
		for (Event event : events) {
			Timestamp currentDate = new Timestamp(System.currentTimeMillis());
			Timestamp evenDate = event.getEventDate().getDateStart();
			float daysRemaining = ((evenDate.getTime() - currentDate.getTime()) / 81400000);
			if (daysRemaining <= event.getDaysSponsored() && daysRemaining > 0) {
				sponsoredEvents.add(event);
			} else {
				unSponsoredEvents.add(event);
			}
		}
		Collections.sort(sponsoredEvents, new Comparator<Event>() {

			@Override
			public int compare(Event o1, Event o2) {
				double distanceA = Math.sqrt(Math.pow(userLatittude - o1.getAddress().getLatitude(), 2)
						+ Math.pow(userLongitude - o1.getAddress().getLongitude(), 2));
				double distanceB = Math.sqrt(Math.pow(userLatittude - o2.getAddress().getLatitude(), 2)
						+ Math.pow(userLongitude - o2.getAddress().getLongitude(), 2));
				return Boolean.compare(distanceA >= distanceB, true);
			}
		});
		Collections.sort(unSponsoredEvents, new Comparator<Event>() {

			@Override
			public int compare(Event o1, Event o2) {
				double distanceA = Math.sqrt(Math.pow(userLatittude - o1.getAddress().getLatitude(), 2)
						+ Math.pow(userLongitude - o1.getAddress().getLongitude(), 2));
				double distanceB = Math.sqrt(Math.pow(userLatittude - o2.getAddress().getLatitude(), 2)
						+ Math.pow(userLongitude - o2.getAddress().getLongitude(), 2));
				return Boolean.compare(distanceA >= distanceB, true);
			}
		});
		for (Event event : unSponsoredEvents) {
			sponsoredEvents.add(event);
		}
		return sponsoredEvents;
	}

	public ArrayList<Event> getEventsbyPopularity() {
		ArrayList<Event> sponsoredEvents = new ArrayList<Event>();
		ArrayList<Event> unSponsoredEvents = new ArrayList<Event>();
		for (Event event : events) {
			Timestamp currentDate = new Timestamp(System.currentTimeMillis());
			Timestamp evenDate = event.getEventDate().getDateStart();
			float daysRemaining = ((evenDate.getTime() - currentDate.getTime()) / 81400000);
			if (daysRemaining <= event.getDaysSponsored() && daysRemaining > 0) {
				sponsoredEvents.add(event);
			} else {
				unSponsoredEvents.add(event);
			}
		}
		Collections.sort(sponsoredEvents, new Comparator<Event>() {

			@Override
			public int compare(Event o1, Event o2) {
				return getNumberOfSuscriptions(o2.getIdEvent()) - getNumberOfSuscriptions(o1.getIdEvent());
			}
		});
		Collections.sort(unSponsoredEvents, new Comparator<Event>() {

			@Override
			public int compare(Event o1, Event o2) {
				return getNumberOfSuscriptions(o2.getIdEvent()) - getNumberOfSuscriptions(o1.getIdEvent());
			}
		});
		for (Event event : unSponsoredEvents) {
			sponsoredEvents.add(event);
		}
		return sponsoredEvents;
	}

	public int getNumberOfSuscriptions(String idEvent) {
		int numberOfSuscriptions = 0;
		for (Suscription suscription : suscriptions) {
			if (suscription.getEvent().getIdEvent().equals(idEvent)) {
				numberOfSuscriptions++;
			}
		}
		return numberOfSuscriptions;
	}

	public ArrayList<Event> getEventsByPriceAscending() {
		Collections.sort(events, new Comparator<Event>() {

			@Override
			public int compare(Event o1, Event o2) {
				return (int) (o1.getHowToBuy().getPrice() - o2.getHowToBuy().getPrice());
			}
		});
		return events;
	}

	public ArrayList<Event> getEventsByPriceDescending() {
		Collections.sort(events, new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				return (int) (o2.getHowToBuy().getPrice() - o1.getHowToBuy().getPrice());
			}
		});
		return events;
	}


	public ArrayList<Event> getEventsByInterest(String idUser) throws UserDoesntExists {
		ArrayList<Event> eventsByInterest = new ArrayList<Event>();
		User user = searchUser(idUser);
		for (Event event : events) {
			for (Interest interest : user.getUserInteres()) {
				if (event.getEventInterests().contains(interest) && (!eventsByInterest.contains(event))) {
					eventsByInterest.add(event);
				}
			}
		}
		for (Event event : events) {
			if (!eventsByInterest.contains(event)) {
				eventsByInterest.add(event);
			}
		}
		return eventsByInterest;
	}
	
	// ----------------------------------getters&setters---------------------------------

	public ArrayList<Event> getEvents() {
		return events;
	}

	public ArrayList<EventCard> getEventCards() {
		ArrayList<EventCard> eventCards = new ArrayList<>();
		for (Event event : events) {
			eventCards.add(event.getEventCard());
		}
		return eventCards;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public ArrayList<Suscription> getSuscriptions() {
		return suscriptions;
	}

	public ArrayList<AttendanceHistory> getAttendanceHistory() {
		return attendanceHistory;
	}

	public ArrayList<ViewsHistory> getViewsHistory() {
		return viewsHistory;
	}

	public ArrayList<Interest> getInterests() {
		return interests;
	}

	public void setInterests(ArrayList<Interest> interests) {
		this.interests = interests;
	}

	// -----------------------------------------listares--------------------------------------

	public void listAllSuscriptions() throws SQLException, UserDoesntExists, EventDoesntExists {
		suscriptions = new ArrayList<>();
		ResultSet resultSet;
		resultSet = DataBaseAcces.getInstance().getStatement().executeQuery("SELECT * FROM SUSCRIPTION");
		while (resultSet.next()) {
			User us = searchUser(resultSet.getString(1));
			Event ev = searchEvent(resultSet.getString(2));
			Suscription suscription = new Suscription(us, ev);
			suscriptions.add(suscription);
		}
	}

	public void listAllAttendanceHistory() throws SQLException, UserDoesntExists, EventDoesntExists {
		attendanceHistory = new ArrayList<>();
		ResultSet resultSet;
		resultSet = DataBaseAcces.getInstance().getStatement().executeQuery("SELECT * FROM ATTENDANCE_HISTORY");
		while (resultSet.next()) {
			User us = searchUser(resultSet.getString(1));
			Event ev = searchEvent(resultSet.getString(2));
			AttendanceHistory AttendanceHistory = new AttendanceHistory(us, ev);
			attendanceHistory.add(AttendanceHistory);
		}
	}

	public void listAllViewsHistory() throws SQLException, UserDoesntExists, EventDoesntExists {
		viewsHistory = new ArrayList<>();
		ResultSet resultSet;
		resultSet = DataBaseAcces.getInstance().getStatement().executeQuery("SELECT * FROM VIEWS_HISTORY");
		while (resultSet.next()) {
			User us = searchUser(resultSet.getString(1));
			Event ev = searchEvent(resultSet.getString(2));
			ViewsHistory viewHistory = new ViewsHistory(us, ev);
			// TODO set del timestamp con el resultset 3
			viewsHistory.add(viewHistory);
		}
	}

	public ArrayList<Event> listAllEvents() throws SQLException, UserDoesntExists {
		ArrayList<Event> events = new ArrayList<>();
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT * FROM EVENTS "
						+ "INNER JOIN HOW_TO_BUY ON EVENTS.ID_HOW_TO_BUY = HOW_TO_BUY.ID_HOW_TO_BUY "
						+ "INNER JOIN ADDRESS ON EVENTS.ID_ADDRESS = ADDRESS.ID_ADDRESS "
						+ "INNER JOIN DATES ON EVENTS.ID_DATE = DATES.ID_DATE");
		while (resultSet.next()) {
			Event ev = new Event(resultSet.getString(1), searchUser(resultSet.getString(2)),
					new HowToBuy(resultSet.getString(3), // idHowToBuy, descriptionHowToBuy, inPresence, price
							resultSet.getString(10), resultSet.getBoolean(11), resultSet.getFloat(12)),
					new Address(resultSet.getString(4), // idAddress, latitude, longitude, nameCity, namePlace
							resultSet.getFloat(14), resultSet.getFloat(15), resultSet.getString(16),
							resultSet.getString(17)),
					new EventDate(resultSet.getString(5), // idDate, dateStart, dateFinish
							resultSet.getTimestamp(19), resultSet.getTimestamp(20)),
					resultSet.getString(6), resultSet.getString(7), resultSet.getFloat(8), new ArrayList<Image>(),
					new ArrayList<Interest>(), new ArrayList<Rule>()); // TODO regulations
			events.add(ev);
		}
		for (Event event : events) {
			event.setGallery(Event.searchGalleryIntoDatabase(event.getIdEvent()));
			event.setEventInterests(Event.searchEventInterestsIntoDatabase(event.getIdEvent()));
			event.setRegulations(Event.searchRegulationsIntoDatabase(event.getIdEvent()));
		}
		return events;
	}

	public ArrayList<User> listAllUsers() throws SQLException, UserDoesntExists {
		ArrayList<User> users = new ArrayList<>();
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement().executeQuery("SELECT * FROM USERS "
				+ "INNER JOIN CREDENTIALS ON USERS.ID_CREDENTIAL = CREDENTIALS.ID_CREDENTIAL "
				+ "INNER JOIN CREDENTIALS_TYPE ON CREDENTIALS.ID_CREDENTIALS_TYPE = CREDENTIALS_TYPE.ID_CREDENTIALS_TYPE "
				+ "INNER JOIN IMAGES ON USERS.ID_IMAGE = IMAGES.ID_IMAGE");
		while (resultSet.next()) {
			User us = new User(resultSet.getString(1), // idUser, credential, image, nameUser, lastName, birthDate,
														// nickname, userInteres
					new Credentials(resultSet.getString(2), // idCredential, credentialsType, user, passwordOrToken
							new CredentialsType(resultSet.getString(9), // idCredentials, name
									resultSet.getString(13)),
							resultSet.getString(10), resultSet.getString(11)),
					new Image(resultSet.getString(3), // idImage, content
							resultSet.getString(15)),
					resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7),
					new ArrayList<Interest>());
			users.add(us);
		}
		for (User user : users) {
			user.setUserInteres(User.searchInterestsIntoDatabase(user.getIdUser()));
		}
		return users;
	}

	public ArrayList<Event> getSuscriptionsByUser(String idUser) {
		ArrayList<Event> eventsSuscribed = new ArrayList<Event>();
		for (Suscription suscription : suscriptions) {
			if (suscription.getUser().getIdUser().equals(idUser)) {
				eventsSuscribed.add(suscription.getEvent());
			}
		}
		return eventsSuscribed;
	}

	public ArrayList<Event> getEventsCreated(String idUser) {
		ArrayList<Event> eventsCreated = new ArrayList<Event>();
		for (Event event : events) {
			if (event.getUserCreator().getIdUser().equals(idUser)) {
				eventsCreated.add(event);
			}
		}
		return eventsCreated;
	}


}