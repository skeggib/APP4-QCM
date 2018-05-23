package com.app4qcm.logic.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.app4qcm.logic.Logic;
import com.app4qcm.networking.ClientConnection;

public class LogicTests {

	@Test
	public void verif_create_session() {
		Logic cli=new Logic();
		String strxml="create_session";
		cli.command(null,strxml);
	}
	
	@Test
	public void verif_create_session_xml() {
		Logic cli=new Logic();
		String strxml="create_session <com.app4qcm.database.Session></com.app4qcm.database.Session>";
		cli.command(null,strxml);
	}
	
	@Test
	public void send_question() {
		Logic cli=new Logic();
		String strxml="send_question 12 ";
		cli.command(null,strxml);
	}

}
