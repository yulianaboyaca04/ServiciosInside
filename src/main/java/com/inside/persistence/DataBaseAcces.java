package com.inside.persistence;

import java.sql.*;

public class DataBaseAcces {

	private Connection connection;
	private Statement statement;

	//----------------Patron Singleton----------------------------------------------------------------
	private static DataBaseAcces dataBaseAcces;
	private DataBaseAcces() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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

	//---------------------------------------------------------------------------------------
	public static String getKeyNextVal(String tableName, String columName) throws SQLException {
		ResultSet resultSet = DataBaseAcces.getInstance().getStatement()
				.executeQuery("SELECT "+ columName.toUpperCase() + " FROM " +  tableName.toUpperCase() + " ORDER BY " + columName.toUpperCase() + " *1");
		String idMax = "";
		while (resultSet.next()) {
			idMax = resultSet.getString(1);
		}
		return idMax;
	}
}
