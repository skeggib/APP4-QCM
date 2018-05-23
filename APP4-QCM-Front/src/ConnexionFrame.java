import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import Controls.Button;
import Controls.Label;
import Controls.Panel;
import Controls.TextField;

public class ConnexionFrame extends JDialog {

	static TextField txtSession = new TextField("Enter your Session ID...");
	Button btnCopy = new Button("Copy");
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

		pnlConnexion.add(btnCopy);
		initializeCopy();

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
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tmp.setVisible(false);
			}
		});
	}

	void initializeCopy() {
		btnCopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(txtSession.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				txtSession.requestFocus();
				txtSession.selectAll();
			}
		});
	}

	// show connexion identifier to share
	public static void show(JDialog dialog, String connexion) {
		ConnexionFrame connexionFrame = new ConnexionFrame(dialog, connexion);
		connexionFrame.setLocationRelativeTo(dialog);
		connexionFrame.setVisible(true);
	}

	// ask connexion identifier to join
	public static String ask(JDialog dialog) {
		ConnexionFrame connexionFrame = new ConnexionFrame(dialog);
		connexionFrame.setLocationRelativeTo(dialog);
		connexionFrame.setVisible(true);
		return txtSession.getText();
	}
}
