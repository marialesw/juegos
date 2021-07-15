package models;

import net.ClientConnection;

public class Match {

	private ClientConnection clientOne;
	private ClientConnection clientTwo;
	
	public Match(ClientConnection clientOne, ClientConnection clientTwo) {
		System.out.println("se puede iniciar una nueva partida");
		this.clientOne = clientOne;
		this.clientTwo = clientTwo;
	}
}