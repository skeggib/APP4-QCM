import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import Controls.Button;
import Controls.Panel;
import Controls.TextField;
import Models.Question;

public class ConnexionFrame extends JDialog {

	static TextField txtSession = new TextField("");
	Button btnOK = new Button("OK");
	
	Panel pnlConnexion = new Panel();
	
	//as student
	ConnexionFrame(JDialog dialog) {
		super(dialog, "Connexion", true);
		this.setSize(300, 480);
		
		txtSession.setText("Enter your Session ID...");
		pnlConnexion.add(txtSession);
		pnlConnexion.add(btnOK);
		this.setContentPane(pnlConnexion);
		
		initializeOK(false);
	}
	
	//as teacher
	ConnexionFrame(JDialog dialog, String connexion) {
		super(dialog, "Connexion", true);
		this.setSize(300, 480);
		
		txtSession.setText(connexion);
		txtSession.setEditable(false);
		pnlConnexion.add(txtSession);
		pnlConnexion.add(btnOK);
		this.setContentPane(pnlConnexion);
		
		initializeOK(true);
	}
	
	void initializeOK(boolean editable) {
		JDialog tmp = this;
		Question question = new Question();
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tmp.setVisible(false);
				if(editable == true)
					QuestionFrame.create(tmp);
				else
					QuestionFrame.show(tmp,question);
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
