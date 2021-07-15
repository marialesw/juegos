package runner;

import java.io.IOException;

import net.Client;

public class RunClient {

	public static void main(String[] args) {
		try {
			new Client();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
