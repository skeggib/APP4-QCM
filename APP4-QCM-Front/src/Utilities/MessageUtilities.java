package Utilities;

import javax.swing.JOptionPane;

public class MessageUtilities {

	@SuppressWarnings("static-access")
	public static void showError(String message, String title) {
		new JOptionPane().showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public static void showError(Exception e) {
		switch (e.getClass().getName()) {
		case "com.app4qcm.networking.NotConnected":
			showError("Not connected to server", "Error Server");
			break;

		case "com.app4qcm.networking.InvalidSessionName":
			showError("Invalid Session Name", "Error Session");
			break;
		case "com.app4qcm.networking.SessionNameAlreadyUsed":
			showError("Session Name Already used", "Error Session");
			break;
		case "com.app4qcm.networking.SessionNotFound":
			showError("Session not found.", "Error Session");
			break;
		case "com.app4qcm.networking.SessionAlreadyStarted":
			showError("Session is already started.", "Error Session");
			break;

		case "com.app4qcm.networking.NoQuestionAvailable":
			showError("No question available.", "Error Question");
			break;
		case "com.app4qcm.networking.InvalidQuestionNumber":
			showError("Question not found.", "Error Question");
			break;

		case "java.io.IOException":
		case "java.net.SocketException":
		case "java.net.ConnectException":
			showError("Failed to call Server", "Error Server");
			break;
		case "java.net.UnknownHostException":
		case "com.app4qcm.networking.UnrecognizedResponse":
		default:
			showError("Unknown error.", "Unknown error");
			break;
		}
	}
}
