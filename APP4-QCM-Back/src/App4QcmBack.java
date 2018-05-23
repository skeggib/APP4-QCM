import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.app4qcm.logic.Logic;
import com.app4qcm.networking.ClientConnection;

public class App4QcmBack {

	public static void main(String[] args) {
		
		Logic logic = new Logic();

		final ServerSocket service;
		try {
			service = new ServerSocket(6789);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					service.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		while (true) {
			try {
				new Thread(new ClientConnection(service.accept(), logic)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
