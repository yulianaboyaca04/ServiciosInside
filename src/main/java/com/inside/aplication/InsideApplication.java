package com.inside.aplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inside.models.dao.InsideManager;
import com.inside.models.dao.InsideManagercesar;
/**
 * 
 * @author Boyaca Yuliana, Cardozo Cesar
 * 
 * Clase principal de la aplicacion (runner)
 */
@SpringBootApplication
public class InsideApplication {

	public static void main(String[] args) {
		InsideManager.getInstance();
		InsideManagercesar.getInstance();
		//Cambia la propiedad del puerto
		System.setProperty("server.port", "5000");
		//lanza los servicios de la aplicacion
		SpringApplication.run(InsideApplication.class, args);
	}
}