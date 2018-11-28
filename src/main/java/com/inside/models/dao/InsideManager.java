package com.inside.models.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import com.inside.models.entities.Address;
import com.inside.models.entities.AttendanceHistory;
import com.inside.models.entities.Credentials;
import com.inside.models.entities.EventDate;
import com.inside.models.entities.EventInside;
import com.inside.models.entities.HowToBuy;
import com.inside.models.entities.Image;
import com.inside.models.entities.Interest;
import com.inside.models.entities.Rule;
import com.inside.models.entities.Suscription;
import com.inside.models.entities.UserInside;
import com.inside.models.entities.ViewsHistory;


public class InsideManager {

	private ArrayList<EventInside> events;
	private ArrayList<UserInside> users;
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
			events = EventInside.listAllEvents();
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
	public void registerUser(UserInside userInside) {
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
	public UserInside createUser(String idUser, Credentials credential, Image image, String nameUser, String lastName,
			Date birthDate, String nickename, ArrayList<Interest> userInteres) {
		UserInside userInside =  new UserInside(idUser, credential, image, nameUser, lastName, birthDate, nickename, userInteres);
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
	
	public ArrayList<EventInside> listAllEvents(){
		try {
			return EventInside.listAllEvents();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
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
	public EventInside createEvent(String idEvent, UserInside userCreator, HowToBuy howToBuy, Address address, EventDate eventDate,
			String nameEvent, String descriptionEvent, ArrayList<Image> gallery, ArrayList<Interest> eventInterests,
			ArrayList<Rule> regulations) {
		EventInside eventInside = new EventInside(idEvent, userCreator, howToBuy, address, eventDate, nameEvent, descriptionEvent, gallery, eventInterests, regulations);
		return eventInside;
	}
	
	/**
	 * Registra el evento en la base de datos
	 * @param eventInside
	 */
	public void registerEvent(EventInside eventInside) {
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

	public EventInside searchEvent(String idEvent) {
		EventInside event = null;
		try {
			event = EventInside.searchEventIntoDatabase(idEvent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event;
	}

	//------------------------------------------------------------------------------

	public void subscribeToEvent(UserInside user, EventInside event) {

	}

	public void registerAttendanceToEvent(UserInside user, EventInside event) {

	}

	public void registerViewToEvent(UserInside user, EventInside event) {

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
	public ArrayList<EventInside> getEvents() {
		return events;
	}


	public ArrayList<UserInside> getUsers() {
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
