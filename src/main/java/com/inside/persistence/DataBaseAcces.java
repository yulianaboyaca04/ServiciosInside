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
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost/inside?" + "user=root&password=inside&serverTimezone=UTC");

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
