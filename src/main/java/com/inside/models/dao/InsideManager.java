package com.inside.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.inside.exceptions.EventDoesntExists;
import com.inside.exceptions.UserDoesntExists;
import com.inside.models.entities.AttendanceHistory;
import com.inside.models.entities.Event;
import com.inside.models.entities.Interest;
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
			System.out.println("Loading events...");
			events = Event.listAllEvents();
			System.out.println("Events loaded.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Loading users...");
			users = User.listAllUsers();
			System.out.println("Users loaded.");
		} catch (SQLException e) {
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
	public void registerUser(User userInside) throws SQLException {
		users.add(userInside);
		userInside.insertIntoDataBase();
	}

	public void editUser(User userEdited) throws UserDoesntExists, SQLException {
		User us = searchUser(userEdited.getIdUser());
		us.edit(userEdited);
	}

	public void deleteUser(String idUser) throws SQLException, UserDoesntExists {
		User user = searchUser(idUser);
		user.removeFromDatabase();
		users.remove(user);

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

	// ---------------------------------event-------------------------------------------------

	public void createEvent(Event event) throws SQLException {
		event.getAddress().insertIntoDataBase();
		event.getHowToBuy().insertIntoDataBase();
		event.getEventDate().insertIntoDataBase();
		event.insertIntoDataBase();
		events.add(event);
	}

	public void editEvent(Event eventEdited) throws EventDoesntExists, SQLException {
		Event ev = searchEvent(eventEdited.getIdEvent());
		ev.edit(eventEdited);
	}

	public void deleteEvent(String idEvent) throws EventDoesntExists, SQLException {
		Event ev = searchEvent(idEvent);
		events.remove(ev);
		ev.removeFromDatabase();
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

	public void subscribeToEvent(String idUser, String idEvent)
			throws SQLException, UserDoesntExists, EventDoesntExists {
		User user = searchUser(idUser);
		Event event = searchEvent(idEvent);
		Suscription suscription = new Suscription(user, event);
		suscription.insertIntoDataBase();
		this.suscriptions.add(suscription);
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

	public void addInterest(Interest interest) {
		this.interests.add(interest);
		try {
			interest.insertIntoDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------filtros------------------------------------------

	public ArrayList<Event> getEventsByNearnes(float userLatittude, float userLongitude) {
		Collections.sort(events, new Comparator<Event>() {

			@Override
			public int compare(Event o1, Event o2) {
				double distanceA = Math.sqrt(Math.pow(userLatittude - o1.getAddress().getLatitude(), 2)
						+ Math.pow(userLongitude - o1.getAddress().getLongitude(), 2));
				double distanceB = Math.sqrt(Math.pow(userLatittude - o2.getAddress().getLatitude(), 2)
						+ Math.pow(userLongitude - o2.getAddress().getLongitude(), 2));
				return Boolean.compare(distanceA >= distanceB, true);
			}
		});
		return events;
	}
	
	public ArrayList<Event> getEventsbyPopularity() {
		Collections.sort(events, new Comparator<Event>() {

			@Override
			public int compare(Event o1, Event o2) {
				return getNumberOfSuscriptions(o2) - getNumberOfSuscriptions(o1);
			}
		});
		return events;
	}
	
	public int getNumberOfSuscriptions (Event event) {
		int numberOfSuscriptions = 0;
		for (Suscription suscription : suscriptions) {
			if (suscription.getEvent().getIdEvent().equals(event.getIdEvent())) {
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
	
	// ----------------------------------getters&setters---------------------------------

	public ArrayList<Event> getEvents() {
		return events;
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
			//TODO set del timestamp con el resultset 3
			viewsHistory.add(viewHistory);
		}
	}
}