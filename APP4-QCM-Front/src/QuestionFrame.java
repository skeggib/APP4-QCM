import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.app4qcm.database.Question;

import Controls.Button;
import Controls.CheckBox;
import Controls.Label;
import Controls.Panel;
import Controls.TextField;

// 
public class QuestionFrame extends JDialog {
	private static final long serialVersionUID = 5003809370524506931L;

	Panel pnlQuestionFrame = new Panel();

	Label lblQuestion = new Label("Question n°");
	TextField txtQuestion = new TextField("Enter your question here...");

	CheckBox cbAnswer1 = new CheckBox();
	TextField txtAnswer1 = new TextField("Enter your answer here...");
	CheckBox cbAnswer2 = new CheckBox();
	TextField txtAnswer2 = new TextField("Enter your answer here...");
	CheckBox cbAnswer3 = new CheckBox();
	TextField txtAnswer3 = new TextField("Enter your answer here...");
	CheckBox cbAnswer4 = new CheckBox();
	TextField txtAnswer4 = new TextField("Enter your answer here...");

	Button btnValidate = new Button("Validate");

	static QuestionFrame currentQuestionFrame;

	int id_q;

	// editable = true
	private QuestionFrame(JDialog dialog) {
		super(dialog, "Question", true);
		this.initialize();
	}

	// editable as teacher
	// non-editable as student
	private QuestionFrame(JDialog dialog, Question question, boolean editable) {
		super(dialog, "Question", true);
		this.initialize();

		txtQuestion.setEditable(editable);
		txtAnswer1.setEditable(editable);
		txtAnswer2.setEditable(editable);
		txtAnswer3.setEditable(editable);
		txtAnswer4.setEditable(editable);

		id_q = question.getId_q();
		lblQuestion.setText(lblQuestion.getText() + String.valueOf(id_q));
		txtQuestion.setText(question.getTxt_quest());
		txtAnswer1.setText(question.getRep1());
		cbAnswer1.setSelected(question.getCorrect1());
		txtAnswer2.setText(question.getRep2());
		cbAnswer2.setSelected(question.getCorrect2());
		txtAnswer3.setText(question.getRep3());
		cbAnswer3.setSelected(question.getCorrect3());
		txtAnswer4.setText(question.getRep4());
		cbAnswer4.setSelected(question.getCorrect4());
	}

	void initialize() {
		currentQuestionFrame = this;

		this.setSize(600, 480);

		pnlQuestionFrame.setLayout(new GridBagLayout());
		GridBagConstraints gbcFrame = new GridBagConstraints();

		// Panel Question
		Panel pnlQuestion = new Panel();
		pnlQuestion.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 20;

		c.ipadx = 30;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 0;
		pnlQuestion.add(lblQuestion, c);

		c.ipadx = 280;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 0;
		pnlQuestion.add(txtQuestion, c);

		// Add to pnlQuestionFrame
		gbcFrame.ipady = 50;
		gbcFrame.fill = GridBagConstraints.BOTH;
		gbcFrame.gridx = 0;
		gbcFrame.gridy = 1;
		pnlQuestionFrame.add(pnlQuestion, gbcFrame);

		// Panel Answer
		Panel pnlAnswer = new Panel();
		pnlAnswer.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.ipady = 20;
		gbc.insets = new Insets(5, 0, 0, 0);

		gbc.ipadx = 30;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridy = 0;
		pnlAnswer.add(cbAnswer1, gbc);

		gbc.ipadx = 280;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.gridy = 0;
		pnlAnswer.add(txtAnswer1, gbc);

		gbc.ipadx = 30;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		pnlAnswer.add(cbAnswer2, gbc);

		gbc.ipadx = 280;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.gridy = 1;
		pnlAnswer.add(txtAnswer2, gbc);

		gbc.ipadx = 30;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridy = 2;
		pnlAnswer.add(cbAnswer3, gbc);

		gbc.ipadx = 280;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.gridy = 2;
		pnlAnswer.add(txtAnswer3, gbc);

		gbc.ipadx = 30;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridy = 3;
		pnlAnswer.add(cbAnswer4, gbc);

		gbc.ipadx = 280;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.gridy = 3;
		pnlAnswer.add(txtAnswer4, gbc);

		// Add to pnlQuestionFrame
		gbcFrame.ipady = 100;
		gbcFrame.ipadx = 280;
		gbcFrame.fill = GridBagConstraints.BOTH;
		gbcFrame.gridx = 0;
		gbcFrame.gridy = 2;
		pnlQuestionFrame.add(pnlAnswer, gbcFrame);

		// Panel Bouton
		Panel pnlBouton = new Panel();
		pnlBouton.setLayout(new GridBagLayout());
		GridBagConstraints gbcBouton = new GridBagConstraints();

		gbcBouton.ipadx = 50;
		gbcBouton.fill = GridBagConstraints.HORIZONTAL;
		gbcBouton.gridx = 3;
		gbc.gridwidth = 1;
		gbcBouton.gridy = 0;
		pnlBouton.add(btnValidate, gbcBouton);

		// Add to pnlQuestionFrame
		gbcFrame.anchor = GridBagConstraints.EAST;
		gbcFrame.ipady = 50;
		gbcFrame.fill = GridBagConstraints.BOTH;
		gbcFrame.gridx = 0;
		gbcFrame.gridy = 3;
		pnlQuestionFrame.add(pnlBouton, gbcFrame);

		this.setContentPane(pnlQuestionFrame);

		initializeValidate();
	}

	Question result;

	void initializeValidate() {
		QuestionFrame tmp = this;
		btnValidate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				result = new Question(tmp.txtQuestion.getText(), tmp.txtAnswer1.getText(), tmp.cbAnswer1.isSelected(),
						tmp.txtAnswer2.getText(), tmp.cbAnswer2.isSelected(), tmp.txtAnswer3.getText(),
						tmp.cbAnswer3.isSelected(), tmp.txtAnswer4.getText(), tmp.cbAnswer4.isSelected());
				tmp.setVisible(false);
			}
		});
	}

	// for teacher
	public static Question create(JDialog dialog) {
		QuestionFrame questionFrame = new QuestionFrame(dialog);
		questionFrame.setLocationRelativeTo(dialog);
		questionFrame.setVisible(true);
		currentQuestionFrame = null;
		return questionFrame.result;
	}

	public static Question ask(JDialog dialog, Question question) {
		QuestionFrame questionFrame = new QuestionFrame(dialog, question, false);
		questionFrame.setLocationRelativeTo(dialog);
		questionFrame.setVisible(true);
		currentQuestionFrame = null;
		return questionFrame.result;
	}

	public static void show(JDialog dialog, Question question) {
		QuestionFrame questionFrame = new QuestionFrame(dialog, question, false);
		questionFrame.setLocationRelativeTo(dialog);
		questionFrame.setVisible(true);
		currentQuestionFrame = null;
	}

	public static Question forceClose() {
		if (currentQuestionFrame != null) {
			currentQuestionFrame.btnValidate.doClick();
			return currentQuestionFrame.result;
		} else
			return null;
	}
}
