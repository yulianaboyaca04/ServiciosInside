package com.inside.aplication.controllers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.validation.Valid;

import org.apache.catalina.mbeans.UserMBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inside.models.dao.InsideManager;
import com.inside.models.entities.Event;
import com.inside.models.entities.User;
import com.inside.persistence.DataBaseAcces;
import com.inside.persistence.JsonManager;
/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar
 * Controlador para los servicios con respecto a usuarios
 */
@RestController
public class UsersController {


	/**
	 * Registra el usuario en la base de datos
	 * @return
	 */
	@RequestMapping(value = "/createUserInside", method = RequestMethod.POST)
	public String createUser(@Valid @RequestBody User userInside) {
		try {
			userInside.getCredential().insertIntoDataBase();
			userInside.getImage().insertIntoDataBase();
			userInside.insertIntoDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return JsonManager.printJson(userInside);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		return "//TODO";
	}

	/**
	 * Elimina el usuario de la base de datos y de la logica
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		try {
			InsideManager.getInstance().deleteUser(idUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Successfully deleted user";
	}

	/**
	 * Servicio para buscar un determinado usuario por su id
	 * @param idUser
	 * @return
	 */
	@RequestMapping(value = "/searchUser", method = RequestMethod.GET)
	public String searchUser(@RequestParam(value = "idUser", defaultValue = "") String idUser) {
		User user = InsideManager.getInstance().searchUser(idUser);
		return JsonManager.printJson(user);
	}

	@RequestMapping(value = "/deactivateUser", method = RequestMethod.POST)
	public String deactivateUser() {
		return "//TODO";

	}
}