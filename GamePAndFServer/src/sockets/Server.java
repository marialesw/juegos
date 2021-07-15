package sockets;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.json.simple.DeserializationException;

import models.User;
import persistence.PerFileJSON;

public class Server implements Runnable {
	
	private Thread threadConnections;
	private boolean serverOn;
	private ServerSocket serverSocket;
	private ArrayList<ServerThread> connections;
	private ArrayList<User> usersList;

	public Server() throws IOException {
		serverSocket = new ServerSocket(1235);
		usersList = new ArrayList<>();
		System.out.println("servidor esperando en : " + InetAddress.getLocalHost().getHostAddress());		
		//////////////////////////////////////////
		connections = new ArrayList<>();
		serverOn = true;
		threadConnections = new Thread(this);
		threadConnections.start();
	}

	@Override
	public void run() {
		while (serverOn) {
			Socket socket;
			try {
				socket = serverSocket.accept();
				String host = socket.getInetAddress().getHostAddress();
				ServerThread serverThread = new ServerThread(socket);
				System.out.println("se conecto: " + host);
				connections.add(serverThread);	
				usersList.add(new User(host, LocalDate.now(), LocalTime.now(), serverThread.getNumberGuess(), serverThread.getCountIntentos()));
				printList();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void printList() {
		try {
			PerFileJSON.writeFileUser(usersList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}