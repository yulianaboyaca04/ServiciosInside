package com.inside.models.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.inside.models.entities.AttendanceHistory;
import com.inside.models.entities.EventInside;
import com.inside.models.entities.Interest;
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
		events = new ArrayList<>();
		users = new ArrayList<>();
		suscriptions = new ArrayList<>();
		attendanceHistory = new ArrayList<>();
		viewsHistory = new ArrayList<>();
		interests = new ArrayList<>();
		
		addInterest(new Interest("2", "los amo"));
	}
	
	// user-----------------------------------------------------------
	public void registerUser() {
		// TODO
	}

	public UserInside createUser() {
		// TODO
		return null;
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

	public EventInside createEvent() {
		// TODO
		return null;
	}

	public void editEvent() {
		// TODO
	}

	public void deleteEvent() {

	}

	public void searchEvent() {

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
