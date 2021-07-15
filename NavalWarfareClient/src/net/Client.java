package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import observer.ManagerObserver;

/**
 * 
 * @author Maria Lisbeth Latorre
 *
 */
public class Client implements Runnable{

	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private ManagerObserver managerObserver;
	private Thread threadClient;
	
	public Client() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 1234);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		threadClient = new Thread(this);
		threadClient.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(inputStream.readBoolean());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}