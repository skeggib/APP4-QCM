package com.app4qcm.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Context {
	
	Connection _connection;
	
	public Context(String path) throws SQLException {
		_connection = DriverManager.getConnection("jdbc:sqlite:" + path);
	}
	
	public void close() throws SQLException {
		_connection.close();
	}

	public void reset() throws SQLException {
		String table_creation = 
						"DROP TABLE IF EXISTS Question;" +
						"DROP TABLE IF EXISTS Session;" +
						"CREATE TABLE Session(" +
							"VARCHAR name NOT NULL," +
							"PRIMARY KEY(name)" +
						");";
		
		_connection.createStatement().execute(table_creation);
	}
	
	public void addSession(Session session) {
		
	}
	
}
