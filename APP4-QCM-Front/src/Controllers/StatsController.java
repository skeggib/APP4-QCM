package Controllers;

import com.app4qcm.database.Question;

public final class StatsController extends AbstractController {

	public static Question[] get(int numQuestion){
		Question[] questionList = new Question[4];
		
		Question q1 = new Question("Q1 : Quelle est la couleur du cheval blanc d'HenryIV ?", "Rouge", "Bleu", "Noir", "Blanc", false, false, false, false);
		Question q2 = new Question("Q1 : Quelle est la couleur du cheval blanc d'HenryIV ?", "Rouge", "Bleu", "Noir", "Blanc", false, false, false, false);
		Question q3 = new Question("Q1 : Quelle est la couleur du cheval blanc d'HenryIV ?", "Rouge", "Bleu", "Noir", "Blanc", false, false, false, false);
		Question q4 = new Question("Q1 : Quelle est la couleur du cheval blanc d'HenryIV ?", "Rouge", "Bleu", "Noir", "Blanc", false, false, false, false);

		questionList[0] = q1;
		questionList[1] = q2;
		questionList[2] = q3;
		questionList[3] = q4;
		
		return questionList;
	}
}
