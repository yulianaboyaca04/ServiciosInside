package com.inside.aplication.controllers;

import java.sql.Date;
import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inside.exceptions.UserAlreadyExists;
import com.inside.models.dao.InsideManager;
import com.inside.models.dao.UsersManager;
import com.inside.models.entities.Credentials;
import com.inside.models.entities.Image;
import com.inside.models.entities.User;
import com.inside.models.entities.UserInside;
import com.inside.persistence.JsonManager;
/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar
 * Controlador para los servicios con respecto a usuarios
 */
@RestController
public class UsersController {

	/**
	 * String idUser, Credentials credential, Image image, String nameUser, String lastName,
			Date birthDate, String nickename
	 */
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@RequestParam(value = "idUser", defaultValue = "") String idUser, 
			@RequestParam(value = "credentials", defaultValue = "") Credentials credential,
			@RequestParam(value = "image", defaultValue = "") Image image,
			@RequestParam(value = "nameUser", defaultValue = "") String nameUser,
			@RequestParam(value = "lastName", defaultValue = "") String lastName,
			@RequestParam(value = "birthDate", defaultValue = "") Date birthDate,
			@RequestParam(value = "nickname", defaultValue = "") String nickname) {
//		UserInside userInside;
//		try {
//			userInside = InsideManager.getInstance().createUser(idUser, credential, image, nameUser, lastName, birthDate, nickname, null);
//			System.out.println(userInside.toString());
//			userInside.insertIntoDataBase();
//			return userInside.toString();
//		} catch (UserAlreadyExists | SQLException e) {
//			return e.getMessage();
//		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser() {
		return "//TODO";
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * metodo que permite crear usuarios mediante el metodo get
	 * @param idUser
	 * @param nameUser
	 * @param lastName
	 * @param birthDate
	 * @param email
	 * @param password
	 * @param nickname
	 * @return mensaje tipo log de lo sucedido
	 */
	//http://localhost:8092/createUser?idUser=1&nameUser=Yuliana&lastName=Boyaca&birhtDate=04,16,1999&email=Yuli@gmail.com&password=cuchurrumino&nickname=yuli
	public String createUser(
			@RequestParam(value = "idUser", defaultValue = "") String idUser, 
			@RequestParam(value = "nameUser", defaultValue = "") String nameUser,
			@RequestParam(value = "lastName", defaultValue = "") String lastName,
			@RequestParam(value = "birthDate", defaultValue = "") String birthDate,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "password", defaultValue = "") String password,
			@RequestParam(value = "nickname", defaultValue = "") String nickname) {
		User us;
		try {
			UsersManager usmn = UsersManager.getInstance();
			us = usmn.createUser(idUser, nameUser, lastName, birthDate, email, password, nickname);
			UsersManager.getInstance().addUser(us);
			System.out.println(us.toString());
			return us.toString();
		} catch (UserAlreadyExists e) {
			return e.getMessage();
		}
		//TODO cambiar por un log de la creacion
	}
	
	/**
	 * metodo que permite crear usuarios mediante el metodo post
	 * @param user
	 * @return mensaje tipo log de lo sucedido
	 */
	@RequestMapping(value = "/createUserOld", method = RequestMethod.POST)
	public String createUser1(@Valid @RequestBody User user) {
		System.out.println(JsonManager.printJson(user));
		UsersManager.getInstance().addUser(user);
		return user.toString();		//TODO cambiar por un log de la creacion
	}
	
	/**
	 * retorna la lista de todos los usuarios creados con la aplicacion
	 * 
	 * @return jSon lista de eventos
	 */
	@RequestMapping("/getUsersOld"
			+ "") 
	public String getUsers() {
		return JsonManager.printJson(UsersManager.getInstance().getUsers());
	}
	

}