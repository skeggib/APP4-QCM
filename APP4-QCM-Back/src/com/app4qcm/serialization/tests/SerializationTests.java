package com.app4qcm.serialization.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.app4qcm.database.Question;
import com.app4qcm.serialization.XML_Tools;

public class SerializationTests {

	@Test
	public void test_decode_txt_quest() throws FileNotFoundException, IOException {
		Question ques=new Question();
		ques.setTxt_quest("ca va ?");
		XML_Tools.encodeToFile(ques, "test.xml");
		Question result;
		result=(Question) XML_Tools.decodeFromFile("test.xml");
		assertEquals("ca va ?",result.getTxt_quest());
	}
	
	@Test
	public void test_decode_reponse() throws FileNotFoundException, IOException {
		Question ques=new Question();
		ques.setRep1("oui");
		ques.setRep2("non");
		ques.setRep3("pe");
		ques.setRep4("presque");
		XML_Tools.encodeToFile(ques, "test.xml");
		Question result;
		result=(Question) XML_Tools.decodeFromFile("test.xml");
		assertEquals("oui",result.getRep1());
		assertEquals("non",result.getRep2());
		assertEquals("pe",result.getRep3());
		assertEquals("presque",result.getRep4());
	}
	
	@Test
	public void test_decode_correct() throws FileNotFoundException, IOException {
		Question ques=new Question();
		ques.setCorrect1(true);
		ques.setCorrect2(false);
		ques.setCorrect3(false);
		ques.setCorrect4(true);
		XML_Tools.encodeToFile(ques, "test.xml");
		Question result;
		result=(Question) XML_Tools.decodeFromFile("test.xml");
		assertEquals(true,result.getCorrect1());
		assertEquals(false,result.getCorrect2());
		assertEquals(false,result.getCorrect3());
		assertEquals(true,result.getCorrect4());
	}
	
	@Test
	public void test_decode_id() throws FileNotFoundException, IOException {
		Question ques=new Question();
		ques.setId_q(1);
		XML_Tools.encodeToFile(ques, "test.xml");
		Question result;
		result=(Question) XML_Tools.decodeFromFile("test.xml");
		assertEquals(1,result.getId_q());
	}

}
