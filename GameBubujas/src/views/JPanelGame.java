package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import models.Constants;
import models.Oval;

public class JPanelGame extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Oval> listOvals;
	
	private URL url = getClass().getResource("/imgs/fondo.jpg");
    Image imageFondo = new ImageIcon(url).getImage();
	
	public JPanelGame() {
		setBackground(Color.DARK_GRAY);
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.drawImage(imageFondo, 0, 0, getWidth(), getHeight(), this);
		graphics.setColor(Color.white);
		if (listOvals != null) {
			for (int i = 0; i < listOvals.size(); i++) {
				if (!listOvals.get(i).isAlive()) {
					graphics.setColor(Color.RED);
				}else{
					graphics.setColor(Color.white);
				}
				graphics.fillOval(listOvals.get(i).getX(), listOvals.get(i).getY(), 
						Constants.DIMENSION_OVAL, Constants.DIMENSION_OVAL);
			}	
		}
	}

	public void refresh(ArrayList<Oval> listOvals){
		this.listOvals = listOvals;
	}
}