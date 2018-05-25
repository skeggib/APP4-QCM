import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import Controls.Button;
import Controls.Label;
import Controls.Panel;
import Controls.TextField;

public class TextFrame extends JDialog {
	private static final long serialVersionUID = 63917319005319122L;

	static TextField txtSession = new TextField("Enter your Session ID...");
	Button btnCopy = new Button("Copy");
	Button btnOK = new Button("OK");
	Label lblSession = new Label("Your SheetID is : ");

	Panel pnlConnexion = new Panel();

	TextFrame(JDialog dialog, String title) {
		super(dialog, title, true);

		lblSession.setText("Enter " + title + " : ");
		txtSession.setEditable(true);

		initialize();
	}

	void initialize() {
		this.setSize(300, 100);

		txtSession.setText("Enter your Session ID...");
		pnlConnexion.add(lblSession);
		pnlConnexion.add(txtSession);
		pnlConnexion.add(btnOK);
		this.setContentPane(pnlConnexion);

		btnOK.requestFocus();

		initializeOK();
	}

	void initializeOK() {
		JDialog tmp = this;
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tmp.setVisible(false);
			}
		});
	}

	void initializeCopy() {
		btnCopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(txtSession.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				txtSession.requestFocus();
				txtSession.selectAll();
			}
		});
	}

	public static String ask(JDialog dialog, String title) {
		TextFrame connexionFrame = new TextFrame(dialog, title);
		connexionFrame.setLocationRelativeTo(dialog);
		connexionFrame.setVisible(true);
		return txtSession.getText();
	}
}
