import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;

import Controls.Button;
import Controls.Label;
import Controls.Panel;
import Controls.ScrollPanel;
import Controls.TextField;
import Models.Answer;
import Models.Question;
import Models.Sheet;

// calls QuestionFrame (as teacher)
// edit all questions, add new, etc.
public class TestFrame extends JDialog {

	Panel pnlSheet = new Panel();
	ScrollPanel scrSheet = new ScrollPanel(null);
	Panel pnlInside = new Panel();
	ArrayList<Panel> questionPanels = new ArrayList<Panel>();

	Sheet sheet;

	private TestFrame(JDialog dialog, Sheet sheet) {
		super(dialog, "Sheet", true);
		this.sheet = sheet;
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
				// send server "start sessionId numQuestion"
			}
		});
	}
	
	void initializeStop(Button btnStop, int numQuestion) {
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// send server "stop sessionId numQuestion"
			}
		});
	}
	
	void initializeStats(Button btnStats, int numQuestion) {
		btnStats.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// send server "stats sessionId numQuestion"
			}
		});
	}

	void update() {
		remove(scrSheet);
		setLayout(new BorderLayout());

		pnlSheet = new Panel();
		pnlSheet.setLayout(null);

		int y = 6;
		for (Question question : sheet.getQuestions()) {
			Panel tmpPanel = create(question);
			tmpPanel.setLocation(6, y);
			y += tmpPanel.getHeight();
			pnlSheet.add(tmpPanel);
		}

		pnlSheet.setPreferredSize(new Dimension(550, y));

		scrSheet = new ScrollPanel(pnlSheet);
		add(scrSheet, BorderLayout.CENTER);

		setPreferredSize(new Dimension(600, 480));
		pack();

		revalidate();
	}

	Panel create(Question question) {
		Panel panel = new Panel();
		panel.setLayout(null);

		TextField txtQuestion = new TextField(question.text);
		txtQuestion.setEditable(false);
		txtQuestion.setBounds(6, 6, 400, 20);
		txtQuestion.setLocation(6, 6);
		panel.add(txtQuestion);
		
		Button btnStart = new Button("Start");
		btnStart.setBounds(412, 6, 100, 20);
		initializeStart(btnStart, question.numQuestion);
		panel.add(btnStart);
		
		Button btnStop = new Button("Stop");
		btnStop.setBounds(412, 32, 100, 20);
		initializeStart(btnStop, question.numQuestion);
		panel.add(btnStop);
		
		Button btnStats = new Button("Stats");
		btnStats.setBounds(412, 58, 100, 20);
		initializeStart(btnStats, question.numQuestion);
		panel.add(btnStats);

		int y = txtQuestion.getY() + txtQuestion.getHeight() + 6;
		for (Answer answer : question.answers) {
			Label lblAnswer = new Label("• " + answer.text);
			lblAnswer.setBounds(30, y, 400, 15);
			y += lblAnswer.getHeight() + 6;
			panel.add(lblAnswer);
		}

		panel.setSize(this.getWidth() - 24, y);
		return panel;
	}

	public static void show(JDialog dialog, Sheet sheet) {
		TestFrame testFrame = new TestFrame(dialog, sheet);
		testFrame.setLocationRelativeTo(dialog);
		testFrame.setVisible(true);
	}
}
