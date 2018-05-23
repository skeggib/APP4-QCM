package com.app4qcm.logic;

import java.util.HashMap;

import com.app4qcm.networking.ClientConnection;

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
		
	}

}
