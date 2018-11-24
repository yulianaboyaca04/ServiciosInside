package com.inside.persistence;

import java.sql.*;

public class DataBaseAcces {

	private Connection connection;
	private Statement statement;

	//----------------Patron Singleton----------------------------------------------------------------
	private static DataBaseAcces dataBaseAcces;
	private DataBaseAcces() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("URL"+ ((char)61) +"%2Fsignin%2Fapex%2FCom");
			connection = DriverManager
					.getConnection("jdbc:mysql://myinsidebd.cnluui5kegb8.us-east-1.rds.amazonaws.com/inside?" + "user=insideroot55&password=c96y99n94f97&serverTimezone=UTC");

			statement = connection.createStatement();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static DataBaseAcces getInstance() {
		if (dataBaseAcces == null) {
			dataBaseAcces = new DataBaseAcces();
		}
		return dataBaseAcces;
	}
	//--------------Fin del patron Singleton----------------------------------------------------------------

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	
}
