import javax.swing.JFrame;

import Models.Question;

// 
public class QuestionFrame extends JFrame {

	// editable as teacher
	// non-editable as student
	private QuestionFrame(Question question, boolean editable) {
		// TODO
	}
	
	public static Question create() {
		return new Question();
	}
	
	public static void show(Question question, boolean editable) {
		
	}
	
}
