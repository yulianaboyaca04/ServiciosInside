package com.inside.aplication.controllers;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inside.models.dao.InsideManager;
import com.inside.models.entities.Event;
import com.inside.models.entities.User;
import com.inside.persistence.JsonManager;

/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar, Gil Nilson Controlador para los
 *         servicios de la aplicacion inside
 */
@RestController
public class InsideController {

//	/**
//	 * 
//	 */
//	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
//	public String registerUser() {
//		return "//TODO";
//	}
//
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createUserInside", method = RequestMethod.POST)
	public String createUser(@Valid @RequestBody User userInside) {
		try {
			userInside.getCredential().insertIntoDataBase();
			userInside.getImage().insertIntoDataBase();
			userInside.insertIntoDataBase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonManager.printJson(userInside);
	}
//
//	/**
//	 * 
//	 */
//	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
//	public String editUser() {
//		return "//TODO";
//	}
//
//	/**
//	 * 
//	 */
//	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
//	public String deleteUser() {
//		return "//TODO";
//
//	}
//
//	@RequestMapping(value = "/searchUser", method = RequestMethod.GET)
//	public String searchUser() {
//		return "//TODO";
//
//	}
//
//	@RequestMapping(value = "/deactivateUser", method = RequestMethod.POST)
//	public String deactivateUser() {
//		return "//TODO";
//
//	}

	// ---------------------------------event-------------------------

//	@RequestMapping(value = "/createEvent", method = RequestMethod.POST)
//	public String createEvent() {
//		return "//TODO";
//	}
//
//	@RequestMapping(value = "/editEvent", method = RequestMethod.POST)
//	public String editEvent() {
//		return "//TODO";
//	}
//
//	@RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
//	public String deleteEvent() {
//		return "//TODO";
//
//	}
//
//	@RequestMapping(value = "/searchEvent", method = RequestMethod.GET)
//	public String searchEvent() {
//		return "//TODO";
//
//	}

	// ------------------------------------------------------------------------------

	@RequestMapping(value = "/subscribeToEvent", method = RequestMethod.POST)
	public String subscribeToEvent(User user, Event event) {
		return "//TODO";

	}

	@RequestMapping(value = "/registerAttendanceToEvent", method = RequestMethod.POST)
	public String registerAttendanceToEvent(User user, Event event) {
		return "//TODO";

	}

	@RequestMapping(value = "/registerViewToEvent", method = RequestMethod.POST)
	public String registerViewToEvent(User user, Event event) {
		return "//TODO";

	}

	// ----------------------------------getters&setters---------------------------------

	@RequestMapping(value = "/getEvents", method = RequestMethod.GET)
	public String getEvents() {
		return JsonManager.printJson(InsideManager.getInstance().getEvents());
	}

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public String getUsers() {
		return "//TODO";
	}

	@RequestMapping(value = "/getSuscriptions", method = RequestMethod.GET)
	public String getSuscriptions() {
		return "//TODO";
	}

	@RequestMapping(value = "/getAttendanceHistory", method = RequestMethod.GET)
	public String getAttendanceHistory() {
		return "//TODO";
	}

	@RequestMapping(value = "/getViewsHistory", method = RequestMethod.GET)
	public String getViewsHistory() {
		return "//TODO";

	}

}
