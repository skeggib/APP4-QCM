package com.app4qcm.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import com.app4qcm.logic.Logic;

/**
 * Connexion d'un client
 * @author Sebastien Klasa
 */
public class ClientConnection implements Runnable {
	
	private int id;
	Socket socket;
	Logic logic;
	
	public ClientConnection(Socket socket, Logic logic) throws IOException {
		this.id = logic.nextId();
		logic.register(this);
		this.socket = socket;
		this.logic = logic;
	}

	@Override
	public void run() {
		
		try {
			System.out.println("(" + id + ") Connected");
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				System.out.println("(" + id + ") Received > " + line);
				this.logic.command(this, line);
				line = reader.readLine();
			}
			System.out.println("(" + id + ") Disconnected");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				this.logic.unregister(this);
				this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getId() {
		return id;
	}
	
}
