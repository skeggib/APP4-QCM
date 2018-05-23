import java.awt.BorderLayout;
import java.awt.Color;
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
	ScrollPanel scrSheet;
	ArrayList<Panel> questionPanels = new ArrayList<Panel>();
	Panel pnlNew = new Panel();
	Button btnNew = new Button("Add new question");
	Button btnTerminate = new Button("Terminate MCQ");
	Panel pnlTerminate = new Panel();
	
	Sheet sheet = new Sheet();

	private SheetFrame(JDialog dialog) {
		super(dialog, "Session Creation", true);
		initialize();
	}

	private SheetFrame(JDialog dialog, Sheet sheet) {
		super(dialog, "Session Creation", true);
		this.sheet = sheet;
		initialize();
	}

	void initialize() {
		sheet.add(new Question("TEST QUESTION", new Answer("ABC", false), new Answer("DEF", false),
				new Answer("GHI", false), new Answer("JKL", false)));

		setSize(600, 480);

		btnNew.setBounds(6, 6, 150, 20);
		pnlNew.add(btnNew);

		btnTerminate.setBounds(300, 6, 150, 20);
		pnlTerminate.setLayout(null);
		pnlTerminate.add(btnTerminate);
		
		initializeNew();
		initializeTerminate();
		update();
	}

	void initializeNew() {
		JDialog tmp = this;
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sheet.add(QuestionFrame.create(tmp));
				update();
			}
		});
	}
	
	void initializeTerminate() {
		JDialog tmp = this;
		btnTerminate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sheetId = ConnexionFrame.ask(tmp); 
				//ask to server for sheetId
				tmp.setVisible(false);
				tmp.dispose();
			}
		});
	}

	void update() {
		pnlSheet = new Panel();
		pnlSheet.setLayout(null);
		setContentPane(pnlSheet);

		int y = 6;
		for (Question question : sheet.getQuestions()) {
			Panel tmpPanel = create(question);
			tmpPanel.setLocation(6, y);
			y += tmpPanel.getHeight();
			pnlSheet.add(tmpPanel);
		}

		pnlNew.setBounds(12, y, btnNew.getWidth() + 12, btnNew.getHeight() + 12);
		y += pnlNew.getHeight();
		pnlSheet.add(pnlNew);
		
		pnlTerminate.setBounds(12, y, btnTerminate.getX() + btnTerminate.getWidth() + 12, btnTerminate.getHeight() + 12);
		pnlSheet.add(pnlTerminate);
		
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

	public static void show(JDialog dialog) {
		SheetFrame sheet = new SheetFrame(dialog);
		sheet.setLocationRelativeTo(dialog);
		sheet.setVisible(true);
	}
}
