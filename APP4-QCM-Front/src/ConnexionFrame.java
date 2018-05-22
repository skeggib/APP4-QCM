import javax.swing.JDialog;

import Controls.TextField;

public class ConnexionFrame extends JDialog {

	TextField txtSession = new TextField("");
	
	ConnexionFrame(JDialog dialog) {
		super(dialog, "Connexion", true);
		this.setSize(300, 480);
	}
	
	// show connexion identifier to share
	public static void show(JDialog dialog, String connexion) {
		// TODO
	}
	
	// ask connexion identifier to join
	public static String ask(JDialog dialog) {
		// TODO
		return "";
	}
}
