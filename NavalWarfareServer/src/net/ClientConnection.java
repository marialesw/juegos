package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import observer.ManagerObserver;

/**
 * 
 * @author Maria Lisbeth Latorre
 *
 */
public class ClientConnection implements Runnable {

	private DataOutputStream ouputServer;
	private DataInputStream inputServer;
	private Thread threadRequest;
	private ManagerObserver managerObserver;
	private boolean isConnecting;
	
	/**
	 * 
	 * @param socket
	 * @param managerObserver 
	 * @throws IOException
	 */
	
	public ClientConnection(Socket socket, ManagerObserver managerObserver) throws IOException {
		this.managerObserver = managerObserver;
		Logger.getGlobal().log(Level.INFO,"SE HA CONECTADO UN NUEVO USUARIO");
		isConnecting = true;
		inputServer = new DataInputStream(socket.getInputStream());
		ouputServer = new DataOutputStream(socket.getOutputStream());
		threadRequest = new Thread(this);
		threadRequest.start();
	}

	@Override
	public void run() {
		while(true) {
			try {
				System.out.println(inputServer.readUTF());
			} catch (IOException e) {
				isConnecting = false;
				managerObserver.deleteConnection();
				//System.out.println("SE DESCONECTO");
				//e.printStackTrace();
			}
		}
	}
	
	public void sendBoolean(boolean message) {
		try {
			ouputServer.writeBoolean(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) {
		try {
			ouputServer.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isConnecting() {
		return isConnecting;
	}
}