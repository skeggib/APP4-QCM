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
	
	public int getId_q() {
		return id_q;
	}

	public void setId_q(int id_q) {
		this.id_q = id_q;
	}

	public String getTxt_quest() {
		return txt_quest;
	}

	public void setTxt_quest(String txt_quest) {
		this.txt_quest = txt_quest;
	}

	public String getRep1() {
		return rep1;
	}

	public void setRep1(String rep1) {
		this.rep1 = rep1;
	}

	public String getRep2() {
		return rep2;
	}

	public void setRep2(String rep2) {
		this.rep2 = rep2;
	}

	public String getRep3() {
		return rep3;
	}

	public void setRep3(String rep3) {
		this.rep3 = rep3;
	}

	public String getRep4() {
		return rep4;
	}

	public void setRep4(String rep4) {
		this.rep4 = rep4;
	}
	
}
