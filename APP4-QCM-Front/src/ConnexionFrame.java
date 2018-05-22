import javax.swing.JFrame;

import Controls.TextField;

public class ConnexionFrame extends JFrame {

	TextField txtSession = new TextField();
	
	private ConnexionFrame() {
		
	}
	
	// String session = ConnexionFrame.showDialog();
	public static String showDialog() {
		ConnexionFrame connexion = new ConnexionFrame();
		return connexion.txtSession.getText();
	}
}
