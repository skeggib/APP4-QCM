import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.app4qcm.database.Question;
import com.app4qcm.database.Session;
import com.app4qcm.networking.InvalidSessionName;
import com.app4qcm.networking.Server;
import com.app4qcm.networking.SessionNameAlreadyUsed;
import com.app4qcm.networking.UnrecognizedResponse;

import Controls.Button;
import Controls.Label;
import Controls.Panel;
import Controls.ScrollPanel;
import Controls.TextField;
import Utilities.MessageUtilities;

// calls QuestionFrame (as teacher)
// edit all questions, add new, etc.
public class SessionFrame extends JDialog {

	Panel pnlSession = new Panel();
	ScrollPanel scrSession = new ScrollPanel(null);
	Panel pnlInside = new Panel();
	ArrayList<Panel> questionPanels = new ArrayList<Panel>();
	Panel pnlLast = new Panel();
	Button btnNew = new Button("Add new question");
	Button btnTerminate = new Button("Terminate MCQ");

	Session session = new Session();

	public SessionFrame(JDialog dialog) {
		super(dialog, "Session", true);
		initialize();
	}

	private SessionFrame(JDialog dialog, Session session) {
		super(dialog, "Session", true);
		this.session = session;
		initialize();
	}

	void initialize() {
		setPreferredSize(new Dimension(600, 480));

		pnlLast.setLayout(null);
		btnNew.setBounds(6, 6, 150, 20);
		btnTerminate.setBounds(362, 6, 150, 20);
		pnlLast.add(btnNew);
		pnlLast.add(btnTerminate);
		pack();

		initializeNew();
		initializeTerminate();
		update();
	}

	void initializeNew() {
		JDialog tmp = this;
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				session.add(QuestionFrame.create(tmp));
				update();
				scrSession.scrollToBottom();
			}
		});
	}

	void initializeTerminate() {
		JDialog tmp = this;
		btnTerminate.addActionListener(new ActionListener() {
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

				boolean isCreated = false;
				do {
					session.setName(ConnexionFrame.ask(tmp));

					try {
						server.createSession(session);
						isCreated = true;
					} catch (InvalidSessionName | SessionNameAlreadyUsed se) {
						MessageUtilities.showError(se);
						session.setName(ConnexionFrame.ask(tmp));
					} catch (Exception ex) {
						MessageUtilities.showError(ex);
						return;
					}
				} while (!isCreated);

				tmp.setVisible(false);
				tmp.dispose();
			}
		});
	}

	void initializeDelete(Button btnDelete, int id_q) {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int scroll = scrSession.getScrollValue();
				session.removeId(id_q);
				update();
				scrSession.setScrollValue(scroll);
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

		pnlLast.setBounds(6, y, btnTerminate.getX() + btnTerminate.getWidth() + 12, btnTerminate.getHeight() + 62);
		y += pnlLast.getHeight();
		pnlSession.add(pnlLast);

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

		TextField txtQuestion = new TextField(question.getTxt_quest());
		txtQuestion.setEditable(false);
		txtQuestion.setBounds(6, 6, 400, 20);
		txtQuestion.setLocation(6, 6);
		panel.add(txtQuestion);

		Button btnDelete = new Button("Delete");
		btnDelete.setBounds(412, 6, 100, 20);
		initializeDelete(btnDelete, question.getId_q());
		panel.add(btnDelete);

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

	public static void show(JDialog dialog) {
		SessionFrame sessionFrame = new SessionFrame(dialog);
		sessionFrame.setLocationRelativeTo(dialog);
		sessionFrame.setVisible(true);
	}

	public static void edit(JDialog dialog, Session session) {
		SessionFrame sessionFrame = new SessionFrame(dialog, session);
		sessionFrame.setLocationRelativeTo(dialog);
		sessionFrame.setVisible(true);
	}
}
