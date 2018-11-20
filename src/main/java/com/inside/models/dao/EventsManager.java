package com.inside.models.dao;

import java.util.ArrayList;

import com.inside.models.entities.Event;
/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar
 *
 */
public class EventsManager {

//---------------------------------------------------------------------------------------
	/**
	 * Uso del patron singleton para obtener instancias de esta clase de ser necesario
	 */
	private static EventsManager em;
	public static EventsManager getInstance() {
		if (em == null) {
			em = new EventsManager();
		}
		return em;
	}
//----------------------------------------------------------------------------------------
	/**
	 * lista de los eventos que se han creado
	 */
	private ArrayList<Event> events;

	/**
	 * Metodo constructor 
	 */
	private EventsManager() {
		events = new ArrayList<>();
	}

	/**
	 * metodo utilizado para la creacion de diferentes eventos
	 * @param idEvent
	 * @param idCity
	 * @param idUserCreator
	 * @param nameEvent
	 * @param coordinates
	 * @param capacity
	 * @param dateStart
	 * @param dateFinish
	 * @param descriptionEvent
	 * @param rules
	 * @param price
	 * @param howToBuy
	 * @return Evento creado o un mensaje de excepcion en caso de ser necesario.
	 */
	public Event createEvent(String idEvent, String idCity, String idUserCreator, String nameEvent, String coordinates,
			String capacity, String dateStart, String dateFinish, String descriptionEvent, String rules, String price,
			String howToBuy) {
		return new Event(idEvent, idCity, idUserCreator, nameEvent, coordinates, capacity, dateStart, dateFinish,
				descriptionEvent, rules, price, howToBuy);
	}

	/**
	 * Metodo para agregar un evento a la lista 'events'
	 * @param event
	 */
	public void addEvent(Event event) {
		events.add(event);
	}

	/**
	 * Metodo para obtener todos los eventos que posee la lista 'events'
	 * @return lista de eventos
	 */
	public ArrayList<Event> getEvents() {
		return events;
	}
}