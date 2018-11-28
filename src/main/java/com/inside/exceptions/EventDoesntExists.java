package com.inside.exceptions;

/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar
 * Excepcion que ocurre cuando se intenta agregar un usuario 
 * con un email que ya se encuentra registrado
 */
@SuppressWarnings("serial")
public class EventDoesntExists extends Exception {
	public EventDoesntExists() {
		super("EVENT_DOESNT_EXISTS");
	}
}
