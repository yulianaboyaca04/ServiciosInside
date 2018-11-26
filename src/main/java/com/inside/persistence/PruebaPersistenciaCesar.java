package com.inside.persistence;

import com.inside.models.dao.InsideManager;
import com.inside.models.entities.EventInside;

public class PruebaPersistenciaCesar {
	
	public static void main(String[] args) {
		
		EventInside ev = InsideManager.getInstance().searchEvent("1");
		System.out.println(JsonManager.printJson(ev));
		
	}
}
