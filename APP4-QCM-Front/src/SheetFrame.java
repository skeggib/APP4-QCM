import java.util.ArrayList;

import javax.swing.JDialog;

import Controls.Button;
import Controls.Label;
import Controls.Panel;
import Controls.TextField;
import Models.Answer;
import Models.Question;
import Models.Sheet;

// calls QuestionFrame (as teacher)
// edit all questions, add new, etc.
public class SheetFrame extends JDialog {

	Panel pnlSheet = new Panel();
	ArrayList<Panel> questionPanels = new ArrayList<Panel>();
	Panel pnlQuestion = new Panel();
	Button btnQuestion = new Button("Add new question");

	Sheet sheet = new Sheet();

	private SheetFrame(JDialog dialog) {
		super(dialog, "Session Creation", true);
		initialize();
	}

	private SheetFrame(JDialog dialog, Sheet session) {
		super(dialog, "Session Creation", true);
		this.sheet = session;
		initialize();
	}

	void initialize() {
		sheet.add(new Question("TEST QUESTION", new Answer("ABC", false), new Answer("DEF", false),
				new Answer("GHI", false), new Answer("JKL", false)));

		this.setSize(600, 480);

		pnlQuestion.add(btnQuestion);
		this.setContentPane(pnlSheet);

		update();
	}

	void update() {
		pnlSheet.setLayout(null);
		pnlSheet.removeAll();

		int y = 12;
		for (Question question : sheet.getQuestions()) {
			Panel tmpPanel = create(question);
			tmpPanel.setLocation(12, y);
			y += tmpPanel.getHeight();
			pnlSheet.add(tmpPanel);
		}

		pnlQuestion.setLocation(12, y);
		pnlSheet.add(pnlQuestion);
	}

	Panel create(Question question) {
		Panel panel = new Panel();
		panel.setLayout(null);

		TextField txtQuestion = new TextField(question.text);
		txtQuestion.setEditable(false);
		txtQuestion.setLocation(6, 6);
		panel.add(txtQuestion);

		Label lblAnswer1 = new Label(question.answers[0].text);
		lblAnswer1.setLocation(6, txtQuestion.getY() + txtQuestion.getHeight() + 6);
		panel.add(lblAnswer1);

		panel.setSize(this.getWidth() - 24, lblAnswer1.getY() + lblAnswer1.getHeight() + 6);
		return panel;
	}

	public static void show(JDialog dialog) {
		SheetFrame sheet = new SheetFrame(dialog);
		sheet.setLocationRelativeTo(dialog);
		sheet.setVisible(true);
	}
}
