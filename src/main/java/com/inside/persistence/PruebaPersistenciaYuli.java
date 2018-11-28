package com.inside.persistence;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.sql.Date;
import java.sql.SQLException;

import com.inside.aplication.controllers.UsersController;
import com.inside.models.entities.Address;
import com.inside.models.entities.AttendanceHistory;
import com.inside.models.entities.Credentials;
import com.inside.models.entities.CredentialsType;
import com.inside.models.entities.EventDate;
import com.inside.models.entities.Event;
import com.inside.models.entities.HowToBuy;
import com.inside.models.entities.Image;
import com.inside.models.entities.Interest;
import com.inside.models.entities.Rule;
import com.inside.models.entities.RulesType;
import com.inside.models.entities.Suscription;
import com.inside.models.entities.User;
import com.inside.models.entities.ViewsHistory;

public class PruebaPersistenciaYuli {
	
	public Image img;
	public HowToBuy htb;
	public Address adre;
	public AttendanceHistory ah;
	public Credentials cr;
	public CredentialsType cT;
	public Event ev;
	public EventDate ed;
	public Interest interest;
	public Rule rule;
	public RulesType rt;
	public Suscription sus;
	public User uI;
	public ViewsHistory vh;
	
/*
 * ---------------METODOS LISTOS-------------------------
 * 
 * 1. servicio de mostrar todos los eventos creados:: /getListAllEvents
 * 
 * 
 * 
 */
	
	public static void main(String[] args) {
		//PruebaPersistenciaYuli py =  new PruebaPersistenciaYuli();
		//py.testSearchPersistence();
		UsersController us =  new UsersController();
		us.deleteUser("2");
	}
	
	private void testSearchPersistence() {
		try {
			User userInside = User.searchUserIntoDatabase("2");
			System.out.println(JsonManager.printJson(userInside));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void generarRegistros() {
		//img = new Image("3", "Image_url_2");
		htb = new HowToBuy("20", "pago en efectivo", true, 1000);
		adre = new Address("20", 247, 244, "Duitama", "B.solano");
		
		ed = new EventDate("39", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()+10000));
		uI= new User();
		img =  new Image("30", "soy nico en un string");
		
		cT =  new CredentialsType("20", "facebook");
		cr =  new Credentials("20", cT, "Nico", "Te pasas");
		//--- array intereces----//
		interest = new Interest("60","Dogs");
		Interest interest1 = new Interest("1","Dogser");
		Interest interest2 = new Interest("2","Dogsito");
		ArrayList<Interest> arrayInteres  =  new ArrayList<>();
		arrayInteres.add(interest);
		arrayInteres.add(interest1);
		arrayInteres.add(interest2);
			//--- fin array intereces---//
		//--- array rules ----//
		rt =  new RulesType("30","Edad Minima","edad minima de las personas que pueden asistir al evento");
		rule = new Rule("30",rt,"18");
		Rule rule1 = new Rule("2", rt, "100");
		
		Rule rule2 = new Rule("29", rt, "15");
		Rule rule3 = new Rule("3", rt, "80");
		
		ArrayList<Rule> arrayRules  =  new ArrayList<>();
		
		arrayRules.add(rule);
		arrayRules.add(rule1);
		arrayRules.add(rule2);
		arrayRules.add(rule3);
			//--- fin array rules---//
		//--------user-------//
		ArrayList<Image> gallery  =  new ArrayList<>();
		gallery.add(img);
		
		GregorianCalendar d = new GregorianCalendar(1996, GregorianCalendar.DECEMBER, 9);
		uI =  new User("42",cr,img,"Yuli","cardozo", new Date(d.getTimeInMillis()),"nico",arrayInteres);
		//------ fin user----//
		ev = new Event("12",uI,htb,adre,ed,"Aginaldo Samaquence","Esto se va descontrolar",gallery,arrayInteres,arrayRules);
//		System.out.println(ev.toString());
	//	uI= new UserInside(idUser, credential, image, nameUser, lastName, birthDate, nikename, userInteres)
		
//			try {
//				img.insertIntoDataBase();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				htb.insertIntoDataBase();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
//			try {
//				ed.insertIntoDataBase();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				cT.insertIntoDataBase();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				cr.insertIntoDataBase();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				interest.insertIntoDataBase();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				rt.insertIntoDataBase();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				rule3.insertIntoDataBase();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				uI.insertIntoDataBase();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				adre.insertIntoDataBase();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//			try {
//				ev.insertIntoDataBase();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
			
	}
}
