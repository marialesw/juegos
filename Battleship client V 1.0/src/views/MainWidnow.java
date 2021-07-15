package views;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import models.ShipOrientationInBoard;
import models.ShipType;

public class MainWidnow extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private PanelLocateShips panelLocateShips;
	private PanelMatch panelMatch;
	private PanelInit initPanel;
	private JPanel panelActual;
	private ActionListener actionListener;
	
	public MainWidnow(ActionListener actionListener) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Battleship");
		setSize(1300,750);
		this.actionListener = actionListener;
		
		initComponents(actionListener);
		
		setVisible(true);
	}
	
	private void initComponents(ActionListener actionListener) {
		initPanel = new PanelInit(actionListener);
		panelLocateShips = new PanelLocateShips(actionListener);
		add(initPanel, BorderLayout.CENTER);
		panelActual = initPanel;
	}
	
	public void changePanel(JPanel panel) {
		getContentPane().remove(panelActual);
		panelActual = panel;
		add(panelActual, BorderLayout.CENTER);
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
	public void changeToInitPanel() {
		changePanel(initPanel);
	}
	
	public void changeToPanelLocateShips() {
		changePanel(panelLocateShips);
	}
	
	public void changeToPanelGame() {
		panelMatch = new PanelMatch(actionListener, panelLocateShips);
		changePanel(panelMatch);
	}
	
	public void setShiptType(ShipType shipType) {
		panelLocateShips.setShipType(shipType);
	}
	
	public ShipType getShipType() {
		return panelLocateShips.getShipType();
	}
	
	public void addHorizontal(int x,int y,ShipOrientationInBoard shipOrientationInBoard) {
		panelLocateShips.addHorizontal(x, y,shipOrientationInBoard);
	}
	
	public void addVertical(int x,int y,ShipOrientationInBoard shipOrientationInBoard) {
		panelLocateShips.addVertical(x, y,shipOrientationInBoard);
	}
	
	public String getCarrier() {
		return panelMatch.getCarrier();
	}
	public PanelMatch getBoardPanel() {
		return panelMatch;
	}
	public String getBoatName() {
		return panelMatch.getBoatName();
	}
	
	public String getMissileStation() {
		return panelMatch.getMissileStation();
	}
	
	public String getVessel() {
		return panelMatch.getVessel();
	}
	
	public String getSubmarine() {
		return panelMatch.getSubmarine();
	}
	
	public int getCountAircraft() {
		return panelLocateShips.getCountAircraft();
	}
	
	public int getCountSubmarine() {
		return panelLocateShips.getCountSubmarine();
	}
	
	public int getCountVessel() {
		return panelLocateShips.getCountVessel();
	}
	
	public int getCountBoat() {
		return panelLocateShips.getCountBoat();
	}
	
	public int getCountMissileStation() {
		return panelLocateShips.getCountMissileStation();
	}

	public static void showWarningMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}
