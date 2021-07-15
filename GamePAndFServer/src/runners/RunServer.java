package runners;

import java.io.IOException;

import sockets.Server;

public class RunServer {

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
