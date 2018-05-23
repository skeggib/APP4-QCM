package Controls;

import java.awt.Component;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ScrollPanel extends JScrollPane {
	
	public ScrollPanel(Component component) {
		super(component);
		getVerticalScrollBar().setUnitIncrement(10);
	}
	
	public void scrollToBottom() {
		JScrollBar vertical = getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
	}
}
