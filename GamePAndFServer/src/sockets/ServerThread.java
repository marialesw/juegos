package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import models.MyNumber;

public class ServerThread implements Runnable{

	public static final int DIGITS = 4;
	private MyNumber myNumber;
	private Socket socket;
	private DataOutputStream outputServer;
	private DataInputStream inputServer;
	private Thread threadRequest;
	private int countIntentos;
	private int numberGuess;

	public ServerThread(Socket socket) throws IOException {
		this.socket = socket;
		myNumber = new MyNumber();
		myNumber.generate(DIGITS);
		System.out.println(myNumber.getNumber());
		numberGuess = myNumber.getNumber();
		inputServer = new DataInputStream(socket.getInputStream());
		outputServer = new DataOutputStream(socket.getOutputStream());
		threadRequest = new Thread(this);
		countIntentos = 0;
		threadRequest.start();
	}

	@Override
	public void run() {
		while(true) {
			try {
				if (inputServer.readUTF().equals(ConstantsConections.REINIT)) {
					myNumber.generate(DIGITS);
					System.out.println(myNumber.getNumber());
					countIntentos = 0;
				}else {
					int num = inputServer.readInt();
					countIntentos++;
					System.out.println("Recibio el " + num);
					validateNumber(num, DIGITS);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void validateNumber(int number, int digits) throws IOException {
		if (myNumber.fixes(number) == DIGITS) {
			outputServer.writeUTF(ConstantsConections.WIN);
			outputServer.writeInt(countIntentos);
		}else {
			if (myNumber.isNumberOfDigit(number, digits)) {
				if (!myNumber.isDijitDuplicate(number, digits)) {
					outputServer.writeUTF(ConstantsConections.OK);
					outputServer.writeInt(myNumber.fixes(number));
					outputServer.writeInt(myNumber.pikes(number) - myNumber.fixes(number));
				}else {
					outputServer.writeUTF(ConstantsConections.ERROR);
					outputServer.writeUTF(ConstantsConections.DUPLICATE);
				}
			}else {
				outputServer.writeUTF(ConstantsConections.ERROR);
				outputServer.writeUTF(ConstantsConections.ERROR_DIGITS + digits + " digitos");
			}
		}
	}
	
	public int getNumberGuess() {
		return numberGuess;
	}
	
	public int getCountIntentos() {
		return countIntentos;
	}
}
