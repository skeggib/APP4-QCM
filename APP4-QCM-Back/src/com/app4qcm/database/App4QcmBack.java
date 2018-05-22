package com.app4qcm.database;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class App4QcmBack {

	public static void main(String[] args) {
		
		ConnectionSource connectionSource;
		Context context;
		
		try {
			System.out.println("Connecting to database...");
			connectionSource = new JdbcConnectionSource("jdbc:sqlite:db.sqlite");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		try {
			System.out.println("Setting up the context...");
			context = new Context(connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
	}

}
