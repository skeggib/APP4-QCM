package Models;

public class Question {

	public int numQuestion;
	public String text;
	public Answer[] answers = new Answer[4];
	
	public Question() {
		text = "";
		for(int i = 0; i < 4; i++)
			answers[i] = new Answer("", false);
	}
	
	public Question(String text, Answer a1, Answer a2, Answer a3, Answer a4) {
		this.text = text;
		this.answers[0] = a1;
		this.answers[1] = a2;
		this.answers[2] = a3;
		this.answers[3] = a4;
	}
}
