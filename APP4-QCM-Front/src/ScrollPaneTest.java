import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controls.Button;
import Controls.Label;
import Controls.Panel;
import Controls.ScrollPanel;
import Controls.TextField;
import Models.Answer;
import Models.Question;
import Models.Sheet;

public class ScrollPaneTest extends JDialog {

	Panel pnlSheet = new Panel();
	ScrollPanel scrSheet = new ScrollPanel(null);
	Panel pnlInside = new Panel();
	ArrayList<Panel> questionPanels = new ArrayList<Panel>();
	Panel pnlLast = new Panel();
	Button btnNew = new Button("Add new question");
	Button btnTerminate = new Button("Terminate MCQ");

	Sheet sheet = new Sheet();

	public ScrollPaneTest() {
		setPreferredSize(new Dimension(600, 480));

		initialize();
	}

	void initialize() {
		sheet.add(new Question(1, "TEST QUESTION", new Answer("ABC", false), new Answer("DEF", false),
				new Answer("GHI", false), new Answer("JKL", false)));

		setSize(600, 480);

		pnlLast.setLayout(null);
		btnNew.setBounds(6, 6, 150, 20);
		btnTerminate.setBounds(300, 6, 150, 20);
		pnlLast.add(btnNew);
		pnlLast.add(btnTerminate);

		initializeNew();
		initializeTerminate();
		update();
	}

	void initializeNew() {
		JDialog tmp = this;
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int num = sheet.getQuestions().size() + 1;
				sheet.add(QuestionFrame.create(tmp, num));
				update();
				scrSheet.scrollToBottom();
			}
		});
	}

	void initializeTerminate() {
		JDialog tmp = this;
		btnTerminate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sheetId = ConnexionFrame.ask(tmp);
				// ask to server for sheetId
				tmp.setVisible(false);
				tmp.dispose();
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

		pnlLast.setBounds(12, y, btnTerminate.getX() + btnTerminate.getWidth() + 12,
				btnTerminate.getHeight() + 62);
		y += pnlLast.getHeight();
		pnlSheet.add(pnlLast);

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
		txtQuestion.setBounds(6, 6, 400, 30);
		txtQuestion.setLocation(6, 6);
		panel.add(txtQuestion);

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
}

class Panneau extends JPanel {

	public Panneau() {
		setPreferredSize(new Dimension(2000, 1000));
	}
}