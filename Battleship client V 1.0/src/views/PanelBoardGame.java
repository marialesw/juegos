package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import control.Options;
import models.ShipOrientationInBoard;
import models.ShipType;

/**
 * 
 * @author Santigo Moreno, dario baron
 *
 */

public class PanelBoardGame extends JPanel{

	private static final long serialVersionUID = 1L;

	private ArrayList<JButton> cellButtons;
	private int countAircraft;
	private int countSubmarine;
	private int countVessel;
	private int countBoat;
	private int countMissileStation;
	private ShipType shipType;

	public PanelBoardGame(ActionListener actionListener) {
		cellButtons = new ArrayList<>();

		setLayout(new GridBagLayout());
		setBackground(Color.decode("#3D2813"));
		setBorder(BorderFactory.createEmptyBorder(1, 3, 1, 3));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridheight = 1;
		c.weighty = 0.1;

		for (int i = 0; i < 20; i++) {
			c.gridx = i;
		}
		for (int i = 0; i < 20; i++) {
			c.gridy = i;
			if(i!=0) {
			}
		}
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				c.gridx = i;
				c.gridy = j; 
				c.gridheight = 1;
				c.gridwidth = 1;
				JButton jbutton = new JButton();
				JPopupMenu popupMenu = new JPopupMenu();
				JMenuItem itemVertical = new JMenuItem("Vertical");
				JMenuItem itemHorizontal = new JMenuItem("Horizontal");
				popupMenu.add(itemHorizontal);
				popupMenu.add(itemVertical);
				MouseAdapter mouseAdapter = new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						popupMenu.show(jbutton, e.getX(), e.getY());
					}
				};
				jbutton.addMouseListener(mouseAdapter);
				jbutton.setName(i+":"+j);
				jbutton.setBorder(BorderFactory.createLineBorder(Color.decode("#15AFFF")));
				jbutton.setBackground(Color.decode("#BEFED4"));
				cellButtons.add(jbutton);
				add(jbutton,c);
				itemVertical.addActionListener(actionListener);
				itemVertical.setActionCommand(Options.ADD_VERTICAL.toString());
				itemVertical.setName(i+":"+j);
				itemHorizontal.addActionListener(actionListener);
				itemHorizontal.setActionCommand(Options.ADD_HORIZONTAL.toString());
				itemHorizontal.setName(i+":"+j);
			}
		}
	}

	public void setShipTye(ShipType shipType) {
		this.shipType = shipType;
	}
	
	public ShipType getShipType() {
		return shipType;
	}
	
	public int getCountAircraft() {
		return countAircraft;
	}
	
	public int getCountSubmarine() {
		return countSubmarine;
	}
	
	public int getCountVessel() {
		return countVessel;
	}
	
	public int getCountBoat() {
		return countBoat;
	}
	
	public int getCountMissileStation() {
		return countMissileStation;
	}

	public void addShipVertical(int x , int y,ShipOrientationInBoard shipOrientationInBoard) {
		try {
			if(shipType.equals(ShipType.AIRCRAFT_CARRIER) ) {
				if(countAircraft < 2) {
					addShipVerticalConfirmated(x, y,Color.decode(PanelTypeShip.COLOR_CARRIER),shipOrientationInBoard);
					countAircraft++;
				}
			}else if(shipType.equals(ShipType.SUBMARINE) ){
				if(countSubmarine < 3) {
					addShipVerticalConfirmated(x, y,Color.decode(PanelTypeShip.Color_SUBMARINE),shipOrientationInBoard);
					countSubmarine++;
				}
			}else if(shipType.equals(ShipType.VESSEL)){
				if(countVessel < 4) {
					addShipVerticalConfirmated(x, y,Color.decode(PanelTypeShip.COLOR_VASSEL),shipOrientationInBoard);
					countVessel++;
				}
			}else if(shipType.equals(ShipType.BOAT)){
				if(countBoat < 5) {
					addShipVerticalConfirmated(x, y,Color.decode(PanelTypeShip.COLOR_BOAT),shipOrientationInBoard);
					countBoat++;
				}else {
					JOptionPane.showMessageDialog(null, "Ya se ubicaron todas las lanchas");
				}
			}else if(shipType.equals(ShipType.MISSILE_STATION)){
				if(countMissileStation < 1) {
					addShipVerticalConfirmated(x, y, Color.decode(PanelTypeShip.COLOR_MISSILE_STATION),shipOrientationInBoard);
					countMissileStation++;
				}
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado barco");
		}
	}

	private void addShipVerticalConfirmated(int x, int y,Color color,ShipOrientationInBoard shipOrientationInBoard) {
		int width = shipType.getArea().getWidth();
		int height = shipType.getArea().getHeight();
		for (JButton jButton : cellButtons) {
			String[] ubicationInit = jButton.getName().split(":");
			if(jButton.getName().equals(String.valueOf(x)+":"+String.valueOf(y))) {
				jButton.setBackground(color);
				if(width>height) {
					int height2 = height;
					height = width;
					width = height2;
					if(shipOrientationInBoard.equals(ShipOrientationInBoard.VERTICAL_ABOVE)) {
						for (JButton jButton2 : cellButtons) {
							String[] ubicationBtn = jButton2.getName().split(":");
							if((Integer.valueOf(ubicationBtn[1])<Integer.valueOf(ubicationInit[1]) && Integer.valueOf(ubicationBtn[1])>=Integer.valueOf(ubicationInit[1])-(height-1))&&Integer.valueOf(ubicationBtn[0])==x) {
								jButton2.setBackground(color);
							}
						}
					}else if(shipOrientationInBoard.equals(ShipOrientationInBoard.VERTICAL_BELOW)) {
						for (JButton jButton2 : cellButtons) {
							String[] ubicationBtn = jButton2.getName().split(":");
							if((Integer.valueOf(ubicationBtn[1])<Integer.valueOf(ubicationInit[1]) && Integer.valueOf(ubicationBtn[1])<=Integer.valueOf(ubicationInit[1])-(height-1))&&Integer.valueOf(ubicationBtn[0])==x) {
								jButton2.setBackground(color);
							}
						}
					}
				}else {
					for (JButton jButton2 : cellButtons) {
						String[] ubicationBtn = jButton2.getName().split(":");
						if (shipOrientationInBoard.equals(ShipOrientationInBoard.VERTICAL_ABOVE)) {
							if((Integer.valueOf(ubicationBtn[1])<Integer.valueOf(ubicationInit[1]) && Integer.valueOf(ubicationBtn[1])>=Integer.valueOf(ubicationInit[1])-(height-1))&&Integer.valueOf(ubicationBtn[0])==x) {
								jButton2.setBackground(color);
							}
						}else if(shipOrientationInBoard.equals(ShipOrientationInBoard.VERTICAL_BELOW)) {
							if((Integer.valueOf(ubicationBtn[1])>Integer.valueOf(ubicationInit[1]) && Integer.valueOf(ubicationBtn[1])<=Integer.valueOf(ubicationInit[1])+(height-1))&&Integer.valueOf(ubicationBtn[0])==x) {
								jButton2.setBackground(color);
							}
						}
					}
				}
			}
		}
	}

	public void addShipHorizontal(int x , int y, ShipOrientationInBoard shipOrientationInBoard) {
		try {
			if(shipType.equals(ShipType.AIRCRAFT_CARRIER) ) {
				if(countAircraft < 2) {
					addShipHorizontalConfirmated(x, y, Color.decode(PanelTypeShip.COLOR_CARRIER),shipOrientationInBoard);
					countAircraft++;
				}
			}else if(shipType.equals(ShipType.SUBMARINE) ){
				if(countSubmarine < 3) {
					addShipHorizontalConfirmated(x, y,Color.decode(PanelTypeShip.Color_SUBMARINE),shipOrientationInBoard);
					countSubmarine++;
				}else {
					JOptionPane.showMessageDialog(null, "Ya estan ubicados todos los submarinos");
				}
			}else if(shipType.equals(ShipType.VESSEL)){
				if(countVessel < 4) {
					addShipHorizontalConfirmated(x, y,Color.decode(PanelTypeShip.COLOR_VASSEL),shipOrientationInBoard);
					countVessel++;
				}
			}else if(shipType.equals(ShipType.BOAT)){
				if(countBoat < 5) {
					addShipHorizontalConfirmated(x, y,Color.decode(PanelTypeShip.COLOR_BOAT),shipOrientationInBoard);
					countBoat++;
				}
			}else if(shipType.equals(ShipType.MISSILE_STATION)){
				if(countMissileStation < 1) {
					addShipHorizontalConfirmated(x, y,Color.decode(PanelTypeShip.COLOR_MISSILE_STATION),shipOrientationInBoard);
					countMissileStation++;
				}
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado barco");
		}
	}

	private void addShipHorizontalConfirmated(int x, int y,Color color,ShipOrientationInBoard shipOrientationInBoard) {
		int width = shipType.getArea().getWidth();
		int height = shipType.getArea().getHeight();
		for (JButton jButton : cellButtons) {
			String[] ubicationInit = jButton.getName().split(":");
			if(jButton.getName().equals(String.valueOf(x)+":"+String.valueOf(y))) {
				jButton.setBackground(color);
				if(height>width) {
					int width2 = width;
					width = height;
					height = width2;
					if(shipOrientationInBoard.equals(ShipOrientationInBoard.HORIZONTAL_LEFT)) {
						for (JButton jButton2 : cellButtons) {
							String[] ubicationBtn = jButton2.getName().split(":");
							if((Integer.valueOf(ubicationBtn[0])<Integer.valueOf(ubicationInit[0]) && Integer.valueOf(ubicationBtn[0])>=Integer.valueOf(ubicationInit[0])-(width-1))&&Integer.valueOf(ubicationBtn[1])==y) {
								jButton2.setBackground(color);
							}
						}
					}else if(shipOrientationInBoard.equals(ShipOrientationInBoard.HORIZONTAL_RIGHT)) {
						for (JButton jButton2 : cellButtons) {
							String[] ubicationBtn = jButton2.getName().split(":");
							if((Integer.valueOf(ubicationBtn[0])>Integer.valueOf(ubicationInit[0]) && Integer.valueOf(ubicationBtn[0])<=Integer.valueOf(ubicationInit[0])+(width-1))&&Integer.valueOf(ubicationBtn[1])==y) {
								jButton2.setBackground(color);
							}
						}
					}

				}else {
					for (JButton jButton2 : cellButtons) {
						String[] ubicationBtn = jButton2.getName().split(":");
						if((Integer.valueOf(ubicationBtn[0])>Integer.valueOf(ubicationInit[0]) && Integer.valueOf(ubicationBtn[0])<=Integer.valueOf(ubicationInit[0])+(width-1))&&Integer.valueOf(ubicationBtn[1])==y) {
							jButton2.setBackground(color);
						}
					}
				}
			}
		}
	}
}	
