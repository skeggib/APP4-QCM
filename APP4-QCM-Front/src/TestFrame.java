import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDialog;

import com.app4qcm.database.Question;
import com.app4qcm.database.Session;
import com.app4qcm.networking.InvalidQuestionNumber;
import com.app4qcm.networking.NotConnected;
import com.app4qcm.networking.Server;
import com.app4qcm.networking.UnrecognizedResponse;

import Controls.Button;
import Controls.Label;
import Controls.Panel;
import Controls.ScrollPanel;
import Controls.TextField;
import Utilities.MessageUtilities;

// calls QuestionFrame (as teacher)
// edit all questions, add new, etc.
public class TestFrame extends JDialog {

	Panel pnlSession = new Panel();
	ScrollPanel scrSession = new ScrollPanel(null);
	Panel pnlInside = new Panel();
	ArrayList<Panel> questionPanels = new ArrayList<Panel>();

	Session session;

	Server server;

	private TestFrame(JDialog dialog, Session session) {
		super(dialog, "Session", true);
		this.session = session;
		initialize();
	}

	void initialize() {
		setPreferredSize(new Dimension(600, 480));
		pack();

		update();
	}

	void initializeStart(Button btnStart, int numQuestion) {
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					server.sendQuestion(numQuestion);
				} catch (Exception ex) {
					MessageUtilities.showError(ex);
				}
			}
		});
	}

	void initializeStop(Button btnStop, int numQuestion) {
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					server.sendQuestion(-1);
				} catch (Exception ex) {
					MessageUtilities.showError(ex);
				}
			}
		});
	}

	void initializeStats(Button btnStats, int numQuestion) {
		btnStats.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// TODO
				} catch (Exception ex) {
					MessageUtilities.showError(ex);
				}
			}
		});
	}

	void update() {
		remove(scrSession);
		setLayout(new BorderLayout());

		pnlSession = new Panel();
		pnlSession.setLayout(null);

		int y = 6;
		for (Question question : session.getQuestions()) {
			Panel tmpPanel = create(question);
			tmpPanel.setLocation(6, y);
			y += tmpPanel.getHeight();
			pnlSession.add(tmpPanel);
		}

		pnlSession.setPreferredSize(new Dimension(550, y));

		scrSession = new ScrollPanel(pnlSession);
		add(scrSession, BorderLayout.CENTER);

		setPreferredSize(new Dimension(600, 480));
		pack();

		revalidate();
	}

	Panel create(Question question) {
		Panel panel = new Panel();
		panel.setLayout(null);

		TextField txtQuestion = new TextField(question.getRep1());
		txtQuestion.setEditable(false);
		txtQuestion.setBounds(6, 6, 400, 20);
		txtQuestion.setLocation(6, 6);
		panel.add(txtQuestion);

		Button btnStart = new Button("Start");
		btnStart.setBounds(412, 6, 100, 20);
		initializeStart(btnStart, question.getId_q());
		panel.add(btnStart);

		Button btnStop = new Button("Stop");
		btnStop.setBounds(412, 32, 100, 20);
		initializeStart(btnStop, question.getId_q());
		panel.add(btnStop);

		Button btnStats = new Button("Stats");
		btnStats.setBounds(412, 58, 100, 20);
		initializeStart(btnStats, question.getId_q());
		panel.add(btnStats);

		int y = txtQuestion.getY() + txtQuestion.getHeight() + 6;
		for (String answer : question.getAnswers()) {
			Label lblAnswer = new Label("• " + answer);
			lblAnswer.setBounds(30, y, 400, 15);
			y += lblAnswer.getHeight() + 6;
			panel.add(lblAnswer);
		}

		panel.setSize(this.getWidth() - 24, y);
		return panel;
	}

	public static void show(JDialog dialog, Session session) {
		TestFrame testFrame = new TestFrame(dialog, session);

		try {
			testFrame.server = new Server();
		} catch (Exception ex) {
			MessageUtilities.showError(ex);
			return;
		}

		try {
			testFrame.server.connect();
		} catch (Exception ex) {
			MessageUtilities.showError(ex);
			return;
		}

		testFrame.setLocationRelativeTo(dialog);
		testFrame.setVisible(true);

		try {
			testFrame.server.close();
		} catch (Exception ex) {
			MessageUtilities.showError(ex);
			return;
		}
	}
}
