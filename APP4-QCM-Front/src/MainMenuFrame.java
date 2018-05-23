import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.app4qcm.database.Question;
import com.app4qcm.database.Session;

import Controls.Button;
import Controls.Panel;

// start of application
// calls ConnexionFrame, SessionCreationFrame and SessionFrame (as teacher), QuestionFrame (as student)
public class MainMenuFrame extends JDialog {

	Panel pnlMain = new Panel();
	Button btnCreate = new Button("Create session");
	Button btnStart = new Button("Start session");
	Button btnJoin = new Button("Join session");

	Session testSession;

	public MainMenuFrame() {
		initialize();

		testSession = new Session();
		testSession.add(new Question("Qui est le plus beau ?", "Séb, ça s'tient.", true, "Cyril, si 'real' !", true,
				"Dimitri, dix mi-tris", true, "Maxime, c'est moi quoi", true));
		testSession.add(new Question("Qui a travaillé sur le backend ?", "Séb, ça s'tient.", true, "Cyril, si 'real' !",
				true, "Dimitri, dix mi-tris", false, "Maxime, c'est moi quoi", false));
		testSession.add(new Question("Qui n'a pas travaillé sur le frontend ?", "Séb, ça s'tient.", true,
				"Cyril, si 'real' !", true, "Dimitri, dix mi-tris", false, "Maxime, c'est moi quoi", false));
		testSession.add(new Question("Qui a travaillé ?", "Séb, ça s'tient.", true, "Cyril, si 'real' !", true,
				"Dimitri, dix mi-tris", true, "Maxime, c'est moi quoi", true));
		testSession.add(new Question("Essaie de lécher ton coude.", "Trop facile !", false, "J'y suis presque !", false,
				"C'est possible ?", false, "Bien sûr que non tout le monde sait ça...", true));
	}

	void initialize() {
		int width = 200, height = 50;

		this.setSize(600, 480);

		pnlMain.setLayout(null);
		pnlMain.add(btnCreate);
		btnCreate.setBounds(this.getWidth() / 2 - width - 3, this.getHeight() / 2 - height - 3, width, height);
		pnlMain.add(btnStart);
		btnStart.setBounds(this.getWidth() / 2 + 3, this.getHeight() / 2 - height - 3, width, height);
		pnlMain.add(btnJoin);
		btnJoin.setBounds(this.getWidth() / 2 - width - 3, this.getHeight() / 2 + 3, 2 * width + 6, height);
		this.setContentPane(pnlMain);

		initializeCreate();
		initializeStart();
		initializeJoin();
	}

	void initializeCreate() {
		JDialog tmp = this;
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SessionFrame.edit(tmp, testSession);
			}
		});
	}

	void initializeStart() {
		JDialog tmp = this;
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sessionId = "GET FROM SERVER";
				ConnexionFrame.show(tmp, sessionId);
				TestFrame.show(tmp, testSession);
			}
		});
	}

	void initializeJoin() {
		JDialog tmp = this;
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sessionId = ConnexionFrame.ask(tmp);
				// WaitQuestionFrame.show(sessionId);
			}
		});
	}
}
