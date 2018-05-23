package Models;

import java.util.ArrayList;

import Utilities.StringUtilities;

public class Sheet {

	ArrayList<Question> questions = new ArrayList<Question>();

	public Sheet() {
		
	}

	public Sheet(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public void add(Question question) {
		if (question == null || StringUtilities.stringIsNullOrEmpty(question.text))
			return;

		for (int i = 0; i < 4; i++)
			if (question.answers[i] == null || StringUtilities.stringIsNullOrEmpty(question.answers[i].text))
				return;

		questions.add(question);
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}
}
