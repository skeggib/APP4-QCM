import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JDialog;
import javax.swing.SwingConstants;

import com.app4qcm.database.Statistics;

import Controls.Button;
import Controls.Label;
import Controls.Panel;

public class StatsFrame extends JDialog {
	private static final long serialVersionUID = -2124367271238695825L;

	Panel pnlStat = new Panel();
	Button btnClose = new Button("Close");
	Label lblQuestion = new Label("");
	Label lblAnswer1 = new Label("");
	Label lblAnswer2 = new Label("");
	Label lblAnswer3 = new Label("");
	Label lblAnswer4 = new Label("");
	Label lblA = new Label("A");
	Label lblB = new Label("B");
	Label lblC = new Label("C");
	Label lblD = new Label("D");

	Statistics statistics;

	private StatsFrame(JDialog dialog, Statistics statistics) {
		super(dialog, "Stats", true);
		this.statistics = statistics;
		initialize();
	}

	private void initialize() {
		this.setSize(600, 480);

		pnlStat.setLayout(null);

		Panel pnlQuestion = new Panel();
		pnlQuestion.setLayout(new GridBagLayout());
		GridBagConstraints gbcQuestion = new GridBagConstraints();
		gbcQuestion.anchor = GridBagConstraints.WEST;
		gbcQuestion.fill = GridBagConstraints.HORIZONTAL;

		gbcQuestion.gridx = 0;
		gbcQuestion.gridy = 0;
		lblQuestion.setText(statistics.getQuestion().getTxt_quest());
		pnlQuestion.add(lblQuestion, gbcQuestion);

		gbcQuestion.gridx = 0;
		gbcQuestion.gridy = 1;
		lblAnswer1.setText("A. " + statistics.getQuestion().getRep1());
		pnlQuestion.add(lblAnswer1, gbcQuestion);

		gbcQuestion.gridx = 0;
		gbcQuestion.gridy = 2;
		lblAnswer2.setText("B. " + statistics.getQuestion().getRep2());
		pnlQuestion.add(lblAnswer2, gbcQuestion);

		gbcQuestion.gridx = 0;
		gbcQuestion.gridy = 3;
		lblAnswer3.setText("C. " + statistics.getQuestion().getRep3());
		pnlQuestion.add(lblAnswer3, gbcQuestion);

		gbcQuestion.gridx = 0;
		gbcQuestion.gridy = 4;
		lblAnswer4.setText("D. " + statistics.getQuestion().getRep4());
		pnlQuestion.add(lblAnswer4, gbcQuestion);

		pnlQuestion.setBounds(7, 25, 500, 100);

		pnlStat.add(pnlQuestion);

		// Adding Diagrams in pnlStat
		int repAPourc = 0;
		int repBPourc = 0;
		int repCPourc = 0;
		int repDPourc = 0;
		int repTotal = statistics.getResponses().size();

		for (Entry<String, boolean[]> entry : statistics.getResponses().entrySet()) {
			if (entry.getValue()[0] == true) {
				repAPourc = repAPourc + 1;
			}
			if (entry.getValue()[1] == true) {
				repBPourc = repBPourc + 1;
			}
			if (entry.getValue()[2] == true) {
				repCPourc = repCPourc + 1;
			}
			if (entry.getValue()[3] == true) {
				repDPourc = repDPourc + 1;
			}
		}
		repAPourc = repAPourc * 100 / repTotal;
		repBPourc = repBPourc * 100 / repTotal;
		repCPourc = repCPourc * 100 / repTotal;
		repDPourc = repDPourc * 100 / repTotal;

		Panel pnlDiagrams1 = new Panel();
		pnlDiagrams1.setLayout(null);
		pnlDiagrams1.setBounds(107, 150 + 2 * (100 - repAPourc), 70, 2 * repAPourc);
		pnlDiagrams1.setBackground(Color.BLUE);
		pnlStat.add(pnlDiagrams1);
		lblA.setBounds(107, 360, 70, 20);
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		pnlStat.add(lblA);

		Panel pnlDiagrams2 = new Panel();
		pnlDiagrams2.setLayout(null);
		pnlDiagrams2.setBounds(207, 150 + 2 * (100 - repBPourc), 70, 2 * repBPourc);
		pnlDiagrams2.setBackground(Color.RED);
		pnlStat.add(pnlDiagrams2);
		lblB.setBounds(207, 360, 70, 20);
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		pnlStat.add(lblB);

		Panel pnlDiagrams3 = new Panel();
		pnlDiagrams3.setLayout(null);
		pnlDiagrams3.setBounds(307, 150 + 2 * (100 - repCPourc), 70, 2 * repCPourc);
		pnlDiagrams3.setBackground(Color.GREEN);
		pnlStat.add(pnlDiagrams3);
		lblC.setBounds(307, 360, 70, 20);
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		pnlStat.add(lblC);

		Panel pnlDiagrams4 = new Panel();
		pnlDiagrams4.setLayout(null);
		pnlDiagrams4.setBounds(407, 150 + 2 * (100 - repDPourc), 70, 2 * repDPourc);
		pnlDiagrams4.setBackground(Color.PINK);
		pnlStat.add(pnlDiagrams4);
		lblD.setBounds(407, 360, 70, 20);
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		pnlStat.add(lblD);

		btnClose.setBounds(407, 400, 70, 20);
		pnlStat.add(btnClose);

		setContentPane(pnlStat);

		initializeClose();
	}

	void initializeClose() {
		JDialog tmp = this;
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tmp.setVisible(false);
			}
		});
	}

	public static void show(JDialog dialog, Statistics statistics) {
		StatsFrame statsFrame = new StatsFrame(dialog, statistics);
		statsFrame.setLocationRelativeTo(dialog);
		statsFrame.setVisible(true);
	}
}
