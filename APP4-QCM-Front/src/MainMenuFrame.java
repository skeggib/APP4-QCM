import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDialog;

import com.app4qcm.database.Question;
import com.app4qcm.database.Session;
import com.app4qcm.networking.Server;
import com.app4qcm.networking.SessionNotFound;
import com.app4qcm.networking.UnrecognizedResponse;

import Controls.Button;
import Controls.Panel;
import Utilities.MessageUtilities;

// start of application
// calls ConnexionFrame, SessionCreationFrame and SessionFrame (as teacher), QuestionFrame (as student)
public class MainMenuFrame extends JDialog {

	Panel pnlMain = new Panel();
	Button btnCreate = new Button("Create session");
	Button btnStart = new Button("Start session");
	Button btnJoin = new Button("Join session");

	public MainMenuFrame() {
		super(new JDialog(), "Main Menu", true);
		initialize();
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
				SessionFrame.show(tmp);
			}
		});
	}

	void initializeStart() {
		JDialog tmp = this;
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Server server;
				try {
					server = new Server();
				} catch (Exception ex) {
					MessageUtilities.showError(ex);
					return;
				}

				try {
					server.connect();
				} catch (Exception ex) {
					MessageUtilities.showError(ex);
					return;
				}

				try {
					server.startSession(ConnexionFrame.ask(tmp));
				} catch (Exception ex) {
					MessageUtilities.showError(ex);
					return;
				}

				Session session = new Session();
				session.add(new Question("Il est quelle heure ?", "L'heure du café.", "L'heure de manger.",
						"L'heure du caca.", "L'heure de rentrer !", false, false, false, false));
				// session = server.getSession()

				TestFrame.show(tmp, session);

				try {
					server.close();
				} catch (Exception ex) {
					MessageUtilities.showError(ex);
				}
			}
		});
	}

	void initializeJoin() {
		JDialog tmp = this;
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WaitQuestionFrame.show(tmp);
			}
		});
	}
}
