import Controllers.AbstractController;
import Utilities.MessageUtilities;

public class App4QcmFront {

	public static void main(String[] args) {
		if (AbstractController.getServer() == null) {
			MessageUtilities.showError("Server connexion failed. Please restart.", "Error Server");
			return;
		}

		MainMenuFrame mainMenu = new MainMenuFrame();
		mainMenu.setLocationRelativeTo(null);
		mainMenu.setVisible(true);
		
		AbstractController.closeServer();
		System.exit(0);
	}

}
