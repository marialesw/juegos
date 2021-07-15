package runner;

import java.io.IOException;

import net.Server;

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
