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
	 * @param questions
	 */
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
}
