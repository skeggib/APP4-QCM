import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import Controls.Button;
import Controls.Label;
import Controls.Panel;
import Controls.TextField;
import Models.Question;

public class ConnexionFrame extends JDialog {

	static TextField txtSession = new TextField("Enter your Session ID...");
	Button btnOK = new Button("OK");
	Label lblSession = new Label("Your SheetID is : ");

	Panel pnlConnexion = new Panel();

	// as student
	ConnexionFrame(JDialog dialog) {
		super(dialog, "Connexion", true);
		
		lblSession.setText("Enter your Session ID : ");		
		initialize();
	}

	// as teacher
	ConnexionFrame(JDialog dialog, String connexion) {
		super(dialog, "Connexion", true);
		initialize();

		txtSession.setText(connexion);
		txtSession.setEditable(false);
		
	}

	void initialize() {
		this.setSize(300, 100);

		txtSession.setText("Enter your Session ID...");
		pnlConnexion.add(lblSession);
		pnlConnexion.add(txtSession);
		pnlConnexion.add(btnOK);
		this.setContentPane(pnlConnexion);
		
		btnOK.requestFocus();

		initializeOK();
	}

	void initializeOK() {
		JDialog tmp = this;
		Question question = new Question();
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tmp.setVisible(false);
			}
		});
	}

	// show connexion identifier to share
	public static void show(JDialog dialog, String connexion) {
		ConnexionFrame connexionFrame = new ConnexionFrame(dialog, connexion);
		connexionFrame.setVisible(true);
	}

	// ask connexion identifier to join
	public static String ask(JDialog dialog) {
		ConnexionFrame connexionFrame = new ConnexionFrame(dialog);
		connexionFrame.setVisible(true);
		return txtSession.getText();
	}
}
