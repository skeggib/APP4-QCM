package Controllers;

import java.io.IOException;

import com.app4qcm.database.Question;
import com.app4qcm.networking.InvalidParameter;
import com.app4qcm.networking.InvalidQuestionNumber;
import com.app4qcm.networking.NoQuestionAvailable;
import com.app4qcm.networking.NotConnected;
import com.app4qcm.networking.UnrecognizedResponse;

public final class QuestionController extends AbstractController {

	public static void send(int questionId)
			throws IOException, NotConnected, InvalidQuestionNumber, UnrecognizedResponse {
		getServer().sendQuestion(questionId);
	}

	public static void answer(Question question)
			throws IOException, NotConnected, UnrecognizedResponse, InvalidParameter, NoQuestionAvailable {
		getServer().sendResponse(question.getCorrect1(), question.getCorrect2(), question.getCorrect3(),
				question.getCorrect4());
	}

	public static Question get() throws IOException, NotConnected, UnrecognizedResponse, NoQuestionAvailable {
		return getServer().getQuestion();
	}

	public static Question getEmpty() throws IOException, NotConnected, UnrecognizedResponse, NoQuestionAvailable {
		Question result = get();
		result.setCorrect1(false);
		result.setCorrect2(false);
		result.setCorrect3(false);
		result.setCorrect4(false);
		return result;
	}
}
