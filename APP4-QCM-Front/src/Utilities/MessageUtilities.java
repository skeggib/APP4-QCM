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
			showError("Invalid Session Name", "Session Name");
			break;
		case "com.app4qcm.networking.SessionNameAlreadyUsed":
			showError("Session Name Already used", "Session Name");
			break;
		case "java.io.IOException":
			showError("Failed to call Server", "Server Call");
			break;
		case "java.net.UnknownHostException":
		case "com.app4qcm.networking.UnrecognizedResponse":
		default:
			showError("Unknown error.", "Unknown error");
			break;
		}
	}
}
