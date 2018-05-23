import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import Controls.Button;
import Controls.Panel;
import Models.Answer;
import Models.Question;
import Models.Sheet;

// start of application
// calls ConnexionFrame, SessionCreationFrame and SessionFrame (as teacher), QuestionFrame (as student)
public class MainMenuFrame extends JDialog {

	Panel pnlMain = new Panel();
	Button btnCreate = new Button("Create session");
	Button btnStart = new Button("Start session");
	Button btnJoin = new Button("Join session");
	
	Sheet testSheet;

	public MainMenuFrame() {
		initialize();
		
		testSheet = new Sheet();
		testSheet.add(new Question("Qui est le plus beau ?", new Answer("Séb, ça s'tient.", true),
				new Answer("Cyril, si 'real' !", true), new Answer("Dimitri, dix mi-tris", true),
				new Answer("Maxime, c'est moi quoi", true)));
		testSheet.add(new Question("Qui a travaillé sur le backend ?", new Answer("Séb, ça s'tient.", true),
				new Answer("Cyril, si 'real' !", true), new Answer("Dimitri, dix mi-tris", false),
				new Answer("Maxime, c'est moi quoi", false)));
		testSheet.add(new Question("Qui n'a pas travaillé sur le frontend ?", new Answer("Séb, ça s'tient.", true),
				new Answer("Cyril, si 'real' !", true), new Answer("Dimitri, dix mi-tris", false),
				new Answer("Maxime, c'est moi quoi", false)));
		testSheet.add(new Question("Qui a travaillé ?", new Answer("Séb, ça s'tient.", true),
				new Answer("Cyril, si 'real' !", true), new Answer("Dimitri, dix mi-tris", true),
				new Answer("Maxime, c'est moi quoi", true)));
		testSheet.add(new Question("Essaie de lécher ton coude.", new Answer("Trop facile !", false),
				new Answer("J'y suis presque !", false), new Answer("C'est possible ?", false),
				new Answer("Bien sûr que non tout le monde sait ça...", true)));
	}

	void initialize() {
		int width = 200, height = 50;

		this.setSize(600, 480);

		pnlMain.setLayout(null);
		pnlMain.add(btnCreate);
		btnCreate.setBounds(this.getWidth() / 2 - width - 3, this.getHeight() / 2 - height - 3, width, height);
		pnlMain.add(btnStart);
		btnStart.setBounds(this.getWidth() / 2 + 3, this.getHeight() / 2 - height - 3, width, height);
		pnlMain.add(btnJoin);
		btnJoin.setBounds(this.getWidth() / 2 - width - 3, this.getHeight() / 2 + 3, 2 * width + 6, height);
		this.setContentPane(pnlMain);

		initializeCreate();
		initializeStart();
		initializeJoin();
	}

	void initializeCreate() {
		JDialog tmp = this;
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SheetFrame.edit(tmp, testSheet);
			}
		});
	}

	void initializeStart() {
		JDialog tmp = this;
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sessionId = "GET FROM SERVER";
				ConnexionFrame.show(tmp, sessionId);
				TestFrame.show(tmp, testSheet);
			}
		});
	}

	void initializeJoin() {
		JDialog tmp = this;
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sessionId = ConnexionFrame.ask(tmp);
				// WaitQuestionFrame.show(sessionId);
			}
		});
	}
}
