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

	public void createSession(Session session)
			throws IOException, InvalidSessionName, SessionNameAlreadyUsed, UnrecognizedResponse {
		String response = send(
				"create_session " + XML_Tools.encodeToString(session).replace("\n", "").replace("\r", ""));
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

	public void startSession(String nomSession) throws IOException, UnrecognizedResponse, SessionNotFound {
		String response = send("start_session " + nomSession);
		System.out.println("Response: " + response);
		if (response.equals("ok"))
			return;
		if (response.equals("session_not_found"))
			throw new SessionNotFound();
		else
			throw new UnrecognizedResponse();
	}

	public void joinSession(String nomSession, String nomEleve)
			throws IOException, UnrecognizedResponse, SessionNotFound {
		String response = send("join_session " + nomSession + " " + nomEleve);
		System.out.println("Response: " + response);
		if (response.equals("ok"))
			return;
		if (response.equals("session_not_found"))
			throw new SessionNotFound();
		else
			throw new UnrecognizedResponse();
	}

	public void sendQuestion(int numero) throws IOException, NotConnected, InvalidQuestionNumber, UnrecognizedResponse {
		String response = send("send_question " + Integer.toString(numero));
		System.out.println("Response: " + response);
		if (response.equals("ok"))
			return;
		if (response.equals("not_connected"))
			throw new NotConnected();
		if (response.equals("invalid_question"))
			throw new InvalidQuestionNumber();
		else
			throw new UnrecognizedResponse();

	}

	public void getQuestion() throws IOException, NotConnected, UnrecognizedResponse, NoQuestionAvailable {
		String response = send("get_question ");
		System.out.println("Response: " + response);
		if (response.equals("ok"))
			return;
		if (response.equals("not_connected"))
			throw new NotConnected();
		if (response.equals("no_question"))
			throw new NoQuestionAvailable();
		else
			throw new UnrecognizedResponse();

	}
	
	
}
