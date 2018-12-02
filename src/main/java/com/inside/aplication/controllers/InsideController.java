package com.inside.aplication.controllers;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inside.exceptions.EventDoesntExists;
import com.inside.exceptions.UserDoesntExists;
import com.inside.models.dao.InsideManager;
import com.inside.models.entities.Credentials;
import com.inside.models.entities.Event;
import com.inside.models.entities.Interest;
import com.inside.models.entities.User;
import com.inside.persistence.JsonManager;

/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar, Gil Nilson Controlador para los
 *         servicios de la aplicacion inside
 */
@RestController
public class InsideController {

	// ---------------------------------user-------------------------
	@RequestMapping(value = "/createUserInside", method = RequestMethod.POST)
	public String createUser(@Valid @RequestBody User userInside) {
		try {
			InsideManager.getInstance().registerUser(userInside);
		} catch (SQLException e) {
			return e.getMessage();
		}
		return "User created";
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(@Valid @RequestBody User userEdited) {
		try {
			InsideManager.getInstance().editUser(userEdited);
		} catch (UserDoesntExists | SQLException e) {
			return e.getMessage();
		}
		return "User edited";
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		try {
			InsideManager.getInstance().deleteUser(idUser);
		} catch (SQLException | UserDoesntExists e) {
			return e.getMessage();
		}
		return "User deleted";
	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.GET)
	public String searchUser(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		User user;
		try {
			user = InsideManager.getInstance().searchUser(idUser);
		} catch (UserDoesntExists e) {
			return e.getMessage();
		}
		return JsonManager.printJson(user);
	}

	@RequestMapping(value = "/deactivateUser", method = RequestMethod.POST)
	public String deactivateUser() {
		// TODO
		return "//TODO";

	}
	
	@RequestMapping(value = "/autenthicate", method = RequestMethod.POST)
	public String autenthicate(@Valid @RequestBody Credentials credentials) {
		User user;
		try {
			user = InsideManager.getInstance().autenthicate(credentials);
		} catch (UserDoesntExists e) {
			return e.getMessage();
		}
		return JsonManager.printJson(user);
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
	
	@RequestMapping(value = "/listEventsByPopularity", method = RequestMethod.GET)
	public String listEventsByNearness() {
		return JsonManager.printJson(InsideManager.getInstance().getEventsbyPopularity());
	}
	
	@RequestMapping(value = "/listEventsByPriceAscending", method = RequestMethod.GET)
	public String listEventsByPriceAscending() {
		return JsonManager.printJson(InsideManager.getInstance().getEventsByPriceAscending());
	}
	
	@RequestMapping(value = "/listEventsByPriceDescending", method = RequestMethod.GET)
	public String listEventsByPriceDescending() {
		return JsonManager.printJson(InsideManager.getInstance().getEventsByPriceDescending());
	}

	// ------------------------------------------------------------------------------
	
	@RequestMapping(value = "/addInterest", method = RequestMethod.POST)
	public void addInterest(@Valid @RequestBody Interest interest) {
		InsideManager.getInstance().addInterest(interest);
		
	}
	
	@RequestMapping(value = "/subscribeToEvent", method = RequestMethod.GET)
	public String subscribeToEvent(@RequestParam(value = "idUser", defaultValue = "") String idUser, 
			@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		try {
			InsideManager.getInstance().subscribeToEvent(idUser, idEvent);
		} catch (SQLException | UserDoesntExists | EventDoesntExists e) {
			return e.getMessage();
		}
		return "Suscription created";

	}

	@RequestMapping(value = "/registerAttendanceToEvent", method = RequestMethod.GET)
	public String registerAttendanceToEvent(@RequestParam(value = "idUser", defaultValue = "") String idUser, 
			@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		try {
			InsideManager.getInstance().registerAttendanceToEvent(idUser, idEvent);
		} catch (SQLException | UserDoesntExists | EventDoesntExists e) {
			return e.getMessage();
		}
		return "Attendance noted";
	}

	@RequestMapping(value = "/registerViewToEvent", method = RequestMethod.GET)
	public String registerViewToEvent(@RequestParam(value = "idUser", defaultValue = "") String idUser, 
			@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		try {
			InsideManager.getInstance().registerViewToEvent(idUser, idEvent);
		} catch (SQLException | UserDoesntExists | EventDoesntExists e) {
			return e.getMessage();
		}
		return "View noted";
	}

	// ----------------------------------getters&setters---------------------------------
	@RequestMapping(value = "/getInterests", method = RequestMethod.GET)
	public String getInterests() {
		return JsonManager.printJson(InsideManager.getInstance().getInterests());
	}
	
	@RequestMapping(value = "/getEvents", method = RequestMethod.GET)
	public String getEvents() {
		return JsonManager.printJson(InsideManager.getInstance().getEvents());
	}
	
	@RequestMapping(value = "/getEventCards", method = RequestMethod.GET)
	public String getEventCards() {
		return JsonManager.printJson(InsideManager.getInstance().getEventCards());
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public String getUsers() {
		return JsonManager.printJson(InsideManager.getInstance().getUsers());
	}

	@RequestMapping(value = "/getSuscriptions", method = RequestMethod.GET)
	public String getSuscriptions() {
		return JsonManager.printJson(InsideManager.getInstance().getSuscriptions());
	}

	@RequestMapping(value = "/getAttendanceHistory", method = RequestMethod.GET)
	public String getAttendanceHistory() {
		return JsonManager.printJson(InsideManager.getInstance().getAttendanceHistory());
	}

	@RequestMapping(value = "/getViewsHistory", method = RequestMethod.GET)
	public String getViewsHistory() {
		return JsonManager.printJson(InsideManager.getInstance().getViewsHistory());
	}
}