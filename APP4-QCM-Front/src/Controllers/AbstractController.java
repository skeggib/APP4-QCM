package Controllers;

import com.app4qcm.networking.Server;

import Utilities.MessageUtilities;

public abstract class AbstractController {

	private static Server server;

	public static Server getServer() {
		if (server == null) {
			try {
				server = new Server();
				server.connect();
			} catch (Exception ex) {
				MessageUtilities.showError(ex);
				server = null;
			}
		}

		return server;
	}

	public static void closeServer() {
		if (server == null)
			return;

		try {
			server.close();
		} catch (Exception ex) {
			MessageUtilities.showError(ex);
		}
		
		server = null;
	}
}
