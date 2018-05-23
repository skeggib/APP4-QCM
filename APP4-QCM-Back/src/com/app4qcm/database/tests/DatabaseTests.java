package com.app4qcm.database.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.app4qcm.database.Context;
import com.app4qcm.database.Question;
import com.app4qcm.database.Session;

public class DatabaseTests {
	
	private Context _context;
	
	@Before
	public void before() throws SQLException, IOException {
		_context = new Context("testdb.sqlite");
		_context.reset();
	}
	
	@Test
	public void test() {
		
	}

	/*@Test
	public void emptyDatabase() throws SQLException {
		assertEquals(0, _context.getQuestionDao().queryForAll().size());
		assertEquals(0, _context.getSessionDao().queryForAll().size());
	}
	
	@Test
	public void insertSession() throws SQLException {
		Session session = new Session();
		session.setName("Test session");
		_context.getSessionDao().create(session);
		
		assertEquals(0, _context.getQuestionDao().queryForAll().size());
		assertEquals(1, _context.getSessionDao().queryForAll().size());
		
		Session given = _context.getSessionDao().queryForAll().get(0);
		assertEquals("Test session", given.getName());
	}
	
	@Test
	public void insertTwoSessions() throws SQLException {
		Session session1 = new Session();
		session1.setName("Test session 1");
		_context.getSessionDao().create(session1);
		
		Session session2 = new Session();
		session2.setName("Test session 2");
		_context.getSessionDao().create(session2);
		
		assertEquals(0, _context.getQuestionDao().queryForAll().size());
		assertEquals(2, _context.getSessionDao().queryForAll().size());
		
		Session given1 = _context.getSessionDao().queryForAll().get(0);
		assertEquals("Test session 1", given1.getName());
		
		Session given2 = _context.getSessionDao().queryForAll().get(1);
		assertEquals("Test session 2", given2.getName());
	}
	
	@Test 
	public void insertQuestion() throws SQLException {
		Session session = new Session();
		session.setName("Test session");
		
		Question q1 = new Question();
		q1.setTxt_quest("Question ?");
		q1.setRep1("Rep 1");
		q1.setRep2("Rep 2");
		q1.setRep3("Rep 3");
		q1.setRep4("Rep 4");
		q1.setCorrect1(true);
		q1.setCorrect2(false);
		q1.setCorrect3(true);
		q1.setCorrect4(false);
		
		_context.getSessionDao().create(session);
		_context.getQuestionDao().create(q1);
		
		assertEquals(1, _context.getQuestionDao().queryForAll().size());
		assertEquals(1, _context.getSessionDao().queryForAll().size());
		
		Question given = _context.getQuestionDao().queryForAll().get(0);
		assertEquals(q1.getTxt_quest(), given.getTxt_quest());
		assertEquals(q1.getRep1(), given.getRep1());
		assertEquals(q1.getRep2(), given.getRep2());
		assertEquals(q1.getRep3(), given.getRep3());
		assertEquals(q1.getRep4(), given.getRep4());
		assertEquals(q1.getCorrect1(), given.getCorrect1());
		assertEquals(q1.getCorrect2(), given.getCorrect2());
		assertEquals(q1.getCorrect3(), given.getCorrect3());
		assertEquals(q1.getCorrect4(), given.getCorrect4());
	}
	
	@Test
	public void insertTwoQuestions() {
		
	}*/

}
