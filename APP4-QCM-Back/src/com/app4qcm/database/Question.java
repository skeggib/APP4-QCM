package com.app4qcm.database;

public class Question {

	private int id_q;

	private String txt_quest;

	private String rep1;

	private String rep2;

	private String rep3;

	private String rep4;

	private boolean correct1;

	private boolean correct2;

	private boolean correct3;

	private boolean correct4;

	public Question() {
	}

	public Question(String txt_quest, String rep1, String rep2, String rep3, String rep4, boolean correct1,
			boolean correct2, boolean correct3, boolean correct4) {
		this.txt_quest = txt_quest;
		this.rep1 = rep1;
		this.rep2 = rep2;
		this.rep3 = rep3;
		this.rep4 = rep4;
		this.correct1 = correct1;
		this.correct2 = correct2;
		this.correct3 = correct3;
		this.correct4 = correct4;
	}

	public Question(String txt_quest, String rep1, boolean correct1, String rep2, boolean correct2, String rep3,
			boolean correct3, String rep4, boolean correct4) {
		this.txt_quest = txt_quest;
		this.rep1 = rep1;
		this.rep2 = rep2;
		this.rep3 = rep3;
		this.rep4 = rep4;
		this.correct1 = correct1;
		this.correct2 = correct2;
		this.correct3 = correct3;
		this.correct4 = correct4;
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
	 * @param rep4
	 */
	public void setRep4(String rep4) {
		this.rep4 = rep4;
	}

	/**
	 * 
	 * @return validitee reponse 1
	 */
	public boolean getCorrect1() {
		return correct1;
	}

	/**
	 * Modifie la validitee de la reponse 1
	 * 
	 * @param correct1
	 */
	public void setCorrect1(boolean correct1) {
		this.correct1 = correct1;
	}

	/**
	 * 
	 * @return validitee reponse 2
	 */
	public boolean getCorrect2() {
		return correct2;
	}

	/**
	 * Modifie la validite de la reponse 2
	 * 
	 * @param correct2
	 */
	public void setCorrect2(boolean correct2) {
		this.correct2 = correct2;
	}

	/**
	 * 
	 * @return validitee reponse 3
	 */
	public boolean getCorrect3() {
		return correct3;
	}

	/**
	 * Modifie la validite de la reponse 3
	 * 
	 * @param correct3
	 */
	public void setCorrect3(boolean correct3) {
		this.correct3 = correct3;
	}

	/**
	 * 
	 * @return validitee reponse 4
	 */
	public boolean getCorrect4() {
		return correct4;
	}

	/**
	 * Modifie la validitee de la reponse 4
	 * 
	 * @param correct4
	 */
	public void setCorrect4(boolean correct4) {
		this.correct4 = correct4;
	}

	public String[] getAnswers() {
		return new String[] { rep1, rep2, rep3, rep4 };
	}

}
