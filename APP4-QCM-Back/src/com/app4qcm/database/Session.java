package com.app4qcm.database;

import java.util.ArrayList;

import com.app4qcm.database.Question;

public class Session {

	private String name;

	private ArrayList<Question> questions;

	public Session() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return La liste des questions
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * Modifie la liste des questions
	 * 
	 * @param questions
	 */
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public void add(Question item) {
		if (questions == null)
			questions = new ArrayList<Question>();

		int max = 0;
		for (Question question : questions)
			if (question.getId_q() > max)
				max = question.getId_q();		
		item.setId_q(max + 1);
		
		questions.add(item);
	}

	public void removeId(int id) {
		if (questions == null)
			questions = new ArrayList<Question>();

		int index = 0;
		for (Question question : questions) {
			if (question.getId_q() == id)
				break;
			index++;
		}

		if (index < questions.size())
			questions.remove(index);
	}
}
