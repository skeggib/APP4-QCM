import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JWindow;

import Controls.Button;
import Controls.CheckBox;
import Controls.Label;
import Controls.Panel;
import Controls.TextField;
import Models.Answer;
import Models.Question;

// 
public class QuestionFrame extends JDialog {

	Panel pnlQuestionFrame = new Panel();
	
	Label lblQuestion = new Label("Question n°");
	Label lblNumQuestion = new Label("");
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
		
	// editable = true
	private QuestionFrame(JDialog dialog) {
		super(dialog, "", true);
		this.initialize();
	}
	
	// editable as teacher
	// non-editable as student	
	private QuestionFrame(JDialog dialog, Question question, boolean editable) {
		this.initialize();
		
		txtQuestion.setEditable(editable);
		txtAnswer1.setEditable(editable);
		txtAnswer2.setEditable(editable);
		txtAnswer3.setEditable(editable);
		txtAnswer4.setEditable(editable);
		
		lblNumQuestion.setText(String.valueOf(question.numQuestion));
		lblQuestion.setText(lblQuestion.getText()+lblNumQuestion.getText());
		txtQuestion.setText(question.text);
		txtAnswer1.setText(question.answers[0].text);
		cbAnswer1.setSelected(question.answers[0].checked);
		txtAnswer2.setText(question.answers[1].text);
		cbAnswer2.setSelected(question.answers[1].checked);
		txtAnswer3.setText(question.answers[2].text);
		cbAnswer3.setSelected(question.answers[2].checked);
		txtAnswer4.setText(question.answers[3].text);
		cbAnswer4.setSelected(question.answers[3].checked);
	}
	
	void initialize() {
		this.setSize(600, 480);
		
		pnlQuestionFrame.setLayout(new GridBagLayout());
		GridBagConstraints gbcFrame = new GridBagConstraints();
		
		//Panel Question
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
		
		//Add to pnlQuestionFrame
		gbcFrame.ipady = 50;
		gbcFrame.fill = GridBagConstraints.BOTH;
		gbcFrame.gridx = 0;
		gbcFrame.gridy = 1;
		pnlQuestionFrame.add(pnlQuestion, gbcFrame);
		
		//Panel Answer
		Panel pnlAnswer = new Panel();
		pnlAnswer.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.ipady = 20;
		gbc.insets = new Insets(5,0,0,0);
		
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
		
		//Add to pnlQuestionFrame
		gbcFrame.ipady = 100;
		gbcFrame.ipadx = 280;
		gbcFrame.fill = GridBagConstraints.BOTH;
		gbcFrame.gridx = 0;
		gbcFrame.gridy = 2;
		pnlQuestionFrame.add(pnlAnswer, gbcFrame);
		
		//Panel Bouton
		Panel pnlBouton = new Panel();
		pnlBouton.setLayout(new GridBagLayout());
		GridBagConstraints gbcBouton = new GridBagConstraints();
		
		gbcBouton.ipadx = 50;
		gbcBouton.fill = GridBagConstraints.HORIZONTAL;
		gbcBouton.gridx = 3;
		gbc.gridwidth = 1;
		gbcBouton.gridy = 0;
		pnlBouton.add(btnValidate, gbcBouton);
		
		//Add to pnlQuestionFrame
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
		btnValidate.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	int numQuestion = Integer.parseInt(lblNumQuestion.getText());
		    	String questionText = tmp.txtQuestion.getText();
				Answer answer1 = new Answer(tmp.txtAnswer1.getText(), tmp.cbAnswer1.isSelected());
				Answer answer2 = new Answer(tmp.txtAnswer2.getText(), tmp.cbAnswer2.isSelected());
				Answer answer3 = new Answer(tmp.txtAnswer3.getText(), tmp.cbAnswer3.isSelected());
				Answer answer4 = new Answer(tmp.txtAnswer4.getText(), tmp.cbAnswer4.isSelected());
				
				result = new Question(questionText, answer1, answer2, answer3, answer4);
				tmp.setVisible(false);
		    }
		});
	}
	
	// for teacher
	public static Question create(JDialog dialog) {
		QuestionFrame questionFrame = new QuestionFrame(dialog);
		questionFrame.setLocationRelativeTo(dialog);
		questionFrame.setVisible(true);
		return questionFrame.result;
	}
	
	public static Question edit(JDialog dialog, Question question) {
		QuestionFrame questionFrame = new QuestionFrame(dialog, question, true);
		questionFrame.setLocationRelativeTo(dialog);
		questionFrame.setVisible(true);
		return questionFrame.result;
	}

	public static void show(JDialog dialog, Question question) {
		QuestionFrame questionFrame = new QuestionFrame(dialog, question, false);
		questionFrame.setLocationRelativeTo(dialog);
		questionFrame.setVisible(true);
	}
	
}
