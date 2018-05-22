package com.app4qcm.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "question")
public class Question {

	@DatabaseField(id = true)
	private int id_q;
	
	@DatabaseField
	private String txt_quest;
	
	@DatabaseField
	private String rep1;
	
	@DatabaseField
	private String rep2;
	
	@DatabaseField
	private String rep3;
	
	@DatabaseField
	private String rep4;

	@DatabaseField
	private int correct1;
	
	@DatabaseField
	private int correct2;
	
	@DatabaseField
	private int correct3;	
	
	@DatabaseField
	private int correct4;

	@DatabaseField(foreign=true,foreignAutoRefresh=true)
    private Session session;

	public Question(int id_q, String txt_quest, String rep1, String rep2, String rep3, String rep4) {
		this.id_q = id_q;
		this.txt_quest = txt_quest;
		this.rep1 = rep1;
		this.rep2 = rep2;
		this.rep3 = rep3;
		this.rep4 = rep4;
	}

	public Question() {
		this.id_q = 0;
		this.txt_quest = "";
		this.rep1 = "";
		this.rep2 = "";
		this.rep3 = "";
		this.rep4 = "";
	}
	
	/**
	 * 
	 * @return identifiant question 
	 */
	public int getId_q() {
		return id_q;
	}

	/**
	 * Modifie l'identifiant de la question
	 * @param id_q
	 */
	public void setId_q(int id_q) {
		this.id_q = id_q;
	}

	/**
	 * 
	 * @return texte de la question
	 */
	public String getTxt_quest() {
		return txt_quest;
	}

	/**
	 * Modifie le texte de la question
	 * @param txt_quest
	 */
	public void setTxt_quest(String txt_quest) {
		this.txt_quest = txt_quest;
	}
	
	/**
	 * 
	 * @return premiere reponse de la question
	 */
	public String getRep1() {
		return rep1;
	}

	/**
	 * Modifie texte de la premiere reponse de la question
	 * @param rep1
	 */
	public void setRep1(String rep1) {
		this.rep1 = rep1;
	}
	
	/**
	 * 
	 * @return deuxieme reponse de la question
	 */
	public String getRep2() {
		return rep2;
	}

	/**
	 * Modifie texte de la deuxieme reponse de la question
	 * @param rep2
	 */
	public void setRep2(String rep2) {
		this.rep2 = rep2;
	}

	/**
	 * 
	 * @return troisieme reponse de la question
	 */
	public String getRep3() {
		return rep3;
	}

	/**
	 * Modifie texte de la troisieme reponse de la question
	 * @param rep3
	 */
	public void setRep3(String rep3) {
		this.rep3 = rep3;
	}

	/**
	 * 
	 * @return quatrieme reponse de la question
	 */
	public String getRep4() {
		return rep4;
	}

	/**
	 * Modifie texte de la quatrieme reponse de la question
	 * @param rep4
	 */
	public void setRep4(String rep4) {
		this.rep4 = rep4;
	}
		
	/**
	 * 
	 * @return validitee reponse 1
	 */
	public int getCorrect1() {
		return correct1;
	}
	
	/**
	 * Modifie la validitee de la reponse 1
	 * @param correct1
	 */
	public void setCorrect1(int correct1) {
		this.correct1 = correct1;
	}

	/**
	 * 
	 * @return validitee reponse 2
	 */
	public int getCorrect2() {
		return correct2;
	}

	/**
	 * Modifie la validite de la reponse 2
	 * @param correct2
	 */
	public void setCorrect2(int correct2) {
		this.correct2 = correct2;
	}

	/**
	 * 
	 * @return validitee reponse 3
	 */
	public int getCorrect3() {
		return correct3;
	}

	/**
	 * Modifie la validite de la reponse 3
	 * @param correct3
	 */
	public void setCorrect3(int correct3) {
		this.correct3 = correct3;
	}

	/**
	 * 
	 * @return validitee reponse 4
	 */
	public int getCorrect4() {
		return correct4;
	}

	/**
	 * Modifie la validitee de la reponse 4
	 * @param correct4
	 */
	public void setCorrect4(int correct4) {
		this.correct4 = correct4;
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}
