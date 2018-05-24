import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.app4qcm.database.Question;
import com.app4qcm.networking.NoQuestionAvailable;

import Controllers.QuestionController;
import Controllers.SessionController;
import Controls.Button;
import Controls.Panel;
import Utilities.MessageUtilities;

public class WaitQuestionFrame extends JDialog {
	private static final long serialVersionUID = -7324420178436852752L;
	
	Panel pnlWaitQuestion = new Panel();
	Button btnActualize = new Button("Actualize");

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
					QuestionController.get();
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
			SessionController.join(sessionName, ConnexionFrame.ask(dialog));
		} catch (Exception ex) {
			MessageUtilities.showError(ex);
			return;
		}

		waitQuestion.setLocationRelativeTo(dialog);
		waitQuestion.setVisible(true);
	}
}
