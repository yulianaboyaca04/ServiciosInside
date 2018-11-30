package com.inside.persistence;

import com.inside.models.dao.InsideManager;

public class PruebaPersistenciaCesar {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(JsonManager.printJson(InsideManager.getInstance().getEvents()));
		
		long end = System.currentTimeMillis();

		System.out.println((end - start) + " ms");
	}
}
