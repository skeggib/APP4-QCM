package com.app4qcm.database;

import com.app4qcm.database.Question;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "session")
public class Session {
	
	@DatabaseField(id = true)
	private String name;
	
	@ForeignCollectionField
	private ForeignCollection<Question> questions;

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
	public ForeignCollection<Question> getQuestions() {
		return questions;
	}
	
	/**
	 * Modifie la liste des questions
	 * @param questions
	 */
	public void setQuestions(ForeignCollection<Question> questions) {
		this.questions = questions;
	}
}
