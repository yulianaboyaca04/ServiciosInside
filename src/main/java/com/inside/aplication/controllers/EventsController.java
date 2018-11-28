package com.inside.aplication.controllers;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inside.models.dao.InsideManager;
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
		//			address.insertIntoDataBase();owToBuy.insertIntoDataBase();
//			EventInside event = eventDTO.generateEvent();
//			event.setAddress(address);
//			event.setHowToBuy(howToBuy);
		try {
			event.getAddress().insertIntoDataBase();
			event.getHowToBuy().insertIntoDataBase();
			event.getEventDate().insertIntoDataBase();
			event.insertIntoDataBase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public String searchEvent(@RequestParam(value = "idEvent", defaultValue = "") String idEvent) {
		Event ev = InsideManager.getInstance().searchEvent(idEvent);
		return JsonManager.printJson(ev);
	}
}