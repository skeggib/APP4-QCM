package Utilities;

import javax.swing.JOptionPane;

public class MessageUtilities {

	@SuppressWarnings("static-access")
	public static void showError(String message, String title) {
		new JOptionPane().showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public static void showError(Exception e) {
		switch (e.getClass().getName()) {
		case "com.app4qcm.networking.InvalidSessionName":
			showError("Invalid Session Name", "Error Session");
			break;
		case "com.app4qcm.networking.SessionNameAlreadyUsed":
			showError("Session Name Already used", "Error Session");
			break;
		case "com.app4qcm.networking.SessionNotFound":
			showError("Session not found.", "Error Session");
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
