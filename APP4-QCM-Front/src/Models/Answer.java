package Models;

public final class Answer {

	public String text;
	public boolean checked;
	
	// as teacher, checked is the 'true' answer
	// as student, checked is his answer
	public Answer(String text, boolean checked) {
		this.text = text;
		this.checked = checked;
	}
}
