package Controllers;

import java.io.IOException;

import com.app4qcm.database.Statistics;
import com.app4qcm.networking.InvalidQuestionNumber;
import com.app4qcm.networking.NotConnected;
import com.app4qcm.networking.UnrecognizedResponse;

public final class StatsController extends AbstractController {

	public static Statistics get(int numQuestion) throws IOException, NotConnected, UnrecognizedResponse, InvalidQuestionNumber {
		return getServer().getStats(numQuestion);
	}
}
