import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.app4qcm.database.Question;
import com.app4qcm.database.Session;
import com.app4qcm.networking.InvalidQuestionNumber;
import com.app4qcm.networking.InvalidSessionName;
import com.app4qcm.networking.NoQuestionAvailable;
import com.app4qcm.networking.NotConnected;
import com.app4qcm.networking.Server;
import com.app4qcm.networking.SessionAlreadyStarted;
import com.app4qcm.networking.SessionNameAlreadyUsed;
import com.app4qcm.networking.SessionNotFound;
import com.app4qcm.networking.UnrecognizedResponse;

public class TestClient {

	public static void main(String[] args) {
		
		Server server;
		
		try {
			server = new Server(InetAddress.getByName("localhost"));
			server.connect();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			String str = "";
			while (!str.equals("exit"))
			{
				str = new BufferedReader(new InputStreamReader(System.in)).readLine();
				
				switch (str) {
				case "create_session":
					try {
						server.createSession(create_session());
					} catch (InvalidSessionName | SessionNameAlreadyUsed | UnrecognizedResponse e) {
						e.printStackTrace();
					}
					break;
				case "send_question":
					try {
						server.sendQuestion(1);
					} catch (NotConnected | InvalidQuestionNumber | UnrecognizedResponse e) {
						e.printStackTrace();
					}
					break;
				case "start_session":
					try {
						server.startSession("Session1");
					} catch (UnrecognizedResponse | SessionNotFound | SessionAlreadyStarted e) {
						e.printStackTrace();
					}
					break;
				case "join_session":
					try {
						server.joinSession("Session1", "Sebastien");
					} catch (UnrecognizedResponse | SessionNotFound e) {
						e.printStackTrace();
					}
					break;
				case "get_question":
					try {
						server.getQuestion();
					} catch (NotConnected | UnrecognizedResponse | NoQuestionAvailable e) {
						e.printStackTrace();
					}
					break;
				case "get_session":
					try {
						server.getSession();
					} catch (NotConnected | UnrecognizedResponse e) {
						e.printStackTrace();
					}
				}
			}
			server.close();
		} catch (UnknownHostException e) {
			System.out.println("Unknown host");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	private static Session create_session() throws IOException {
		Session session = new Session("Session1");
		session.add(new Question(
				"Quelle est la couleur du ciel ?",
				"Rouge", false,
				"Jaune", false,
				"Bleu", true,
				"Gris si on est a Paris", true));
		session.add(new Question(
				"Kamou...",
				"...lox", true,
				"...rox", false,
				"...ploc", false,
				"Aucune des reponses", false));
		return session;
	}

}
