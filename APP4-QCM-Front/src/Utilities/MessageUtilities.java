package Utilities;

import javax.swing.JOptionPane;

public class MessageUtilities {

	@SuppressWarnings("static-access")
	public static void showError(String message, String title) {
		new JOptionPane().showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public static void showError(Exception e) {
		switch (e.getClass().getName()) {
		case "java.net.IOException":
			showError("Failed to call Server", "Server Call");
			break;
		case "java.net.UnknownHostException":
		//default:
			showError("Unknown error.", "Unknown error");
			break;
		}
	}
}
