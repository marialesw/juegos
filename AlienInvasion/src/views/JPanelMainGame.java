package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import models.Enemy;
import models.EstateEnemy;

public class JPanelMainGame extends JPanel{

	private static final String SRC_IMGS_ARMA_PNG = "src/imgs/arma.png";
	private static final String URL_IMAGE_ALIEN_DIE = "/imgs/alienDead.png";
	private static final String URL_IMAGE_ALIEN_2 = "/imgs/alien2.png";
	private static final String URL_IMAGE_ALIEN_1 = "/imgs/alien1.png";
	private static final String URL_IMAGE_EGG_DIE = "/imgs/eggDie.png";
	private static final String URL_IMAGE_EGG_CRACKED = "/imgs/eggCracked.png";
	private static final String URL_IMAGE_EGG = "/imgs/huevo.png";
	private static final String URL_IMAGE_FONDO = "/imgs/fondo1.png";
	private static final int SISE_EGG_DIE = 52;
	private static final int SIZE_ALIEN = 60;
	private static final long serialVersionUID = 1L;
	private ArrayList<Enemy> enemies;
	private Image imageFondo;
	private Image imageEgg;
	private Image imageEggCracked;
	private Image imgEggDie;
	private Image imgAlien1;
	private Image imgAlien2;
	private Image imgAlienDie;
	public static double height;
	public static double width;

	public JPanelMainGame() {
		changeCursor();
		setBackground(Color.black);
		setPreferredSize(new Dimension(200, 600));
		imageFondo = new ImageIcon(getResource(URL_IMAGE_FONDO)).getImage();
		imageEgg = new ImageIcon(getResource(URL_IMAGE_EGG)).getImage();
		imageEggCracked = new ImageIcon(getResource(URL_IMAGE_EGG_CRACKED)).getImage();
		imgEggDie = new ImageIcon(getResource(URL_IMAGE_EGG_DIE)).getImage();
		imgAlien1 = new ImageIcon(getResource(URL_IMAGE_ALIEN_1)).getImage();
		imgAlien2 = new ImageIcon(getResource(URL_IMAGE_ALIEN_2)).getImage();
		imgAlienDie = new ImageIcon(getResource(URL_IMAGE_ALIEN_DIE)).getImage();
	}

	private void changeCursor() {
		Image im = Toolkit.getDefaultToolkit().createImage(SRC_IMGS_ARMA_PNG);
		Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(10,10),"WILL");
		this.setCursor(cur);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.drawImage(imageFondo, 0, 0, getWidth(), getHeight(), this);
		height = this.getHeight();
		width = this.getWidth();
		graphics.setColor(Color.BLACK);
		if (enemies != null) {
			for (Enemy enemy : enemies) {
				if (enemy.isAlive()) {
					paintAlienLive(graphics,enemy);
				}else {
					paintAlienDie(graphics,enemy);
				}	
			}
		}
	}

	private void paintAlienDie(Graphics graphics, Enemy enemy) {
		if (enemy.getState() == EstateEnemy.EGG_DIE) {
			graphics.drawImage(imgEggDie, enemy.getX(), enemy.getY(),
					SISE_EGG_DIE, SISE_EGG_DIE, this);		
		}else if (enemy.getState() == EstateEnemy.DIE_FINAL_EGG) {
			graphics.drawImage(null, enemy.getX(), enemy.getY(),
					SISE_EGG_DIE, SISE_EGG_DIE, this);	
		}else if (enemy.getState() == EstateEnemy.ALIEN_DEAD) {
			graphics.drawImage(imgAlienDie, enemy.getX(), enemy.getY(),
					SIZE_ALIEN, SIZE_ALIEN, this);	
		}else if (enemy.getState() == EstateEnemy.DIE_FINAL) {
			graphics.drawImage(null, enemy.getX(), enemy.getY(),
					SIZE_ALIEN, SIZE_ALIEN, this);	
		}
	}

	private void paintAlienLive(Graphics graphics, Enemy enemy) {
		if (enemy.getState() == EstateEnemy.EGG) {
			graphics.drawImage(imageEgg, enemy.getX(), enemy.getY(),
					Constants.DIMENSION_OVAL, Constants.DIMENSION_OVAL, this);
		}else if (enemy.getState() == EstateEnemy.CRACKED_EGG) {
			graphics.drawImage(imageEggCracked, enemy.getX(), enemy.getY(),
					Constants.DIMENSION_OVAL, Constants.DIMENSION_OVAL, this);
		}else if(enemy.getState() == EstateEnemy.ALIEN){
			if (enemy.getStateMove() == EstateEnemy.ALIEN_2) {
				graphics.drawImage(imgAlien2, enemy.getX(), enemy.getY(),
						SIZE_ALIEN, SIZE_ALIEN, this);
			}else {
				graphics.drawImage(imgAlien1, enemy.getX(), enemy.getY(),
						SIZE_ALIEN, SIZE_ALIEN, this);
			}
		}	
	}

	private URL getResource(String image) {
		URL url = getClass().getResource(image);
		return url;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;	
		this.repaint();
	}
}
