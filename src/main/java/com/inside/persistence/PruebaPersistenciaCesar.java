package com.inside.persistence;

import java.sql.Date;
import java.util.ArrayList;

import com.inside.models.entities.Credentials;
import com.inside.models.entities.CredentialsType;
import com.inside.models.entities.Image;
import com.inside.models.entities.Interest;
import com.inside.models.entities.User;

public class PruebaPersistenciaCesar {
	
	public static void main(String[] args) {
		
//		EventInside ev = InsideManager.getInstance().searchEvent("1");
//		System.out.println(JsonManager.printJson(ev));
		
//		try {
//			System.out.println(DataBaseAcces.getKeyNextVal("EVENTS", "id_event"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println();
//		System.out.println(JsonManager.printJson(InsideManager.getInstance().listAllEvents()));
	
		User us = new User("2", new Credentials("2", new CredentialsType("1", "Facebook"), "yuli", "iluy") , new Image("2", "content"), "yuliana", "boyaca", new Date(System.currentTimeMillis()), "guapa", new ArrayList<Interest>());
		System.out.println(JsonManager.printJson(us));
/*
 		metodos listos:
 		
 		mostrar todos los eventos creados:: InsideManager.getInstance().listAllEvents()
 		borrar usuario y tablas asociadas evento.removeFromDatabase();

 	
 	
 	
 */

	}
}
