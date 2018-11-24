package com.inside.persistence;

import java.sql.SQLException;

import com.inside.models.entities.Address;
import com.inside.models.entities.AttendanceHistory;
import com.inside.models.entities.Credentials;
import com.inside.models.entities.Event;
import com.inside.models.entities.EventDate;
import com.inside.models.entities.EventInside;
import com.inside.models.entities.Gallery;
import com.inside.models.entities.HowToBuy;
import com.inside.models.entities.Image;
import com.inside.models.entities.Interest;
import com.inside.models.entities.Regulation;
import com.inside.models.entities.Rule;
import com.inside.models.entities.RulesType;
import com.inside.models.entities.Suscription;
import com.inside.models.entities.UserInside;
import com.inside.models.entities.ViewsHistory;

public class PruebaPersistenciaYuli {
	
	public Image img;
	public Gallery gal;
	public HowToBuy htb;
	public Address adre;
	public AttendanceHistory ah;
	public Credentials cr;
	public EventInside ev;
	public EventDate ed;
	public Interest interest;
	public Regulation regul;
	public Rule rule;
	public RulesType rt;
	public Suscription sus;
	public UserInside uI;
	public ViewsHistory vh;
	
	
	public static void main(String[] args) {
		
	}
	
	private void generarRegistros() {
		img = new Image("2", "Image_url_2");
		
	}
}
