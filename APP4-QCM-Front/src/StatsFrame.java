import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;

import com.app4qcm.database.Question;

import Controls.Button;
import Controls.Label;
import Controls.Panel;

public class StatsFrame extends JDialog {
	private static final long serialVersionUID = -2124367271238695825L;
	
	Panel pnlStat = new Panel();
	Button btnOK = new Button("OK");
	Label lblQuestion = new Label("");
	Label lblAnswer1 = new Label("");
	Label lblAnswer2 = new Label("");
	Label lblAnswer3 = new Label("");
	Label lblAnswer4 = new Label("");
	
	
	private StatsFrame(JDialog dialog, Question[] questionList){
		super(dialog, "Stats", true);
		
		initialize(questionList);
	}
	
	private void initialize(Question[] questionList){
		this.setSize(600, 480);

		pnlStat.setLayout(new GridBagLayout());
		GridBagConstraints gbcFrame = new GridBagConstraints();
		
		Panel pnlQuestion = new Panel();
		pnlQuestion.setLayout(new GridBagLayout());
		GridBagConstraints gbcQuestion = new GridBagConstraints();
		gbcQuestion.anchor = GridBagConstraints.WEST;
		gbcQuestion.fill = GridBagConstraints.HORIZONTAL;

		
		gbcQuestion.gridx = 0;
		gbcQuestion.gridy = 0;
		lblQuestion.setText(questionList[0].getTxt_quest());
		pnlQuestion.add(lblQuestion, gbcQuestion);
		
		gbcQuestion.gridx = 0;
		gbcQuestion.gridy = 1;
		lblAnswer1.setText("A. " + questionList[0].getRep1());
		pnlQuestion.add(lblAnswer1, gbcQuestion);
		
		gbcQuestion.gridx = 0;
		gbcQuestion.gridy = 2;
		lblAnswer2.setText("B. " + questionList[0].getRep2());
		pnlQuestion.add(lblAnswer2, gbcQuestion);
		
		gbcQuestion.gridx = 0;
		gbcQuestion.gridy = 3;
		lblAnswer3.setText("C. " + questionList[0].getRep3());
		pnlQuestion.add(lblAnswer3, gbcQuestion);
		
		gbcQuestion.gridx = 0;
		gbcQuestion.gridy = 4;		
		lblAnswer4.setText("D. " + questionList[0].getRep4());
		pnlQuestion.add(lblAnswer4, gbcQuestion);
		
		gbcFrame.ipady=100;
		gbcFrame.gridx = 0;
		gbcFrame.gridy = 0;
		pnlStat.add(pnlQuestion, gbcFrame);
		
		
		//Adding Diagrams in pnlStat
		int repAPourc = 0;
		int repBPourc = 0;
		int repCPourc = 0;
		int repDPourc = 0;
		int repTotal = questionList.length;
		
		for(int i = 0; i < questionList.length; i++)
		{
			if(questionList[i].getCorrect1() == true){
				repAPourc = repAPourc + 1;
			}else if(questionList[i].getCorrect2() == true){
				repBPourc = repBPourc + 1;
			}else if(questionList[i].getCorrect3() == true){
				repCPourc = repCPourc + 1;
			}else if(questionList[i].getCorrect4() == true){
				repDPourc = repDPourc + 1;
			}
		}
		repAPourc = repAPourc*100/repTotal;
		repBPourc = repBPourc*100/repTotal;
		repCPourc = repCPourc*100/repTotal;
		repDPourc = repDPourc*100/repTotal;
		
		
		Panel pnlDiagrams1 = new Panel();
		pnlDiagrams1.setLayout(new GridBagLayout());
		GridBagConstraints gbcDiagrams1 = new GridBagConstraints();
		gbcDiagrams1.anchor = GridBagConstraints.CENTER;
		
		gbcDiagrams1.ipady = repAPourc;
		gbcDiagrams1.ipadx = 10;
		pnlDiagrams1.setBackground(Color.BLUE);
		gbcFrame.gridx = 1;
		gbcFrame.gridy = 0;
		pnlStat.add(pnlDiagrams1, gbcFrame);
		
		
		Panel pnlDiagrams2 = new Panel();
		pnlDiagrams2.setLayout(new GridBagLayout());
		GridBagConstraints gbcDiagrams2 = new GridBagConstraints();
		gbcDiagrams2.anchor = GridBagConstraints.CENTER;
		
		gbcDiagrams2.ipady = repAPourc;
		gbcDiagrams2.ipadx = 10;
		pnlDiagrams2.setBackground(Color.RED);
		gbcFrame.gridx = 1;
		gbcFrame.gridy = 1;
		pnlStat.add(pnlDiagrams2, gbcFrame);
		
		
		Panel pnlDiagrams3 = new Panel();
		pnlDiagrams3.setLayout(new GridBagLayout());
		GridBagConstraints gbcDiagrams3 = new GridBagConstraints();
		gbcDiagrams3.anchor = GridBagConstraints.CENTER;
		
		gbcDiagrams3.ipady = repAPourc;
		gbcDiagrams3.ipadx = 10;
		pnlDiagrams3.setBackground(Color.GREEN);
		gbcFrame.gridx = 1;
		gbcFrame.gridy = 0;
		pnlStat.add(pnlDiagrams3, gbcFrame);
		
		
		Panel pnlDiagrams4 = new Panel();
		pnlDiagrams4.setLayout(new GridBagLayout());
		GridBagConstraints gbcDiagrams4 = new GridBagConstraints();
		gbcDiagrams4.anchor = GridBagConstraints.CENTER;
		
		gbcDiagrams4.ipady = repAPourc;
		gbcDiagrams4.ipadx = 10;
		pnlDiagrams4.setBackground(Color.PINK);
		gbcFrame.gridx = 1;
		gbcFrame.gridy = 0;
		pnlStat.add(pnlDiagrams4, gbcFrame);
	}

	public static void show(JDialog dialog, Question[] questionList) {
		StatsFrame statsFrame = new StatsFrame(dialog, questionList);
		statsFrame.setLocationRelativeTo(dialog);
		statsFrame.setVisible(true);
	}
}
