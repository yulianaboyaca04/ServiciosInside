package com.inside.aplication.controllers;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inside.models.dao.EventsManager;
import com.inside.models.dao.InsideManager;
import com.inside.models.dto.EventDTO;
import com.inside.models.entities.Event;
import com.inside.models.entities.EventInside;
import com.inside.persistence.JsonManager;

/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar Clase que se encarga de controlar los
 *         servicios requeridos con respecto a los eventos
 */
@RestController
public class EventsController {

	@RequestMapping(value = "/createEvent", method = RequestMethod.POST)
	public String createEvent(@Valid @RequestBody EventDTO eventDTO) {
		EventInside event = null;
		try {
			event = eventDTO.generateEvent();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return JsonManager.printJson(event);
//		
//		InsideManager.getInstance().registerEvent(event);
//		return JsonManager.printJson(event); // TODO cambiar por un log de la creacion
	}

	@RequestMapping(value = "/editEvent", method = RequestMethod.POST)
	public String editEvent() {
		return "//TODO";
	}

	@RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
	public String deleteEvent() {
		return "//TODO";

	}

	@RequestMapping(value = "/searchEvent", method = RequestMethod.GET)
	public String searchEvent() {
		return "//TODO";

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * retorna la lista de todos los eventos creados con la aplicacion
	 * 
	 * @return jSon lista de eventos
	 */
	@RequestMapping("/getEventsOld")
	public String getEvents() {
		return JsonManager.printJson(EventsManager.getInstance().getEvents());
	}
	
	/*
	 {
	"idEvent": "1",
	"idCity": "1",
	"idUserCreator": "1",
	"nameEvent": "1",
	"coordinates": "1",
	"capacity": "1",
	"dateStart": "1",
	"dateFinish": "1",
	"descriptionEvent": "1",
	"rules": "1",
	"price": "1",
	"howToBuy": "1"
	}
	 */

	/**
	 * Metodo que crea un evento y lo almacena
	 * 
	 * @param idEvent
	 * @param idCity
	 * @param idUserCreator
	 * @param nameEvent
	 * @param coordinatidUserCreatores
	 * @param capacity
	 * @param dateStart
	 * @param dateFinish
	 * @param descriptionEvent
	 * @param rules
	 * @param price
	 * @param howToBuy
	 * @return mensaje tipo log de lo sucedido
	 */
	@RequestMapping("/createEvent")
	public String createUser(@RequestParam(value = "idEvent", defaultValue = "") String idEvent,
			@RequestParam(value = "idCity", defaultValue = "") String idCity,
			@RequestParam(value = "idUserCreator", defaultValue = "") String idUserCreator,
			@RequestParam(value = "nameEvent", defaultValue = "") String nameEvent,
			@RequestParam(value = "coordinates", defaultValue = "") String coordinates,
			@RequestParam(value = "capacity", defaultValue = "") String capacity,
			@RequestParam(value = "dateStart", defaultValue = "") String dateStart,
			@RequestParam(value = "dateFinish", defaultValue = "") String dateFinish,
			@RequestParam(value = "descriptionEvent", defaultValue = "") String descriptionEvent,
			@RequestParam(value = "rules", defaultValue = "") String rules,
			@RequestParam(value = "price", defaultValue = "") String price,
			@RequestParam(value = "howToBuy", defaultValue = "") String howToBuy) {
		Event ev = EventsManager.getInstance().createEvent(idEvent, idCity, idUserCreator, nameEvent, coordinates, capacity, dateStart,
				dateFinish, descriptionEvent, rules, price, howToBuy);
		EventsManager.getInstance().addEvent(ev);
		System.out.println(ev.toString());
		return ev.toString();// TODO cambiar por un log de la creacion
	}

	/**
	 * servicio para la creacion de un evento mediante metodo post
	 * 
	 * @param event
	 * @return mensaje tipo log de lo sucedido
	 */
	@RequestMapping(value = "/createEventOld", method = RequestMethod.POST)
	public String createEvent(@Valid @RequestBody Event event) {
		System.out.println(JsonManager.printJson(event));
		EventsManager.getInstance().addEvent(event);
		return JsonManager.printJson(event); // TODO cambiar por un log de la creacion
	}
}