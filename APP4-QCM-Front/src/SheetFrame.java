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
public class SheetFrame extends JDialog {

	Panel pnlSheet = new Panel();
	ScrollPanel scrSheet = new ScrollPanel(null);
	Panel pnlInside = new Panel();
	ArrayList<Panel> questionPanels = new ArrayList<Panel>();
	Panel pnlLast = new Panel();
	Button btnNew = new Button("Add new question");
	Button btnTerminate = new Button("Terminate MCQ");

	Sheet sheet = new Sheet();

	public SheetFrame(JDialog dialog) {
		super(dialog, "Sheet", true);
		initialize();
	}

	private SheetFrame(JDialog dialog, Sheet sheet) {
		super(dialog, "Sheet", true);
		this.sheet = sheet;
		initialize();
	}

	void initialize() {
		sheet.add(new Question("TEST QUESTION", new Answer("ABC", false), new Answer("DEF", false),
				new Answer("GHI", false), new Answer("JKL", false)));

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
	
	void initializeDelete(Button btnDelete, int numQuestion) {
		btnDelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				sheet.removeAt(numQuestion - 1);
				update();
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

		pnlLast.setBounds(6, y, btnTerminate.getX() + btnTerminate.getWidth() + 12, btnTerminate.getHeight() + 62);
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
		txtQuestion.setBounds(6, 6, 400, 20);
		txtQuestion.setLocation(6, 6);
		panel.add(txtQuestion);
		
		Button btnDelete = new Button("Delete");
		btnDelete.setBounds(412, 6, 100, 20);
		initializeDelete(btnDelete, question.numQuestion);
		panel.add(btnDelete);

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

	public static void show(JDialog dialog) {
		SheetFrame sheetFrame = new SheetFrame(dialog);
		sheetFrame.setLocationRelativeTo(dialog);
		sheetFrame.setVisible(true);
	}

	public static void edit(JDialog dialog, Sheet sheet) {
		SheetFrame sheetFrame = new SheetFrame(dialog, sheet);
		sheetFrame.setLocationRelativeTo(dialog);
		sheetFrame.setVisible(true);
	}
}
