package Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;

public class TextField extends JTextField implements FocusListener {

	String originalText;

	public TextField(String text) {
		originalText = text;
		this.setText(text);

		addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if (isEditable())
			if (getText().equals(originalText))
				setText("");
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if (isEditable())
			if (getText().equals(""))
				setText(originalText);
	}

	@Override
	public void setEditable(boolean editable) {
		super.setEditable(editable);
		if (!editable)
			setText(originalText);
	}
}
