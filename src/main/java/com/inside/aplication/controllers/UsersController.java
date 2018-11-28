package com.inside.aplication.controllers;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.inside.models.entities.User;
import com.inside.persistence.JsonManager;
/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar
 * Controlador para los servicios con respecto a usuarios
 */
@RestController
public class UsersController {


	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createUserInside", method = RequestMethod.POST)
	public String createUser(@Valid @RequestBody User userInside) {
		User user = null;
			try {
				//user.getCredential().insertIntoDataBase();
				user.getImage().insertIntoDataBase();
				user.insertIntoDataBase();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return JsonManager.printJson(user);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser() {
		return "//TODO";
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteUser() {
		return "//TODO";

	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.GET)
	public String searchUser() {
		return "//TODO";

	}

	@RequestMapping(value = "/deactivateUser", method = RequestMethod.POST)
	public String deactivateUser() {
		return "//TODO";

	}
}