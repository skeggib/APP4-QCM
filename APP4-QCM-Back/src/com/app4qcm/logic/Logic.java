package com.app4qcm.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.app4qcm.database.Context;
import com.app4qcm.database.Question;
import com.app4qcm.database.Session;
import com.app4qcm.database.Statistics;
import com.app4qcm.networking.ClientConnection;
import com.app4qcm.serialization.XML_Tools;

public class Logic {
	
	private class Pair<T1, T2> {
		public T1 item1;
		public T2 item2;
		
		public Pair(T1 t1, T2 t2) {
			this.item1 = t1;
			this.item2 = t2;
		}
	}
	
	private class RunningSession {
		public Session session;
		public ClientConnection prof;
		public HashMap<String, ClientConnection> eleves;
		public ArrayList<HashMap<ClientConnection, boolean[]>> responses; 
		public int currentQuestionIndex = -1;
		
		private RunningSession() {
			this.eleves = new HashMap<>();
			this.responses = new ArrayList<>();
		}
		
		public RunningSession(Session session, ClientConnection prof) {
			this();
			this.session = session;
			this.prof = prof;
			for (Question question : this.session.getQuestions())
				this.responses.add(new HashMap<>());
		}
	}
	
	private HashMap<Integer, ClientConnection> clients;
	private Context context;
	
	private int id = 0;
	private HashMap<String, RunningSession> runningSessions;
	
	public Logic() throws SQLException {
		this.clients = new HashMap<>();
		this.context = new Context("db.sqlite");
		this.runningSessions = new HashMap<>();
	}
	
	public int nextId() {
		return id++;
	}
	
	public void register(ClientConnection client) {
		clients.put(client.getId(), client);
	}
	
	public void unregister(ClientConnection client) {
		clients.remove(client.getId());
		
		ArrayList<String> sessionToStop = new ArrayList<>();
		
		for (Entry<String, RunningSession> entry : this.runningSessions.entrySet()) {
			if (client == entry.getValue().prof)
				sessionToStop.add(entry.getKey());
			
			RunningSession session = entry.getValue();
			
			ArrayList<String> elevesToRemove = new ArrayList<>();
			
			for (Entry<String, ClientConnection> entry2 : session.eleves.entrySet()) {
				elevesToRemove.add(entry2.getKey());
			}
			
			for (String name : elevesToRemove) {
				session.eleves.remove(name);
			}
		}
		
		for (String name : sessionToStop) {
			this.runningSessions.remove(name);
		}
	}
	
	/**
	 * Reception de commande
	 * @param client
	 * @param str
	 * @return La reponse du serveur
	 */
	public String command(ClientConnection client, String str) {
		
		int i=0;
		do{
			i++;
		}
		while (str.charAt(i)!=' ' && i<(str.length()-1));

		//Recuperation du premier mot
		String fonction=str.substring(0,i);
		String reste="";

		//Suite du texte recupere
		if ((i+1)<str.length())
			reste=str.substring(i+1);
		
		switch(fonction) {
			case "create_session":	
				return create_session(reste);
			case "start_session":
				return start_session(client,reste);
			case "join_session":
				return join_session(client,reste);
			case "send_question":
				return send_question(client, reste);
			case "get_question":
				return get_question(client);
			case "get_session":
				return get_session(client);
			case "send_response":
				return send_response(client, reste);
			case "get_stats":
				return get_stats(client, reste);
			case "close_session":
				return close_session(client);
		}
		
		return "fatal_error";
	}

	private RunningSession getRunningSessionByEleve(ClientConnection eleve) {
		for (Entry<String, RunningSession> entry : this.runningSessions.entrySet()) {
			RunningSession runningSession = entry.getValue();
			for (Entry<String, ClientConnection> entry2 : runningSession.eleves.entrySet()) {
				if (eleve == entry2.getValue())
					return runningSession;
			}
		}
		return null;
	}

	/**
	 * Eleve demande une question
	 * @return reponse de la demande + numero question + texte de la question
	 */
	private String get_question(ClientConnection eleve) {
		RunningSession runningSession = getRunningSessionByEleve(eleve);
		if (runningSession == null)
			return "not_connected";
		if (runningSession.currentQuestionIndex == -1)
			return "no_question";
		Question question = runningSession.session.getQuestions().get(runningSession.currentQuestionIndex);
		String xml = XML_Tools.encodeToString(question).replace("\n", "").replace("\r", "");
		return "ok " + runningSession.currentQuestionIndex + " " + xml;
	}

	private RunningSession getRunningSessionByProf(ClientConnection prof) {
		for (Entry<String, RunningSession> entry : this.runningSessions.entrySet()) {
			if (entry.getValue().prof == prof)
				return entry.getValue();
		}
		return null;
	}
	
	/**
	 * Professeur lance une question
	 * @param reste
	 * @return validitee de la question demandee
	 */
	private String send_question(ClientConnection prof, String reste) {
		int numQuestion;
		try  { 
			numQuestion = Integer.parseInt(reste.trim()); 
		} 
		catch (NumberFormatException nfe) { 
			return "invalid_question";
		}
		
		RunningSession runningSession = getRunningSessionByProf(prof);
		if (runningSession == null)
			return "not_connected";
		
		if (numQuestion < 0 || numQuestion > runningSession.session.getQuestions().size() - 1)
			return "invalid_question";
		
		runningSession.currentQuestionIndex = numQuestion;
		return "ok";
	}

	/**
	 * Professeur cree un QCM
	 * @param reste
	 * @return si la session est cree ou non
	 */
	private String create_session(String reste) {
		Session session=(Session) XML_Tools.decodeFromString(reste);
		
		//Verification que le nom n est pas null
		if (session.getName()==null)
			return "invalid_name";
		else{
			//Verification que le nom n est pas vide
			if(session.getName().trim().isEmpty()) {
					return "invalid_name";
			}
			
			try {
				this.context.addSession(session);
			} catch (SQLException e) {
				e.printStackTrace();
				return "error";
			}
			
			return "ok";
		}
	}
	
	/**
	 * Eleve se connecte a la session
	 * @param client
	 * @param reste
	 * @return validee de la connexion
	 */
	private String join_session(ClientConnection client, String reste)  {
		if (reste.split(" ").length != 2)
			return "error";
		
		String sessionName = reste.split(" ")[0];
		String userName = reste.split(" ")[1];
		
		if (!this.runningSessions.containsKey(sessionName))
			return "session_not_found";
		
		if (this.runningSessions.get(sessionName).eleves.containsKey(userName))
			return "name_already_used";
		
		this.runningSessions.get(sessionName).eleves.put(userName, client);
		
		return "ok";
	}

	/**
	 * Professeur se connecte a la session
	 * @param client
	 * @param reste
	 * @return validee de la connexion
	 */
	private String start_session(ClientConnection client, String reste) {

		Session session;
		
		try {
			session = this.context.getSession(reste);
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		
		if (session == null)
			return "session_not_found";
		
		if (this.runningSessions.containsKey(session.getName()))
			return "session_already_started";
		
		this.runningSessions.put(session.getName(), new RunningSession(session, client));
		
		return "ok";
	}
	
	private String get_session(ClientConnection prof) {
		RunningSession running = getRunningSessionByProf(prof);
		if (running == null)
			return "not_connected";
		return "ok " + XML_Tools.encodeToString(running.session).replace("\n", "").replace("\r", "");
	}
	
	private String send_response(ClientConnection eleve, String param) {
		RunningSession running = getRunningSessionByEleve(eleve);
		if (running == null)
			return "not_connected";
		if (running.currentQuestionIndex == -1)
			return "no_question";
		if (param.length() != 4)
			return "invalid_parameter";
		
		boolean[] responses = new boolean[4];
		for (int i = 0; i < 4; ++i)
			responses[i] = param.charAt(i) == '1';
		
		running.responses.get(running.currentQuestionIndex).put(eleve, responses);
		
		return "ok";
	}
	
	private String getEleveName(ClientConnection client, RunningSession runningSession) {
		for (Entry<String, ClientConnection> entry : runningSession.eleves.entrySet()) {
			if (entry.getValue() == client)
				return entry.getKey();
		}
		return null;
	}
	
	private String get_stats(ClientConnection prof, String param) {
		RunningSession running = getRunningSessionByProf(prof);
		if (running == null)
			return "not_connected";
		if (param == null)
			return "invalid_question";
		int numQuestion;
		try  { 
			numQuestion = Integer.parseInt(param.trim()); 
		} 
		catch (NumberFormatException nfe) { 
			return "invalid_question";
		}
		if (numQuestion < 0 || numQuestion > running.session.getQuestions().size() - 1)
			return "invalid_question";
		
		Statistics stats = new Statistics();
		stats.question = running.session.getQuestions().get(numQuestion);
		stats.question.setId_q(numQuestion);
		stats.responses = new HashMap<>();
		for (HashMap<ClientConnection, boolean[]> map : running.responses) {
			for (Entry<ClientConnection, boolean[]> entry : map.entrySet()) {
				String name = getEleveName(entry.getKey(), running);
				stats.responses.put(name, entry.getValue());
			}
		}
		
		return "ok " + XML_Tools.encodeToString(stats).replace("\n", "").replace("\r", "");
	}
	
	private String close_session(ClientConnection prof) {
		RunningSession running = getRunningSessionByProf(prof);
		if (running == null)
			return "not_connected";
		this.runningSessions.remove(running.session.getName());
		return "ok";
	}

}
