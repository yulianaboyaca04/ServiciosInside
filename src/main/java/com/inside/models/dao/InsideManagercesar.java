package com.inside.models.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import com.inside.models.entities.Address;
import com.inside.models.entities.AttendanceHistory;
import com.inside.models.entities.Credentials;
import com.inside.models.entities.EventDate;
import com.inside.models.entities.Event;
import com.inside.models.entities.HowToBuy;
import com.inside.models.entities.Image;
import com.inside.models.entities.Interest;
import com.inside.models.entities.Rule;
import com.inside.models.entities.Suscription;
import com.inside.models.entities.User;
import com.inside.models.entities.ViewsHistory;


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

	/**
	 * Crea un evento
	 * @param idEvent
	 * @param userCreator
	 * @param howToBuy
	 * @param address
	 * @param eventDate
	 * @param nameEvent
	 * @param descriptionEvent
	 * @param gallery
	 * @param eventInterests
	 * @param regulations
	 * @return
	 */
	public Event createEvent(String idEvent, User userCreator, HowToBuy howToBuy, Address address, EventDate eventDate,
			String nameEvent, String descriptionEvent, ArrayList<Image> gallery, ArrayList<Interest> eventInterests,
			ArrayList<Rule> regulations) {
		Event eventInside = new Event(idEvent, userCreator, howToBuy, address, eventDate, nameEvent, descriptionEvent, gallery, eventInterests, regulations);
		return eventInside;
	}
	
	/**
	 * Registra el evento en la base de datos
	 * @param eventInside
	 */
	public void registerEvent(Event eventInside) {
		try {
			eventInside.insertIntoDataBase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editEvent() {
		// TODO
	}

	public void deleteEvent() {

	}

	public Event searchEvent(String idEvent) {
		Event event = null;
		try {
			event = Event.searchEventIntoDatabase(idEvent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event;
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
