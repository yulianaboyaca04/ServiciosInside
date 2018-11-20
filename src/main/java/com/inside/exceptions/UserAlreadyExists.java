package com.inside.exceptions;

/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar
 * Excepcion que ocurre cuando se intenta agregar un usuario 
 * con un email que ya se encuentra registrado
 */
@SuppressWarnings("serial")
public class UserAlreadyExists extends Exception {
	public UserAlreadyExists() {
		super("USER_WITH_THE_SAME_EMAIL_ALREADY_EXISTS");
	}
}
