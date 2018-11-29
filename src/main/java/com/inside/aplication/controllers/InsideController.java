package com.inside.aplication.controllers;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inside.exceptions.EventDoesntExists;
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



	/**
	 * Registra el usuario en la base de datos
	 * @return
	 */
	@RequestMapping(value = "/createUserInside", method = RequestMethod.POST)
	public String createUser(@Valid @RequestBody User userInside) {
		try {
			userInside.getCredential().insertIntoDataBase();
			userInside.getImage().insertIntoDataBase();
			userInside.insertIntoDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return JsonManager.printJson(userInside);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		return "//TODO";
	}

	/**
	 * Elimina el usuario de la base de datos y de la logica
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		try {
			InsideManager.getInstance().deleteUser(idUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Successfully deleted user";
	}

	/**
	 * Servicio para buscar un determinado usuario por su id
	 * @param idUser
	 * @return
	 */
	@RequestMapping(value = "/searchUser", method = RequestMethod.GET)
	public String searchUser(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		User user = InsideManager.getInstance().searchUser(idUser);
		return JsonManager.printJson(user);
	}

	@RequestMapping(value = "/deactivateUser", method = RequestMethod.POST)
	public String deactivateUser() {
		return "//TODO";

	}

	// ---------------------------------event-------------------------
	
	@RequestMapping(value = "/createEventInside", method = RequestMethod.POST)
	public String createEvent(@Valid @RequestBody Event event) {
		try {
			InsideManager.getInstance().createEvent(event);
		} catch (SQLException e) {
			return e.getMessage();
		}
		return "Event created";

//		
//		InsideManager.getInstance().registerEvent(event);
//		return JsonManager.printJson(event); // TODO cambiar por un log de la creacion
	}

	@RequestMapping(value = "/editEvent", method = RequestMethod.POST)
	public String editEvent(@Valid @RequestBody Event eventEdited) {
		try {
			InsideManager.getInstance().editEvent(eventEdited);
		} catch (EventDoesntExists | SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Event edited";
	}

	@RequestMapping(value = "/deleteEvent", method = RequestMethod.GET)
	public String deleteEvent(@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		try {
			InsideManager.getInstance().deleteEvent(idEvent);
		} catch (EventDoesntExists | SQLException e) {
			return e.getMessage();
		}
		return "Event deleted";
	}

	@RequestMapping(value = "/searchEvent", method = RequestMethod.GET)
	public String searchEvent(@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		Event ev;
		try {
			ev = InsideManager.getInstance().searchEvent(idEvent);
		} catch (EventDoesntExists e) {
			return e.getMessage();
		}
		return JsonManager.printJson(ev);
	}

	@RequestMapping(value = "/listEventsByNearness", method = RequestMethod.GET)
	public String listEventsByNearness(@RequestParam(value = "latittude", defaultValue = "") String latittude,
			@RequestParam(value = "longitude", defaultValue = "") String longitude) {
		return JsonManager.printJson(InsideManager.getInstance().getEventsByNearnes(Float.parseFloat(latittude),
				Float.parseFloat(longitude)));

	}

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
		return JsonManager.printJson(InsideManager.getInstance().getUsers());
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
