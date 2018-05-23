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
	 */
	public void command(ClientConnection client, String str) {
		
		int i=0;
		do{
			i++;
		}
		while (str.charAt(i)!=' ' && i<(str.length()-1));

		//Recuperation du premier mot
		String fonction=str.substring(0,i);
		String reste="";

		//Suite du texte recupere
		if ((i+1)<str.length()-1)
			reste=str.substring(i+1);
		
		switch(fonction) {
		  //Appel de methode en fonction du premier mot du texte precedent
		  case "create_session":
			  	create_session(reste);
		    	break;
		  case "start_session":
			  start_session(client,reste);
		    	break;
		  case "join_session":
			  	join_session(client,reste);
			  	break;
		  case "send_question":
			  	send_question(reste);
			    break;
		  case "get_question":
			  	get_question();
			    break;
		}
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
			int numQuestion = Integer.parseInt(reste); 
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
					System.out.println("cas1");
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
