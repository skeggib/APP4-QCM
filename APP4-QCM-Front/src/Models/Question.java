package Models;

public class Question {

	public int num_Quest;
	public String text;
	public Answer[] answers = new Answer[4];
	
	public Question() {
		num_Quest = 1;
		text = "";
		for(int i = 0; i < 4; i++)
			answers[i] = new Answer("", false);
	}
	
	public Question(int num_Quest, String text, Answer a1, Answer a2, Answer a3, Answer a4) {
		this.text = text;
		this.answers[0] = a1;
		this.answers[1] = a2;
		this.answers[2] = a3;
		this.answers[3] = a4;
	}
}
