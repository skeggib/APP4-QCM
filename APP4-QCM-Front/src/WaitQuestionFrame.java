import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JDialog;

import com.app4qcm.database.Question;
import com.app4qcm.networking.NoQuestionAvailable;
import com.app4qcm.networking.NotConnected;
import com.app4qcm.networking.Server;
import com.app4qcm.networking.SessionNotFound;
import com.app4qcm.networking.UnrecognizedResponse;

import Controls.Button;
import Controls.Panel;
import Utilities.MessageUtilities;

public class WaitQuestionFrame extends JDialog {

	Panel pnlWaitQuestion = new Panel();
	Button btnActualize = new Button("Actualize");

	Server server;

	private WaitQuestionFrame(JDialog dialog) {
		super(dialog, "Wait Question", true);
		initialize();
	}

	void initialize() {
		setSize(320, 200);

		pnlWaitQuestion.setLayout(null);

		btnActualize.setBounds(6, 6, 150, 20);
		pnlWaitQuestion.add(btnActualize);

		setContentPane(pnlWaitQuestion);

		initializeActualize();
	}

	int previousQuestionId = -1;

	void initializeActualize() {
		JDialog tmp = this;
		btnActualize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Question question;
				try {
					question = new Question();
					server.getQuestion();
					if (question.getId_q() == previousQuestionId)
						question = null;
					else
						previousQuestionId = question.getId_q();
				} catch (NoQuestionAvailable nqa) {
					question = null;
				} catch (Exception ex) {
					MessageUtilities.showError(ex);
					setVisible(false);
					return;
				}

				if (question != null) {
					QuestionFrame.show(tmp, question);
				}

			}
		});
	}

	public static void show(JDialog dialog, String sessionName) {
		WaitQuestionFrame waitQuestion = new WaitQuestionFrame(dialog);

		try {
			waitQuestion.server = new Server();
		} catch (Exception ex) {
			MessageUtilities.showError(ex);
			return;
		}

		try {
			waitQuestion.server.connect();
		} catch (Exception ex) {
			MessageUtilities.showError(ex);
			return;
		}

		try {
			waitQuestion.server.joinSession(sessionName, ConnexionFrame.ask(dialog));
		} catch (Exception ex) {
			MessageUtilities.showError(ex);
			return;
		}

		waitQuestion.setLocationRelativeTo(dialog);
		waitQuestion.setVisible(true);

		try {
			waitQuestion.server.close();
		} catch (Exception ex) {
			MessageUtilities.showError(ex);
			return;
		}
	}
}
