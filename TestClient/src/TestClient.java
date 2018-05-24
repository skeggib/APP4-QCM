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
import com.app4qcm.networking.NotConnected;
import com.app4qcm.networking.Server;
import com.app4qcm.networking.SessionNameAlreadyUsed;
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
		Session session = new Session();
		System.out.print("Session name: ");
		session.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
		session.setQuestions(new ArrayList<>());
		Question q1 = new Question();
		q1.setTxt_quest("Quelle est la couleur du ciel ?");
		q1.setRep1("Vert");
		q1.setRep2("Bleu");
		q1.setRep3("Rouge");
		q1.setRep4("Jaune");
		q1.setCorrect1(false);
		q1.setCorrect2(true);
		q1.setCorrect3(false);
		q1.setCorrect4(false);
		session.getQuestions().add(q1);
		return session;
	}

}
