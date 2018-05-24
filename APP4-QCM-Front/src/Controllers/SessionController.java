package Controllers;

import java.io.IOException;

import com.app4qcm.database.Session;
import com.app4qcm.networking.InvalidSessionName;
import com.app4qcm.networking.NotConnected;
import com.app4qcm.networking.SessionAlreadyStarted;
import com.app4qcm.networking.SessionNameAlreadyUsed;
import com.app4qcm.networking.SessionNotFound;
import com.app4qcm.networking.UnrecognizedResponse;

public final class SessionController extends AbstractController {

	public static void create(Session session) throws IOException, InvalidSessionName, SessionNameAlreadyUsed, UnrecognizedResponse {
		getServer().createSession(session);
	}
	
	public static void start(String nomSession) throws IOException, UnrecognizedResponse, SessionNotFound, SessionAlreadyStarted {
		getServer().startSession(nomSession);
	}
	
	public static void join(String nomSession, String nomEleve) throws IOException, UnrecognizedResponse, SessionNotFound {
		getServer().joinSession(nomSession, nomEleve);
	}
	
	public static Session get() throws IOException, NotConnected, UnrecognizedResponse {
		return getServer().getSession();
	}
}
