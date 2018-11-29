package com.inside.models.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;

import com.inside.exceptions.UserDoesntExists;
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
import com.inside.persistence.DataBaseAcces;


public class InsideManager {

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
	private static InsideManager insideManger;
	public static InsideManager getInstance() {
		if (insideManger == null) {
			insideManger = new InsideManager();
		}
		return insideManger;
	}
	//----------------------------------------------------------------------------------------

	private InsideManager () {
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

	/**
	 * Busca usuario por id en la base de datos
	 * @param idUser
	 * @return User
	 */
	public User searchUser(String idUser) {
		User user = null;
		try {
			user = User.searchUserIntoDatabase(idUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
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
		try {
			users = User.listAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	/**
	 * Busca el usuario con el id indicado dentro del array de Users
	 * @param idUser
	 * @return User
	 * @throws UserDoesntExists
	 */
	public User SearchUser(String idUser) throws UserDoesntExists {
		for (User userInside : users) {
			if (userInside.getIdUser().equals(idUser)) {
				return userInside;
			}
		}
		throw new UserDoesntExists();
	}

	
	public void deleteUser(String idUser) throws SQLException {
		User user = searchUser(idUser);
		users.remove(user);
		user.removeFromDatabase();
	}


	
	
}
