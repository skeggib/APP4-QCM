

import java.sql.SQLException;

import com.app4qcm.database.Context;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class App4QcmBack {

	public static void main(String[] args) {
		
		Context context;

		try {
			System.out.println("Setting up the context...");
			context = new Context("db.sqlite");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
	}

}
