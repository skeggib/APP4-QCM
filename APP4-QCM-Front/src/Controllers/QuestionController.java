package Controllers;

import java.io.IOException;

import com.app4qcm.database.Question;
import com.app4qcm.networking.InvalidQuestionNumber;
import com.app4qcm.networking.NoQuestionAvailable;
import com.app4qcm.networking.NotConnected;
import com.app4qcm.networking.UnrecognizedResponse;

public final class QuestionController extends AbstractController {

	public static void send(int questionId) throws IOException, NotConnected, InvalidQuestionNumber, UnrecognizedResponse {
		getServer().sendQuestion(questionId);
	}
	
	public static Question get() throws IOException, NotConnected, UnrecognizedResponse, NoQuestionAvailable {
		return getServer().getQuestion();
	}
}
