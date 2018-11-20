package com.inside.models.dao;

import java.util.HashMap;
import com.inside.exceptions.UserAlreadyExists;
import com.inside.models.entities.User;

/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar
 *
 */
public class UsersManager {

// ---------------------------------------------------------------------------------------
	/**
	 * Uso del patron singleton para obtener instancias de esta clase de ser necesario
	 */
	private static UsersManager usmn;
	public static UsersManager getInstance() {
		if (usmn == null) {
			usmn = new UsersManager();
		}
		return usmn;
	}
//----------------------------------------------------------------------------------------
	/**
	 * lista de usuarios que han sido agregados 
	 */
	private HashMap<String, User> users;

	/**
	 * metodo constructor
	 */
	private UsersManager() {
		users = new HashMap<>();
	}

	/**
	 * metodo utilizado para la creacion de diferentes eventos
	 * @param idUser
	 * @param nameUser
	 * @param lastName
	 * @param birthDate
	 * @param email
	 * @param password
	 * @param nickname
	 * @return Usuario creado o un mensaje de excepcion en caso de ser necesario.
	 * @throws UserAlreadyExists
	 */
	public User createUser(String idUser, String nameUser, String lastName, String birthDate, String email,
			String password, String nickname) throws UserAlreadyExists {
		if (!users.containsKey(email)) {
			return new User(idUser, nameUser, lastName, birthDate, email, password, nickname);
		} else {
			throw new UserAlreadyExists();
		}
	}
	
	/**
	 * Metodo para agregar un evento a la lista 'events'
	 * @param event
	 */
	public void addUser(User user) {
		users.put(user.getEmail(), user);
	}
	
	public HashMap<String, User> getUsers() {
		return users;
	}
	
	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}
}