package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Options;
/**
 * 
 * @author Santiago Moreno, Dario baron
 *
 */
public class PanelTypeShip extends JPanel {
	public  static final String COLOR_CARRIER = "#8E8790";
	public static final String Color_SUBMARINE = "#850F00";
	public  static final String COLOR_VASSEL = "#007785";
	public static final String COLOR_BOAT = "#BFAC00";
	public static final String COLOR_MISSILE_STATION = "#118000";

	public static final long serialVersionUID = 1L;

	private JButton btnCarrier ;
	private JButton btnSubmarine;
	private JButton btnVessel;
	private JButton btnBoat;
	private JButton btnMissileStation;
	
	/**
	 * se encarga de agregar los botones correspondientes a cada tipo de barco
	 * @param action actionListener
	 */
	public PanelTypeShip(ActionListener action) {
		setLayout(new GridBagLayout());
		setBackground(Color.decode("#FFB469"));
		setPreferredSize(new Dimension(300, 100));
		setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 10));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridheight = 1;
		c.weighty = 0.1;
		
		for (int i = 0; i < 6; i++) {
			c.gridx = i;
			add(new JLabel(" "),c);
		}
		for (int i = 0; i < 14; i++) {
			c.gridy = i;
			add(new JLabel(" "),c);
		}
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 1;
		btnCarrier = new JButton("Porta-aviones");
		btnCarrier.setName("Carrier");
		btnCarrier.setFocusable(false);
		btnCarrier.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnCarrier.addActionListener(action);
		btnCarrier.setActionCommand(Options.GET_AIRCRAFT_CARRIER.toString());
		btnCarrier.setBackground(Color.decode(COLOR_CARRIER));
		btnCarrier.setForeground(Color.decode("#FFFFFF"));
		btnCarrier.setFont(new Font("Helvetica", Font.BOLD, 10));
		add(btnCarrier,c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 3;
		c.gridheight =1;
		c.weightx = 1;
		btnSubmarine = new JButton("Submarino");
		btnSubmarine.setName("Submarine");
		btnSubmarine.setFocusable(false);
		btnSubmarine.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnSubmarine.addActionListener(action);
		btnSubmarine.setActionCommand(Options.GET_SUBMARINE.toString());
		btnSubmarine.setBackground(Color.decode(Color_SUBMARINE));
		btnSubmarine.setForeground(Color.decode("#FFFFFF"));
		btnSubmarine.setFont(new Font("Helvetica", Font.BOLD, 10));
		add(btnSubmarine,c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 2;
		c.gridheight =1;
		c.weightx = 1;
		btnVessel = new JButton("Buque");
		btnVessel.setName("Vessel");
		btnVessel.setFocusable(false);
		btnVessel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnVessel.addActionListener(action);
		btnVessel.setActionCommand(Options.GET_VESSEL.toString());
		btnVessel.setBackground(Color.decode(COLOR_VASSEL));
		btnVessel.setForeground(Color.decode("#FFFFFF"));
		btnVessel.setFont(new Font("Helvetica", Font.BOLD, 10));
		add(btnVessel,c);
		
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		btnBoat = new JButton("Lancha");
		btnBoat.setName("Boat");
		btnBoat.setFocusable(false);
		btnBoat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnBoat.addActionListener(action);
		btnBoat.setActionCommand(Options.GET_BOAT.toString());
		btnBoat.setBackground(Color.decode(COLOR_BOAT));
		btnBoat.setForeground(Color.decode("#FFFFFF"));
		btnBoat.setFont(new Font("Helvetica", Font.BOLD, 10));
		add(btnBoat,c);
		
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.weightx = 1;
		btnMissileStation = new JButton("Estación");
		btnMissileStation.setFocusable(false);
		btnMissileStation.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnMissileStation.setName("MissileStation");
		btnMissileStation.addActionListener(action);
		btnMissileStation.setActionCommand(Options.MISSILE_STATION.toString());
		btnMissileStation.setBackground(Color.decode(COLOR_MISSILE_STATION));
		btnMissileStation.setForeground(Color.decode("#FFFFFF"));
		btnMissileStation.setFont(new Font("Helvetica", Font.BOLD, 10));
		add(btnMissileStation,c);
	}
	
	public String getBoatName() {
		return btnBoat.getName();
	}
	
	public String getMissileStation() {
		return btnMissileStation.getName();
	}
	public String getCarrier() {
		return btnCarrier.getName();
	}
	
	public String getVessel() {
		return btnVessel.getName();
	}
	
	public String getSubmarine() {
		return btnSubmarine.getName();
	}

}
