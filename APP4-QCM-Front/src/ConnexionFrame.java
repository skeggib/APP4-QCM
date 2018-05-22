import javax.swing.JDialog;

import Controls.TextField;

public class ConnexionFrame extends JDialog {

	TextField txtSession = new TextField("");
	
	ConnexionFrame(JDialog dialog) {
		super(dialog, "Connexion", true);
		this.setSize(300, 480);
	}
	
	// String session = ConnexionFrame.showDialog();
	public static String show(JDialog dialog) {
		ConnexionFrame connexion = new ConnexionFrame(dialog);
		return connexion.txtSession.getText();
	}
}
