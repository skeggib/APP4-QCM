package com.app4qcm.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Context {
	
	Connection _connection;
	
	public Context(String path) throws SQLException {
		boolean generate = false;
		if (!new File(path).exists())
			generate = true;
		
		_connection = DriverManager.getConnection("jdbc:sqlite:" + path);
		if (generate)
			reset();
	}
	
	public void close() throws SQLException {
		_connection.close();
	}
	
	private static String readInputStream(InputStream stream) throws IOException {
		StringBuilder builder = new StringBuilder();
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		reader.close();
		return builder.toString();
	}

	/**
	 * Resets the database
	 * @throws SQLException
	 */
	public void reset() throws SQLException {		
		try {
			InputStream stream = getClass().getResourceAsStream("/tables_creation.sql");
			_connection.createStatement().executeUpdate(readInputStream(stream));
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a session in the database
	 * @param session
	 * @throws SQLException
	 */
	public void addSession(Session session) throws SQLException {
		insertSession(session);
		if (session.getQuestions() != null)
			for (int i = 0; i < session.getQuestions().size(); ++i)
				insertQuestion(session.getQuestions().get(i), session.getName());
	}
	
	/**
	 * Gets a session by name
	 * @param name
	 * @return The session or null if the session was not found
	 * @throws SQLException
	 */
	public Session getSession(String name) throws SQLException {
		ResultSet set = _connection.createStatement().executeQuery(
				"SELECT session_name FROM Session WHERE session_name = '" + name + "';");
		if (!set.next())
			return null;
		Session session = new Session();
		session.setName(set.getString(1));
		
		set = _connection.createStatement().executeQuery(
				"SELECT question_id, question_text, "
				+ "question_response1, question_correct1, "
				+ "question_response2, question_correct2, "
				+ "question_response3, question_correct3, "
				+ "question_response4, question_correct4 "
				+ "FROM Session JOIN Question ON Session.session_name = Question.session_name "
				+ "WHERE Session.session_name = '" + name + "';");
		
		ArrayList<Question> questions = new ArrayList<>();
		
		while (set.next()) {
			String text = set.getString(2);
			String response1 = set.getString(3);
			boolean correct1 = set.getInt(4) == 1;
			String response2 = set.getString(5);
			boolean correct2 = set.getInt(6) == 1;
			String response3 = set.getString(7);
			boolean correct3 = set.getInt(8) == 1;
			String response4 = set.getString(9);
			boolean correct4 = set.getInt(10) == 1;
			Question question = new Question(
					text,
					response1, correct1,
					response2, correct2,
					response3, correct3,
					response4, correct4
					);
			question.setId_q(set.getInt(1));
			questions.add(question);
		}
		
		session.setQuestions(questions);
		
		return session;
	}
	
	private void insertQuestion(Question question, String session_name) throws SQLException {
		if (question.getTxt_quest() == null)
			throw new NullPointerException("The text of the question cannot be null");
		if (question.getRep1() == null)
			throw new NullPointerException("The response 1 cannot be null");
		if (question.getRep2() == null)
			throw new NullPointerException("The response 2 cannot be null");
		if (question.getRep3() == null)
			throw new NullPointerException("The response 3 cannot be null");
		if (question.getRep4() == null)
			throw new NullPointerException("The response 4 cannot be null");
		_connection.createStatement().execute(
				"INSERT INTO Question("
				+ "question_text,"
				+ "question_response1,"
				+ "question_correct1,"
				+ "question_response2,"
				+ "question_correct2,"
				+ "question_response3,"
				+ "question_correct3,"
				+ "question_response4,"
				+ "question_correct4,"
				+ "session_name) "
				+ "VALUES("
				+ "\"" + question.getTxt_quest() + "\","
				+ "\"" + question.getRep1() + "\","
				+ "\"" + (question.getCorrect1() ? 1 : 0) + "\","
				+ "\"" + question.getRep2() + "\","
				+ "\"" + (question.getCorrect2() ? 1 : 0) + "\","
				+ "\"" + question.getRep3() + "\","
				+ "\"" + (question.getCorrect3() ? 1 : 0) + "\","
				+ "\"" + question.getRep4() + "\","
				+ "\"" + (question.getCorrect4() ? 1 : 0) + "\","
				+ "\"" + session_name + "\""
				+ ");");
	}
	
	private void insertSession(Session session) throws SQLException {
		if (session.getName() == null)
			throw new NullPointerException("The name of the session cannot be null");
		_connection.createStatement().execute("INSERT INTO Session('session_name') VALUES('" + session.getName() + "');");
	}
	
}
