package com.inside.aplication.controllers;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inside.exceptions.EventDoesntExists;
import com.inside.exceptions.SuscriptionDoesntExists;
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

	// todo editar el usuario tambien en los eventos creados
	// ---------------------------------user-------------------------
	@RequestMapping(value = "/createUserInside", method = RequestMethod.POST)
	public String createUser(@Valid @RequestBody User userInside) {
		try {
			return JsonManager.printJson(InsideManager.getInstance().registerUser(userInside));
		} catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(@Valid @RequestBody User userEdited) {
		try {
			return JsonManager.printJson(InsideManager.getInstance().editUser(userEdited));
		} catch (UserDoesntExists | SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		try {
			InsideManager.getInstance().deleteUser(idUser);
		} catch (SQLException | UserDoesntExists e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "User deleted";
	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.GET)
	public String searchUser(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		try {
			return JsonManager.printJson(InsideManager.getInstance().searchUser(idUser));
		} catch (UserDoesntExists e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/autenthicate", method = RequestMethod.POST)
	public String autenthicate(@Valid @RequestBody Credentials credentials) {
		try {
			return JsonManager.printJson(InsideManager.getInstance().autenthicate(credentials));
		} catch (UserDoesntExists e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/getSuscriptionsByUser", method = RequestMethod.GET)
	public String getSuscriptionsByUser(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		return JsonManager.printJson(InsideManager.getInstance().getSuscriptionsByUser(idUser));
	}

	@RequestMapping(value = "/getEventsCreated", method = RequestMethod.GET)
	public String getEventsCreated(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		return JsonManager.printJson(InsideManager.getInstance().getEventsCreated(idUser));
	}

	@RequestMapping(value = "/getEventsCreatedAndInsides", method = RequestMethod.GET)
	public String getEventsCreatedAndInsides(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		return JsonManager.printJson(InsideManager.getInstance().getEventsCreatedAndInsides(idUser));
	}
	// ---------------------------------event-------------------------

	@RequestMapping(value = "/createEventInside", method = RequestMethod.POST)
	public String createEvent(@Valid @RequestBody Event event) {
		try {
			return JsonManager.printJson(InsideManager.getInstance().createEvent(event));
		} catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/editEvent", method = RequestMethod.POST)
	public String editEvent(@Valid @RequestBody Event eventEdited) {
		try {
			return JsonManager.printJson(InsideManager.getInstance().editEvent(eventEdited));
		} catch (EventDoesntExists | SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/deleteEvent", method = RequestMethod.GET)
	public String deleteEvent(@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		try {
			InsideManager.getInstance().deleteEvent(idEvent);
			return "Event deleted";
		} catch (EventDoesntExists | SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/searchEvent", method = RequestMethod.GET)
	public String searchEvent(@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		try {
			return JsonManager.printJson(InsideManager.getInstance().searchEvent(idEvent));
		} catch (EventDoesntExists e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/listEventsByInterests", method = RequestMethod.GET)
	public String listEventsByInterests(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		try {
			return JsonManager.printJson(InsideManager.getInstance().getEventsByInterest(idUser));
		} catch (UserDoesntExists e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/listEventsByNearness", method = RequestMethod.GET)
	public String listEventsByNearness(@RequestParam(value = "latittude", defaultValue = "") String latittude,
			@RequestParam(value = "longitude", defaultValue = "") String longitude) {
		return JsonManager.printJson(InsideManager.getInstance().getEventsByNearnes(Float.parseFloat(latittude),
				Float.parseFloat(longitude)));
	}

	@RequestMapping(value = "/listEventsByPopularity", method = RequestMethod.GET)
	public String listEventsByPopularity() {
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

	@RequestMapping(value = "/sponsorEvent", method = RequestMethod.GET)
	public String sponsorEvent(@RequestParam(value = "idEvent", defaultValue = "") String idEvent,
			@RequestParam(value = "daysSponsored", defaultValue = "") String daysSponsored,
			@RequestParam(value = "adminPass", defaultValue = "") String adminPass) {
		if (adminPass.equals("INSIDEPASSWORD")) {
			try {
				Event event = InsideManager.getInstance().searchEvent(idEvent);
				event.setDaysSponsored(Float.parseFloat(daysSponsored));
				InsideManager.getInstance().editEvent(event);
				return "The event with id: " + idEvent + " has been sponsored by " + daysSponsored + " days.";
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
		} else {
			return "IncorrectPassword";
		}
	}

	// ------------------------------------------------------------------------------

	@RequestMapping(value = "/addInterest", method = RequestMethod.POST)
	public String addInterest(@Valid @RequestBody Interest interest) {
		try {
			InsideManager.getInstance().addInterest(interest);
		} catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Interest created";
	}

	@RequestMapping(value = "/subscribeToEvent", method = RequestMethod.GET)
	public String subscribeToEvent(@RequestParam(value = "idUser", defaultValue = "") String idUser,
			@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		try {
			return JsonManager.printJson(InsideManager.getInstance().subscribeToEvent(idUser, idEvent));
		} catch (SQLException | UserDoesntExists | EventDoesntExists e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/unSubscribeToEvent", method = RequestMethod.GET)
	public String unSubscribeToEvent(@RequestParam(value = "idUser", defaultValue = "") String idUser,
			@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		try {
			return JsonManager.printJson(InsideManager.getInstance().unSubscribeToEvent(idUser, idEvent));
		} catch (SQLException | UserDoesntExists | EventDoesntExists e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/getNumberOfSuscriptions", method = RequestMethod.GET)
	public String getNumberOfSuscriptions(@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		return JsonManager.printJson(InsideManager.getInstance().getNumberOfSuscriptions(idEvent) + "");
	}

	@RequestMapping(value = "/checkIfSubscribed", method = RequestMethod.GET)
	public String checkIfSubscribed(@RequestParam(value = "idUser", defaultValue = "") String idUser,
			@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		try {
			return JsonManager.printJson(InsideManager.getInstance().checkIfSubscribed(idUser, idEvent));
		} catch (SuscriptionDoesntExists e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/registerAttendanceToEvent", method = RequestMethod.GET)
	public String registerAttendanceToEvent(@RequestParam(value = "idUser", defaultValue = "") String idUser,
			@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		try {
			InsideManager.getInstance().registerAttendanceToEvent(idUser, idEvent);
		} catch (SQLException | UserDoesntExists | EventDoesntExists e) {
			e.printStackTrace();
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
			e.printStackTrace();
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