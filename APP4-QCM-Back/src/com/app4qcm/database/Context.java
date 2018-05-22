package com.app4qcm.database;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class Context {
	
	Dao<Session, String> _sessionDao;
	Dao<Question, Integer> _questionDao;
	
	public Context(ConnectionSource connectionSource) throws SQLException {
		_sessionDao = DaoManager.createDao(connectionSource, Session.class);
		_questionDao = DaoManager.createDao(connectionSource, Question.class);
	}
	
	public Dao<Session, String> getSessionDao() {
		return _sessionDao;
	}
	
	public Dao<Question, Integer> getQuestionDao() {
		return _questionDao;
	}
	
}
