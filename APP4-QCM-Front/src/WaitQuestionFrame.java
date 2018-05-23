import javax.swing.JDialog;

public class WaitQuestionFrame extends JDialog {

	private WaitQuestionFrame(JDialog dialog) {
		super(dialog, "Wait Question", true);
		
		initialize();
	}
	
	void initialize() {
		
	}
	
	public static void show(JDialog dialog) {
		WaitQuestionFrame waitQuestion = new WaitQuestionFrame(dialog);
		waitQuestion.setVisible(true);
	}
}
