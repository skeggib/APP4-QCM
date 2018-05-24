import javax.swing.JDialog;

import com.app4qcm.database.Question;

public class StatsFrame extends JDialog {
	private static final long serialVersionUID = -2124367271238695825L;

	public static void show(JDialog dialog, Question[] questionList) {
		Question q1 = new Question("Q1 : Quelle est la couleur du cheval blanc d'HenryIV ?", "Rouge", "Bleu", "Noir", "Blanc", false, false, false, false);
		Question q2 = new Question("Q1 : Quelle est la couleur du cheval blanc d'HenryIV ?", "Rouge", "Bleu", "Noir", "Blanc", false, false, false, false);
		Question q3 = new Question("Q1 : Quelle est la couleur du cheval blanc d'HenryIV ?", "Rouge", "Bleu", "Noir", "Blanc", false, false, false, false);
		Question q4 = new Question("Q1 : Quelle est la couleur du cheval blanc d'HenryIV ?", "Rouge", "Bleu", "Noir", "Blanc", false, false, false, false);

		questionList[0] = q1;
		questionList[1] = q2;
		questionList[2] = q3;
		questionList[3] = q4;
		
		
		
	}
}
