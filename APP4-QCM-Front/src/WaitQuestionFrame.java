import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JDialog;

import com.app4qcm.database.Question;
import com.app4qcm.networking.NotConnected;
import com.app4qcm.networking.Server;

import Controls.Button;
import Controls.Panel;
import Utilities.MessageUtilities;

public class WaitQuestionFrame extends JDialog {

	Panel pnlWaitQuestion = new Panel();
	Button btnActualize;

	Server server;

	private WaitQuestionFrame(JDialog dialog) {
		super(dialog, "Wait Question", true);

		initialize();
	}

	void initialize() {
		pnlWaitQuestion.setLayout(null);

		btnActualize.setBounds(6, 6, 150, 20);
		pnlWaitQuestion.add(btnActualize);

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
				} catch (NotConnected nc) {
					MessageUtilities.showError(nc);
					setVisible(false);
					return;
				} catch (Exception ex) {
					MessageUtilities.showError(ex);
					question = null;
				}

				if (question != null) {
					QuestionFrame.show(tmp, question);
				}

			}
		});
	}

	public static void show(JDialog dialog) {
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
