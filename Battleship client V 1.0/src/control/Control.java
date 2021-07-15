package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JMenuItem;

import models.Location;
import models.Ship;
import models.ShipOrientationInBoard;
import models.ShipType;
import network.Client;
import observerPattern.IObserver;
import observerPattern.ObserverManager;
import protocols.RequestClient;
import views.MainWidnow;

public class Control implements ActionListener, IObserver{
	private MainWidnow mainWidnow;
	private Client client;
	private DataOutputStream outputClient;
	private ObserverManager observerManager;

	private String[] data;

	public Control() {
		observerManager = new ObserverManager(this);
		try {
			client = new Client(observerManager);
			outputClient = client.getOutputStream();
		} catch (IOException e) {
			System.out.println("line 34 client EL SERVIDOR NO ESTA EN LINEA LO SENTIMOS..." + e.getMessage());
		}
		mainWidnow = new MainWidnow(this);
		data = new String[2];
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Options.valueOf(e.getActionCommand())) {
		case GET_AIRCRAFT_CARRIER:
			mainWidnow.setShiptType(ShipType.AIRCRAFT_CARRIER);
			break;
		case GET_SUBMARINE:
			mainWidnow.setShiptType(ShipType.SUBMARINE);
			break;
		case GET_VESSEL:
			mainWidnow.setShiptType(ShipType.VESSEL);
			break;
		case GET_BOAT:
			mainWidnow.setShiptType(ShipType.BOAT);			
			break;
		case MISSILE_STATION:
			mainWidnow.setShiptType(ShipType.MISSILE_STATION);
			break;
		case ADD_VERTICAL:
			String nameV = ((JMenuItem)e.getSource()).getName();
			data = nameV.split(":");
			Location shipLocationV = new Location(Integer.valueOf(data[0]), Integer.valueOf(data[1]));
			ShipType shipTypeV = mainWidnow.getShipType();
			verifySendShip(shipLocationV, shipTypeV, RequestClient.ADD_SHIP_VERTICAL);
			break;
		case ADD_HORIZONTAL:
			String nameH = ((JMenuItem)e.getSource()).getName();
			data = nameH.split(":");
			Location shipLocationH = new Location(Integer.valueOf(data[0]), Integer.valueOf(data[1]));
			ShipType shipTypeH = mainWidnow.getShipType();
			verifySendShip(shipLocationH, shipTypeH, RequestClient.ADD_SHIP_HORIZONTAL);
			break;
		case START:
			notifyStartGame();
			break;
		case START_GAME:
			notifyStartMatch();
			break;
		case INSTRUCTIONS:
			break;
		case EXIT:
			System.exit(0);
			break;
		case ADD_SHIP_HORIZONTAL:
			break;
		case ADD_SHIP_VERTICAL:
			break;
		case SHIP_SELECTED:
			break;
		default:
			break;
		}

	}

	private void notifyStartGame() {
		try {
			outputClient.writeUTF(RequestClient.START.toString());
		} catch (IOException e1) {
			System.err.println("no se puede iniciar juego");
		}
	}

	private void notifyStartMatch() {
		try {
			outputClient.writeUTF(RequestClient.START_GAME.toString());
		} catch (IOException e1) {
			System.err.println("no se puede iniciar partida");
		}
	}

	private void verifySendShip(Location shipLocation, ShipType shipType, RequestClient orientation) {
		try {
			switch (ShipType.valueOf(shipType.name())) {
			case AIRCRAFT_CARRIER:
				if (mainWidnow.getCountAircraft() != 2) {
					outputClient.writeUTF(orientation.name());
					outputClient.writeUTF(persistence.JSONPersistence.writeShipInJSON(new Ship(shipLocation, shipType)));						
				} else {
					MainWidnow.showWarningMessage("Ya se ubicaron todos los porta-aviones");
				}
				break;
			case SUBMARINE:
				if (mainWidnow.getCountSubmarine() != 3) {
					outputClient.writeUTF(orientation.name());
					outputClient.writeUTF(persistence.JSONPersistence.writeShipInJSON(new Ship(shipLocation, shipType)));						
				} else {
					MainWidnow.showWarningMessage("Ya se ubicaron todos los submarinos");
				}
				break;
			case VESSEL:
				if (mainWidnow.getCountVessel() != 4) {
					outputClient.writeUTF(orientation.name());
					outputClient.writeUTF(persistence.JSONPersistence.writeShipInJSON(new Ship(shipLocation, shipType)));						
				} else {
					MainWidnow.showWarningMessage("Ya se ubicaron todos los buques");
				}
				break;
			case BOAT:
				if (mainWidnow.getCountBoat() != 5) {
					outputClient.writeUTF(orientation.name());
					outputClient.writeUTF(persistence.JSONPersistence.writeShipInJSON(new Ship(shipLocation, shipType)));						
				} else {
					MainWidnow.showWarningMessage("Ya se ubicaron todas las lanchas");
				}
				break;
			case MISSILE_STATION:
				if (mainWidnow.getCountMissileStation() != 1) {
					outputClient.writeUTF(orientation.name());
					outputClient.writeUTF(persistence.JSONPersistence.writeShipInJSON(new Ship(shipLocation, shipType)));
				} else {
					MainWidnow.showWarningMessage("Ya se ubico la estación de misiles");
				}
				break;
			}
		} catch (IOException e1) {
			System.err.println("Error - No pudo enviar barco");
		}
	}

	@Override
	public void showPanelLocateShips() {
		mainWidnow.changeToPanelLocateShips();
	}

	@Override
	public void showShipInCorrectOrientationHorizontal(ShipOrientationInBoard orientation) {
		mainWidnow.addHorizontal(Integer.valueOf(data[0]),Integer.valueOf(data[1]), orientation);		
	}

	@Override
	public void showShipInCorrectOrientationVertical(ShipOrientationInBoard orientation) {
		mainWidnow.addVertical(Integer.valueOf(data[0]),Integer.valueOf(data[1]), orientation);		
	}

	@Override
	public void showBoardPanel() {
		mainWidnow.changeToPanelGame();		
	}

	@Override
	public void showWaitingPanel() {
//		mainWidnow.changeToPanelWaiting();		
	}
}