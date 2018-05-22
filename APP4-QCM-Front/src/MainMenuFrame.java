import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import Controls.Button;
import Controls.Panel;

// start of application
// calls ConnexionFrame, SessionCreationFrame and SessionFrame (as teacher), QuestionFrame (as student)
public class MainMenuFrame extends JDialog {

	Panel pnlMain = new Panel();
	Button btnCreate = new Button("Create session");
	Button btnStart = new Button("Start session");
	Button btnJoin = new Button("Join session");

	public MainMenuFrame() {
		initialize();
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
				//SessionCreationFrame.show(tmp);
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
				//SessionFrame.show(tmp, sessionId);
			}
		});
	}

	void initializeJoin() {
		JDialog tmp = this;
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sessionId = ConnexionFrame.ask(tmp);
				//WaitQuestionFrame.show(sessionId);
			}
		});
	}
}
