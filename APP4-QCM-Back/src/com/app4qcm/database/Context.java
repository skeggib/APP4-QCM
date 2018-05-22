package com.app4qcm.database;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class Context {
	
	ConnectionSource _connectionSource;
	Dao<Session, String> _sessionDao;
	Dao<Question, Integer> _questionDao;
	
	public Context(String path) throws SQLException {
		_connectionSource = new JdbcConnectionSource("jdbc:sqlite:" + path);
		
		TableUtils.createTableIfNotExists(_connectionSource, Session.class);
		TableUtils.createTableIfNotExists(_connectionSource, Question.class);
		
		_sessionDao = DaoManager.createDao(_connectionSource, Session.class);
		_questionDao = DaoManager.createDao(_connectionSource, Question.class);
	}
	
	public Dao<Session, String> getSessionDao() {
		return _sessionDao;
	}
	
	public Dao<Question, Integer> getQuestionDao() {
		return _questionDao;
	}
	
	public void close() throws IOException {
		if (_connectionSource != null)
			_connectionSource.close();
	}

	public void reset() throws SQLException {
		TableUtils.dropTable(_connectionSource, Question.class, true);
		TableUtils.dropTable(_connectionSource, Session.class, true);
		
		TableUtils.createTableIfNotExists(_connectionSource, Session.class);
		TableUtils.createTableIfNotExists(_connectionSource, Question.class);
	}
	
}
