package net;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Match;
import models.User;
import observer.IObserver;
import observer.ManagerObserver;

/**
 * 
 * @author Maria Lisbeth Latorre
 *
 */

public class Server implements Runnable, IObserver {

	private ServerSocket serverSocket;
	private ArrayList<Match> matchList;
	private ArrayList<ClientConnection> listConnections;
	private Thread threadConnectios;
	private boolean serverOn;
	private ManagerObserver managerObserver;
	private ClientConnection clientOne;
	private ClientConnection clientTwo;

	/**
	 * Este constructor permite, inicializar cada una de las variables, hilos y listas.
	 * @throws IOException
	 */

	public Server() throws IOException {
		serverSocket = new ServerSocket(1234);
		managerObserver = new ManagerObserver(this);
		matchList = new ArrayList<>();
		listConnections = new ArrayList<>();
		Logger.getGlobal().log(Level.INFO, "servidor esperando en el puerto: " +
				InetAddress.getLocalHost().getHostAddress());
		threadConnectios = new Thread(this);
		serverOn = true;
		threadConnectios.start();
	}

	@Override
	public void run() {
		while (serverOn) {
			Socket socket;
			try {
				socket = serverSocket.accept();
				String host = socket.getInetAddress().getHostAddress();
				Logger.getGlobal().log(Level.INFO, "se conecto: " + host);
				verifyUsers(new ClientConnection(socket, managerObserver));
				//usersList.add(new ClientConnection(socket, managerObserver));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Este metodo permitirá asignar los jugadores a una partida,
	 * Además agregara una nueva Conexión a la lista de conexiones 
	 * @param ingresa como parametro una nueva conexión. 
	 */

	private void verifyUsers(ClientConnection clientConnection) {
		listConnections.add(clientConnection);
		if (clientOne == null) {
			clientOne = clientConnection;
			clientOne.sendBoolean(false);
		}else {
			clientTwo = clientConnection;
			matchList.add(new Match(clientOne, clientTwo));
			clientOne.sendBoolean(true);
			clientTwo.sendBoolean(true);
			clientOne = null;
			clientTwo = null;
		}
	}

	/**
	 * Este metodo permitirá enviar un mensaje a cada uno de los clientes que estan conectados
	 * @param message
	 * @return void
	 */
	private void notifyClient(String message, ClientConnection connection) {
		connection.sendMessage(message);
	}

	@Override
	public void prueba(String pr) {
		//aca se puede acceder a los metodos del servidor
		//por ejemplo si quiero enviar ese string a todos los clientes
	}

	/**
	 * Este metodo permite eliminar una conexión, cuando el usuario se desconecte
	 */
	@Override
	public void deleteConnection() {
		for (ClientConnection clientConnection : listConnections) {
			if (!clientConnection.isConnecting()) {
				listConnections.remove(clientConnection);
				Logger.getGlobal().log(Level.INFO, "Conexion Borrada");
				break;
			}
		}
	}

	private void deleteUser() {

	}
}