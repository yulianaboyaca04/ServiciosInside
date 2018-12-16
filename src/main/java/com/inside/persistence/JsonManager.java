package com.inside.persistence;
	
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		return gson.toJson(object);
	}
}