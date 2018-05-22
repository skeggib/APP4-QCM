package com.app4qcm.database;

import java.util.ArrayList;

import com.app4qcm.database.Question;

public class Session {

	private int id_s;
	private String creator_name;
	private ArrayList<Question> quest_s;
	

	public Session() {
		this.id_s = 0;
		this.creator_name = "";
		quest_s=new ArrayList<Question>();
	}
	
	public Session(int id_s, String creator_name) {
		this.id_s = id_s;
		this.creator_name = creator_name;
		quest_s=new ArrayList<Question>();
	}
	
	//Recupere l'id de la session du professeur
	public int getId_s() {
		return id_s;
	}
	
	//Modifie l'id de la session du professeur
	public void setId_s(int id_s) {
		this.id_s = id_s;
	}
	
	//Renvoie le nom du professeur
	public String getCreator_name() {
		return creator_name;
	}
	
	//Modifie le nom du professeur
	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}
	
	//Retourne la liste de question
	public ArrayList<Question> getQuest_s() {
		return quest_s;
	}
	
	//Ajoute une nouvelle liste de questions (en ecrasant l'ancienne)
	public void setQuest_s(ArrayList<Question> quest_s) {
		this.quest_s = quest_s;
	}
	
	//Ajoute une question dans la liste
	public void ajout_Quest_s(Question nouv) {
		quest_s.add(nouv);
	}
	
	//Recupere une question en fonction de son index
	public Question recup_Quest_s(int ind) {
		return quest_s.get(ind);
	}
	
}
