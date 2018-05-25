import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.SwingConstants;

import com.app4qcm.database.Question;
import com.app4qcm.networking.InvalidParameter;
import com.app4qcm.networking.NoQuestionAvailable;
import com.app4qcm.networking.NotConnected;
import com.app4qcm.networking.StudentNameAlreadyUsed;
import com.app4qcm.networking.UnrecognizedResponse;

import Controllers.QuestionController;
import Controllers.SessionController;
import Controllers.StatsController;
import Controls.Button;
import Controls.Label;
import Controls.Panel;
import Utilities.MessageUtilities;

public class WaitQuestionFrame extends JDialog {
	private static final long serialVersionUID = -7324420178436852752L;

	Panel pnlWaitQuestion = new Panel();
	Button btnActualize = new Button("Actualize");
	Label lblWaiting = new Label("Waiting");

	Thread thread;

	private WaitQuestionFrame(JDialog dialog) {
		super(dialog, "Wait Question", true);

		initialize();
	}

	void initialize() {
		setSize(320, 200);

		pnlWaitQuestion.setLayout(null);
		setContentPane(pnlWaitQuestion);

		btnActualize.setBounds(6, 6, 150, 20);
		lblWaiting.setHorizontalAlignment(SwingConstants.CENTER);
		// pnlWaitQuestion.add(btnActualize);
		pnlWaitQuestion.add(lblWaiting);

		lblWaiting.setBounds(pnlWaitQuestion.getBounds());

		initializeThread();
	}

	int previousQuestionId = -1;

	void initializeThread() {
		WaitQuestionFrame tmp = this;
		thread = new Thread() {
			@Override
			public void run() {
				while (tmp.isVisible()) {
					updateWaiter();
					try {
						if (SessionController.get() == null)
							stopSession();
						else {
							Question question;
							question = QuestionController.getEmpty();
							if (question.getId_q() != previousQuestionId) {
								previousQuestionId = question.getId_q();
								tmp.showQuestion(question);
							}
						}
					} catch (NoQuestionAvailable nqa) {
						hideQuestion();
					} catch (Exception ex) {
						showError(ex);
					}

					try {
						sleep(500);
					} catch (Exception ex) {
					}
				}
			}
		};
		thread.start();
	}

	void updateWaiter() {
		if (lblWaiting.getText().endsWith("..."))
			lblWaiting.setText(lblWaiting.getText().substring(0, lblWaiting.getText().length() - 3));
		else
			lblWaiting.setText(lblWaiting.getText() + ".");
		revalidate();
	}

	void showQuestion(Question question) {
		try {
			QuestionController.answer(QuestionFrame.ask(this, question));
		} catch (Exception ex) {
			MessageUtilities.showError(ex);
		}
	}

	void hideQuestion() {
		try {
			QuestionController.answer(QuestionFrame.forceClose());
		} catch (Exception ex) {
			MessageUtilities.showError(ex);
		}
	}

	void stopSession() {
		setVisible(false);
	}

	void showError(Exception ex) {
		MessageUtilities.showError(ex);
	}

	public static void show(JDialog dialog, String sessionName) {
		WaitQuestionFrame waitQuestion = new WaitQuestionFrame(dialog);

		boolean isConnected = false;

		do {
			try {
				SessionController.join(sessionName, ConnexionFrame.ask(dialog));
				isConnected = true;
			} catch (StudentNameAlreadyUsed snau) {
				MessageUtilities.showError(snau);
			} catch (Exception ex) {
				MessageUtilities.showError(ex);
				return;
			}
		} while (!isConnected);

		waitQuestion.setLocationRelativeTo(dialog);
		waitQuestion.setVisible(true);
	}
}
