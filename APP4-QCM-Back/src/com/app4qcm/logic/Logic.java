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
	
	public void command(ClientConnection client, String str) {
		int i=0;
		do{
			i++;
		}
		while (str.charAt(i)!=' ' && i<(str.length()-1));
		String fonction=str.substring(0,i);
		String reste="";
		if ((i+1)<str.length()-1)
			reste=str.substring(i+1);
		
		switch(fonction) {
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

	private String get_question() {
		// TODO Auto-generated method stub
		return "ok";
	}

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

	private String create_session(String reste) {
		// TODO Auto-generated method stub
		Session session=(Session) XML_Tools.decodeFromString(reste);
		
		if (session.getName()==null)
			return "invalid_name";
		else{
			switch(session.getName()) {
				case "":
					return "invalid_name";
				case " ":
					return "invalid_name";
				default:
					return "ok";
			}
		}
	}

	private String join_session(ClientConnection client, String reste)  {
		// TODO Auto-generated method stub		
		return "session_not_found";
	}

	private String start_session(ClientConnection client, String reste) {
		// TODO Auto-generated method stub
		return "session_not_found";
	}
	
	

}
