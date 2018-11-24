package com.inside.persistence;

import java.sql.SQLException;

import com.inside.models.entities.Address;
import com.inside.models.entities.CredentialsType;
import com.inside.models.entities.EventDate;
import com.inside.models.entities.EventInside;
import com.inside.models.entities.Gallery;
import com.inside.models.entities.HowToBuy;

public class PruebaPersistenciaCesar {
	
	public static void main(String[] args) {
		
		Address ad = new Address("2", 12, 12, "paname", "casa");
//		AttendanceHistory atde = new AttendanceHistory(new UserInside, event);
//		Credentials cre = new Credentials("2", , user, passwordOrToken);
		CredentialsType cerdt;
		EventDate eventdate;
		EventInside eventInside;
		Gallery galer;
		HowToBuy howtobuy;
		try {
			ad.insertIntoDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
