package com.inside.exceptions;

public class UserDoesntExists extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDoesntExists() {
		super("USER_DOESNT_EXISTS");
	}
}
