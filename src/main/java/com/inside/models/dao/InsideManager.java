package com.inside.models.dao;

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
		suscriptions = new ArrayList<>();
		attendanceHistory = new ArrayList<>();
		viewsHistory = new ArrayList<>();
		interests = new ArrayList<>();
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

	public void subscribeToEvent(User user, Event event) {

	}

	public void registerAttendanceToEvent(User user, Event event) {

	}

	public void registerViewToEvent(User user, Event event) {

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
}
