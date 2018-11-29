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
import com.inside.models.dao.InsideManagercesar;
import com.inside.models.entities.Event;
import com.inside.persistence.JsonManager;

/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar Clase que se encarga de controlar los
 *         servicios requeridos con respecto a los eventos
 */
@RestController
public class EventsController {

	@RequestMapping(value = "/createEventInside", method = RequestMethod.POST)
	public String createEvent(@Valid @RequestBody Event event) {
		try {
			InsideManagercesar.getInstance().createEvent(event);
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
			InsideManagercesar.getInstance().editEvent(eventEdited);
		} catch (EventDoesntExists | SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Event edited";
	}

	@RequestMapping(value = "/deleteEvent", method = RequestMethod.GET)
	public String deleteEvent(@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		try {
			InsideManagercesar.getInstance().deleteEvent(idEvent);
		} catch (EventDoesntExists | SQLException e) {
			return e.getMessage();
		}
		return "Event deleted";
	}

	@RequestMapping(value = "/searchEvent", method = RequestMethod.GET)
	public String searchEvent(@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		Event ev = InsideManager.getInstance().searchEvent(idEvent);
		return JsonManager.printJson(ev);
	}

	@RequestMapping(value = "/listEventsByNearness", method = RequestMethod.GET)
	public String listEventsByNearness(@RequestParam(value = "latittude", defaultValue = "") String latittude,
			@RequestParam(value = "longitude", defaultValue = "") String longitude) {
		return JsonManager.printJson(InsideManagercesar.getInstance().getEventsByNearnes(Float.parseFloat(latittude),
				Float.parseFloat(longitude)));

	}

}