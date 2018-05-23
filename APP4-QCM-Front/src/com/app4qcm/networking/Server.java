package com.app4qcm.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

import com.app4qcm.database.Session;
import com.app4qcm.serialization.XML_Tools;

public class Server {
	
	private InetAddress address;
	private Socket socket;
	
	public Server(InetAddress address) {
		this.address = address;
	}
	
	public void connect() throws IOException {
		this.socket = new Socket(this.address, 6789);
	}
	
	public void close() throws IOException {
		this.socket.close();
	}
	
	private String send(String command) throws IOException {
		this.socket.getOutputStream().write(new String(command + "\n").getBytes());
		System.out.println(command + "\n");
		this.socket.getOutputStream().flush();
		return new BufferedReader(new InputStreamReader(this.socket.getInputStream())).readLine();
	}
	
	public void createSession(Session session) throws IOException, InvalidSessionName, SessionNameAlreadyUsed, UnrecognizedResponse {
		String response = send(
				"create_session " + 
				XML_Tools.encodeToString(session).replace("\n", "").replace("\r", ""));
		System.out.println("Response: " + response);
		if (response.equals("ok"))
			return;
		if (response.equals("invalid_name"))
			throw new InvalidSessionName();
		if (response.equals("name_already_used"))
			throw new SessionNameAlreadyUsed();
		else
			throw new UnrecognizedResponse();
	}
	
}
