package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Options;
import models.ShipOrientationInBoard;
import models.ShipType;

public class PanelMatch extends JPanel{
	private static final long serialVersionUID = 1L;
	private PanelLocateShips panelLocateShips;
	private PanelRival panelRival;
	
	public PanelMatch(ActionListener actionListener, PanelLocateShips panelLocateShips) {
		this.panelLocateShips = panelLocateShips;
		
		setBackground(Color.decode("#FFB469"));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridheight = 1;
		c.weighty = 0.1;
		
		for (int i = 0; i < 20; i++) {
			c.gridx = i;
			add(new JLabel(" "),c);
		}
		for (int i = 0; i < 20; i++) {
			c.gridy = i;
			add(new JLabel(" "),c);
		}
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		c.gridheight = 2;
		JLabel lives = new JLabel("Vidas: 3");
		labelPreferences(lives);
		labelPreferences(lives);
		add(lives, c);
		
		c.gridx = 5;
		c.gridy = 1;
		c.gridwidth = 3;
		c.gridheight = 2;
		JButton relocateStation = new RoundedButton(70);
		relocateStation.setText("Reubicar estacion");
		btnPreferences(relocateStation);
		add(relocateStation, c);
		
		c.gridx = 12;
		c.gridy = 1;
		c.gridwidth = 3;
		c.gridheight = 2;
		JButton useRadar = new RoundedButton(70);
		useRadar.setText("Usar radar");
		btnPreferences(useRadar);
		add(useRadar, c);
		
		c.gridx = 16;
		c.gridy = 1;
		c.gridwidth = 3;
		c.gridheight = 2;
		JButton exit = new RoundedButton(70);
		exit.setText("Salir");
		btnPreferences(exit);
		exit.addActionListener(actionListener);
		exit.setActionCommand(Options.EXIT.toString());
		add(exit, c);
	
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 7;
		c.gridheight = 2;
		JLabel yourBoard = new JLabel("Tu tablero", JLabel.CENTER);
		labelPreferences(yourBoard);
		add(yourBoard, c);
		
		c.gridx = 12;
		c.gridy = 4;
		c.gridwidth = 7;
		c.gridheight = 2;
		JLabel rivalBoard = new JLabel("Tablero rival", JLabel.CENTER);
		labelPreferences(rivalBoard);
		add(rivalBoard, c);
		
		c.gridx=0;
		c.gridy=6;
		c.gridwidth = 9;
		c.gridheight = 14;
		add(panelLocateShips.getPanelBoardGame(), c);
		
		c.gridx=11;
		c.gridy=6;
		c.gridwidth = 9;
		c.gridheight =14;
		panelRival = new PanelRival(actionListener);
		add(panelRival, c);	
	}
	
	private void btnPreferences(JButton button) {
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setBackground(Color.decode("#3D2813"));
		button.setForeground(Color.decode("#FFFFFF"));
		button.setFont(new Font("Helvetica", Font.BOLD, 18));
	}
	
	private void labelPreferences(JLabel label) {
		label.setForeground(Color.decode("#3D2813"));
		label.setFont(new Font("Helvetica", Font.BOLD, 18));
	}
	
	public PanelBoardGame gtplayer1() {
		return panelLocateShips.getPanelBoardGame();
	}
	
	public void setShipType(ShipType shipType) {
		panelLocateShips.setShipType(shipType);
	}
	
	public String getBoatName() {
		return panelLocateShips.getBoatName();
	}
	
	public void addHorizontal(int x , int y,ShipOrientationInBoard shipOrientationInBoard) {
		panelLocateShips.addHorizontal(x, y,shipOrientationInBoard);
	}
	public void addVertical(int x , int y,ShipOrientationInBoard shipOrientationInBoard) {
		panelLocateShips.addVertical(x, y,shipOrientationInBoard);
	}
	public String getCarrier() {
		return panelLocateShips.getCarrier();
	}
	
	public String getMissileStation() {
		return panelLocateShips.getMissileStation();
	}
	
	public String getVessel() {
		return panelLocateShips.getVessel();
	}
	
	public String getSubmarine() {
		return panelLocateShips.getSubmarine();
	}
}
