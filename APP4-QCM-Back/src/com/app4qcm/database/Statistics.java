package com.app4qcm.database;

import java.util.HashMap;

public class Statistics {
	
	private Question question;
	
	private HashMap<String, boolean[]> responses;

	public Statistics() {
		responses = new HashMap<String, boolean[]>();
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public HashMap<String, boolean[]> getResponses() {
		return responses;
	}

	public void setResponses(HashMap<String, boolean[]> responses) {
		this.responses = responses;
	}
}
