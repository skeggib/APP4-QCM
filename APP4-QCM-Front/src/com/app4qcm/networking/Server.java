package com.app4qcm.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.app4qcm.database.Question;
import com.app4qcm.database.Session;
import com.app4qcm.database.Statistics;
import com.app4qcm.serialization.XML_Tools;

public class Server {

	private InetAddress address;
	private Socket socket;

	public Server(InetAddress address) {
		this.address = address;
	}

	public Server() throws UnknownHostException {
		this(InetAddress.getLocalHost());
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

	public void startSession(String nomSession)
			throws IOException, UnrecognizedResponse, SessionNotFound, SessionAlreadyStarted {
		String response = send("start_session " + nomSession);
		System.out.println("Response: " + response);
		if (response.equals("ok"))
			return;
		if (response.equals("session_already_started")) {
			throw new SessionAlreadyStarted();
		}
		if (response.equals("session_not_found"))
			throw new SessionNotFound();
		else
			throw new UnrecognizedResponse();
	}

	public void joinSession(String nomSession, String nomEleve)
			throws IOException, UnrecognizedResponse, SessionNotFound, StudentNameAlreadyUsed {
		String response = send("join_session " + nomSession + " " + nomEleve);
		System.out.println("Response: " + response);
		if (response.equals("ok"))
			return;
		if (response.equals("session_not_found"))
			throw new SessionNotFound();
		else if (response.equals("name_already_used"))
			throw new StudentNameAlreadyUsed();
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

	public Question getQuestion() throws IOException, NotConnected, UnrecognizedResponse, NoQuestionAvailable {
		String response = send("get_question ");
		System.out.println("Response: " + response);
		String result = response.split(" ")[0];
		if (result.equals("ok")) {
			int num = Integer.parseInt(response.split(" ")[1]);
			int i = 0;
			int cpt = 0;
			do {
				i++;
				if (response.charAt(i) != ' ')
					cpt++;
			} while (i < (response.length() - 1) && cpt < 2);
			String xml = response.substring(i + 1);
			Question question = (Question) XML_Tools.decodeFromString(xml);
			question.setId_q(num);
			return question;
		}
		if (result.equals("not_connected"))
			throw new NotConnected();
		if (result.equals("no_question"))
			throw new NoQuestionAvailable();
		else
			throw new UnrecognizedResponse();

	}

	public Session getSession() throws IOException, NotConnected, UnrecognizedResponse {
		String response = send("get_session ");
		System.out.println("Response: " + response);
		String result = response.split(" ")[0];
		if (result.equals("ok")) {
			int i = 0;
			int cpt = 0;
			do {
				i++;
				if (response.charAt(i) != ' ')
					cpt++;
			} while (i < (response.length() - 1) && cpt < 1);
			String xml = response.substring(i + 1);
			Session session = (Session) XML_Tools.decodeFromString(xml);
			return session;
		}
		if (result.equals("not_connected"))
			throw new NotConnected();
		else
			throw new UnrecognizedResponse();

	}

	public void sendResponse(boolean rep1, boolean rep2, boolean rep3, boolean rep4)
			throws IOException, NotConnected, UnrecognizedResponse, InvalidParameter, NoQuestionAvailable {
		String response = send(
				"send_response " + (rep1 ? "1" : "0") + (rep2 ? "1" : "0") + (rep3 ? "1" : "0") + (rep4 ? "1" : "0"));
		System.out.println("Response: " + response);
		if (response.equals("ok"))
			return;
		if (response.equals("not_connected"))
			throw new NotConnected();
		if (response.equals("invalid_parameter"))
			throw new InvalidParameter();
		if (response.equals("no_question"))
			throw new NoQuestionAvailable();
		else
			throw new UnrecognizedResponse();

	}

	public Statistics getStats(int numero)
			throws IOException, NotConnected, UnrecognizedResponse, InvalidQuestionNumber {
		String response = send("get_stats " + Integer.toString(numero));
		System.out.println("Response: " + response);
		String result = response.split(" ")[0];
		if (result.equals("ok")) {
			int i = 0;
			int cpt = 0;
			do {
				i++;
				if (response.charAt(i) != ' ')
					cpt++;
			} while (i < (response.length() - 1) && cpt < 1);
			String xml = response.substring(i + 1);
			Statistics statistics = (Statistics) XML_Tools.decodeFromString(xml);
			statistics.getQuestion().setId_q(numero);
			return statistics;
		}
		if (result.equals("not_connected"))
			throw new NotConnected();
		if (result.equals("invalid_question"))
			throw new InvalidQuestionNumber();
		else
			throw new UnrecognizedResponse();

	}

	public void closeSession() throws IOException, UnrecognizedResponse, NotConnected {
		String response = send("close_session ");
		System.out.println("Response: " + response);
		if (response.equals("ok"))
			return;
		if (response.equals("not_connected"))
			throw new NotConnected();
		else
			throw new UnrecognizedResponse();
	}

}
