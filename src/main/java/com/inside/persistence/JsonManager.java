package com.inside.persistence;
	
import java.io.IOException;
import com.google.gson.Gson;

/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar
 *
 */
public class JsonManager {

	/**
	 * Escribe un objeto determinado en un Json
	 * @param object
	 * @return Json	
	 * @throws IOException
	 */
	public static String printJson(Object object){
		Gson gson = new Gson();
		return gson.toJson(object);
	}
}