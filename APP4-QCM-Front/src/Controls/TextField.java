package Controls;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class TextField extends JTextField implements FocusListener {
	private static final long serialVersionUID = -228157581277173049L;
	
	String originalText;

	public TextField(String text) {
		originalText = text;
		this.setText(text);

		addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if (isEditable())
			if (getText().equals(originalText))
				setText("");
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if (isEditable())
			if (getText().equals(""))
				setText(originalText);
	}

	@Override
	public void setEditable(boolean b) {
		super.setEditable(b);
		if (!b)
			setText(originalText);
	}
}
