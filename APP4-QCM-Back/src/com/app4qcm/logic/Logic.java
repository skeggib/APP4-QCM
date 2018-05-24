package com.app4qcm.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import com.app4qcm.database.Question;
import com.app4qcm.database.Session;
import com.app4qcm.networking.ClientConnection;
import com.app4qcm.serialization.XML_Tools;

public class Logic {
	
	private HashMap<Integer, ClientConnection> clients; 
	
	private int id = 0;
	
	public Logic() {
		this.clients = new HashMap<>();
	}
	
	public int nextId() {
		return id++;
	}
	
	public void register(ClientConnection client) {
		clients.put(client.getId(), client);
	}
	
	public void unregister(ClientConnection client) {
		clients.remove(client.getId());
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
			  return send_question(reste);
		  case "get_question":
			  return get_question();
		}
		
		return "error";
	}

	/**
	 * Eleve demande une question
	 * @return reponse de la demande + numero question + texte de la question
	 */
	private String get_question() {
		// TODO Auto-generated method stub
		return "ok";
	}

	/**
	 * Professeur lance une question
	 * @param reste
	 * @return validitee de la question demandee
	 */
	private String send_question(String reste) {
		// TODO Auto-generated method stub
		try 
		{ 
			int numQuestion = Integer.parseInt(reste.trim()); 
		} 
		catch (NumberFormatException nfe) 
		{ 
			return "invalid_question";
		}
		return "ok";
	}

	/**
	 * Professeur cree un QCM
	 * @param reste
	 * @return si la session est cree ou non
	 */
	private String create_session(String reste) {
		// TODO Auto-generated method stub
		Session session=(Session) XML_Tools.decodeFromString(reste);
		
		//Verification que le nom n est pas null
		if (session.getName()==null)
			return "invalid_name";
		else{
			//Verification que le nom n est pas vide
			if(session.getName().trim().isEmpty()) {
					return "invalid_name";
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
		// TODO Auto-generated method stub		
		return "session_not_found";
	}

	/**
	 * Professeur se connecte a la session
	 * @param client
	 * @param reste
	 * @return validee de la connexion
	 */
	private String start_session(ClientConnection client, String reste) {
		// TODO Auto-generated method stub
		return "session_not_found";
	}
	
	

}
