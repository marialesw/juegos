package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import models.ShipOrientationInBoard;
import observerPattern.ObserverManager;
import protocols.Response;
import views.MainWidnow;

/**
 * 
 * @author Maria Lisbeth Latorre
 *
 */
public class Client implements Runnable {

	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private ObserverManager observerManager;

	public Client(ObserverManager observerManager) throws UnknownHostException, IOException {
		this.observerManager = observerManager;
		//		socket = new Socket("localhost", 1234);
		socket = new Socket("192.168.1.59", 1234);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				answer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void answer() throws IOException {
		String response = inputStream.readUTF();
		System.out.println(response + " client line 48");
		switch (Response.valueOf(response)) {
		case WAIT_GAME:
			MainWidnow.showWarningMessage("<html>Aun no hay rival<br></br>¡Espere!</html>");
			break;
		case START_GAME:
			observerManager.showPanelLocateShips();
			break;
		case HORIZONTAL_LEFT:
			observerManager.showShipInCorrectOrientationHorizontal(ShipOrientationInBoard.HORIZONTAL_LEFT);
			break;
		case HORIZONTAL_RIGHT:
			observerManager.showShipInCorrectOrientationHorizontal(ShipOrientationInBoard.HORIZONTAL_RIGHT);
			break;
		case VERTICAL_ABOVE:
			observerManager.showShipInCorrectOrientationVertical(ShipOrientationInBoard.VERTICAL_ABOVE);
			break;
		case VERTICAL_BELOW:
			observerManager.showShipInCorrectOrientationVertical(ShipOrientationInBoard.VERTICAL_BELOW);
			break;
		case CENTER:
			observerManager.showShipInCorrectOrientationVertical(ShipOrientationInBoard.VERTICAL_BELOW);
			break;
		case NONE:
			MainWidnow.showWarningMessage("No se puede agregar");
			break;
		case ERROR:
			MainWidnow.showWarningMessage("<html>Posición ocupada<br></br>No se puede agregar</html>");
			break;
		case START_MATCH:
			observerManager.showBoardPanel();			
			break;
		case WAITING_MATCH:
//			observerManager.showWaitingPanel();
			MainWidnow.showWarningMessage("<html>El tablero de su rival esta incompleto<br></br>Espere a que él agregue los demas barcos</html>");
			break;
		case WARNING_MATCH:
			MainWidnow.showWarningMessage("<html>Su tablero esta incompleto<br></br>Agregue los demas barcos</html>");
			break;
		}
	}

	public DataOutputStream getOutputStream() {
		return outputStream;
	}
}