package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Options;
import models.Ship;
import models.ShipOrientationInBoard;
import models.ShipType;

public class PanelLocateShips extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private PanelBoardGame panelBoardGame;
	private PanelTypeShip panelTypeShip;
	
	public PanelLocateShips(ActionListener actionListener) {
		setLayout(new BorderLayout());
		
		JPanel header = new JPanel();
		header.setBackground(Color.decode("#FFB469"));
		header.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
		
		JLabel title = new JLabel("Ubique sus barcos");
		title.setForeground(Color.decode("#000000"));
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		header.add(title);
		
		add(header,BorderLayout.PAGE_START);
		
		panelTypeShip = new PanelTypeShip(actionListener);
		add(panelTypeShip, BorderLayout.LINE_START);
		
		panelBoardGame = new PanelBoardGame(actionListener);
		add(panelBoardGame, BorderLayout.CENTER);
		
		JPanel right = new JPanel();
		right.setBackground(Color.decode("#FFB469"));
		right.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));
		
		add(right,BorderLayout.LINE_END);
		
		
		JPanel footer = new JPanel();
		footer.setLayout(new FlowLayout());
		footer.setBackground(Color.decode("#FFB469"));
		footer.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
		
		JButton btnInitGame = new RoundedButton(30);
		btnInitGame.setText("Iniciar Juego");
		btnInitGame.setFocusable(false);
		btnInitGame.setBorderPainted(false);
		btnInitGame.setBackground(Color.decode("#3D2813"));
		btnInitGame.setForeground(Color.decode("#FFFFFF"));
		btnInitGame.setFont(new Font("Helvetica", Font.BOLD, 18));
		btnInitGame.addActionListener(actionListener);
		btnInitGame.setActionCommand(Options.START_GAME.name());
		footer.add(btnInitGame);
		
		add(footer,BorderLayout.PAGE_END);
		
	}
	
	public void repaintBoard() {
		remove(panelBoardGame);
		add(panelBoardGame, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public PanelBoardGame getPanelBoardGame() {
		return panelBoardGame;
	}
	public void addShip(Ship ship) {
		System.out.println(ship.getType().getArea().getWidth());
	}

	public void addHorizontal(int x, int y, ShipOrientationInBoard shipOrientationInBoard) {
		panelBoardGame.addShipHorizontal(x, y,shipOrientationInBoard);
	}
	public void addVertical(int x, int y, ShipOrientationInBoard shipOrientationInBoard) {
		panelBoardGame.addShipVertical(x, y,shipOrientationInBoard);
	}
	public void setShipType(ShipType shipType) {
		panelBoardGame.setShipTye(shipType);
	}
	
	public ShipType getShipType() {
		return panelBoardGame.getShipType();
	}
	
	public String getCarrier() {
		return panelTypeShip.getCarrier();
	}
	public String getBoatName() {
		return panelTypeShip.getBoatName();
	}
	
	public String getMissileStation() {
		return panelTypeShip.getMissileStation();
	}
	
	public String getVessel() {
		return panelTypeShip.getVessel();
	}
	
	public String getSubmarine() {
		return panelTypeShip.getSubmarine();
	}
	
	public int getCountAircraft() {
		return panelBoardGame.getCountAircraft();
	}
	
	public int getCountSubmarine() {
		return panelBoardGame.getCountSubmarine();
	}
	
	public int getCountVessel() {
		return panelBoardGame.getCountVessel();
	}
	
	public int getCountBoat() {
		return panelBoardGame.getCountBoat();
	}
	
	public int getCountMissileStation() {
		return panelBoardGame.getCountMissileStation();
	}
}