package com.inside.models.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Date;

import com.inside.exceptions.EventDoesntExists;
import com.inside.models.entities.AttendanceHistory;
import com.inside.models.entities.Credentials;
import com.inside.models.entities.Event;
import com.inside.models.entities.Image;
import com.inside.models.entities.Interest;
import com.inside.models.entities.Suscription;
import com.inside.models.entities.User;
import com.inside.models.entities.ViewsHistory;
import com.sun.javafx.scene.paint.GradientUtils.Point;


public class InsideManagercesar {

	private ArrayList<Event> events;
	private ArrayList<User> users;
	private ArrayList<Suscription> suscriptions;
	private ArrayList<AttendanceHistory> attendanceHistory;
	private ArrayList<ViewsHistory> viewsHistory;
	private ArrayList<Interest> interests;

	//---------------------------------------------------------------------------------------
	/**
	* Uso del patron singleton para obtener instancias de esta clase de ser necesario
	*/
	private static InsideManagercesar insideManger;
	public static InsideManagercesar getInstance() {
		if (insideManger == null) {
			insideManger = new InsideManagercesar();
		}
		return insideManger;
	}
	//----------------------------------------------------------------------------------------

	private InsideManagercesar () {
		try {
			events = Event.listAllEvents();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		users = new ArrayList<>();
		suscriptions = new ArrayList<>();
		attendanceHistory = new ArrayList<>();
		viewsHistory = new ArrayList<>();
		interests = new ArrayList<>();
	}
	
	// user-----------------------------------------------------------
	/**
	 * registra el usuario en la base de datos
	 * @param userInside
	 */
	public void registerUser(User userInside) {
		try {
			userInside.insertIntoDataBase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Crea un nuevo usuario
	 * @param idUser
	 * @param credential
	 * @param image
	 * @param nameUser
	 * @param lastName
	 * @param birthDate
	 * @param nickename
	 * @param userInteres
	 * @return
	 */
	public User createUser(String idUser, Credentials credential, Image image, String nameUser, String lastName,
			Date birthDate, String nickename, ArrayList<Interest> userInteres) {
		User userInside =  new User(idUser, credential, image, nameUser, lastName, birthDate, nickename, userInteres);
		return userInside;
	}

	public void editUser() {
		// TODO
	}

	public void deleteUser() {

	}

	public void searchUser() {

	}

	public void deactivateUser() {

	}

	// ---------------------------------event-------------------------

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

	//------------------------------------------------------------------------------

	public void subscribeToEvent(User user, Event event) {

	}

	public void registerAttendanceToEvent(User user, Event event) {

	}

	public void registerViewToEvent(User user, Event event) {

	}
	
	//----------------------------------------------------------------------------------
	
	public void addInterest(Interest interest) {
		this.interests.add(interest);
		try {
			interest.insertIntoDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//----------------------------------getters&setters---------------------------------
	public ArrayList<Event> getEvents() {
		return events;
	}

	public ArrayList<Event> getEventsByNearnes(float userLatittude, float userLongitude) {
		Collections.sort(events, new Comparator<Event>() {

			@Override
			public int compare(Event o1, Event o2) {
				double distanceA = Math.sqrt(Math.pow(userLatittude - o1.getAddress().getLatitude(),2) + Math.pow(userLongitude - o1.getAddress().getLongitude(),2));
				double distanceB = Math.sqrt(Math.pow(userLatittude - o2.getAddress().getLatitude(),2) + Math.pow(userLongitude - o2.getAddress().getLongitude(),2));
				return Boolean.compare(distanceA>=distanceB,true);
			}
		});
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
