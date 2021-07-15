package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.Timer;
import models.Game;
import models.Oval;
import views.JFMainWindow;

public class MainController implements ActionListener, MouseListener, Runnable {

	private JFMainWindow jfMainWindow;
	private Game game;
	private Timer controlerThread;
	private Thread threadMove;
	private long speed;
	private boolean alive;
	private ThreadGame threadGame;
	private int number;
	private int numberTwo;
	private int numberThree;

	public MainController() {
		alive = true;
		speed = 10 ;
		jfMainWindow = new JFMainWindow(this);
		game = new Game(jfMainWindow);
		threadMove = new Thread(this);
		initThreadGame();
		initThreadMove();
		threadMove.start();
		threadGame = new ThreadGame(game, jfMainWindow);
		threadGame.initThreadCreateOvals();
	}

	private void initThreadMove() {
		controlerThread = 
				new Timer(2000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						number = 1 + (int) (Math.random() * 8);
						numberTwo =  1 + (int) (Math.random() * 5);
						numberThree =  1 + (int) (Math.random() * 3);
					};
				});
		controlerThread.start();
		
	}

	private void initThreadGame() {
		controlerThread = 
				new Timer(60000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						alive = false;
						controlerThread.stop();
						System.out.println("puntaje " + game.getScore());
						jfMainWindow.showScoreEnd(game.getScore());
						jfMainWindow.showDialog();
					};
				});
		controlerThread.start();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		game.shoot(event.getLocationOnScreen().getX(), event.getLocationOnScreen().getY());
		jfMainWindow.setScore(game.getScore());
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void run() {
		while (alive) {
			if (game.getOvals() != null) {
				ArrayList<Oval> listOvals = game.getOvals();
				for (Oval oval : listOvals) {
					if (oval.isAlive()) {
						oval.move(number, numberTwo, numberThree);
						oval.refreshState();	
					}
				}
					try {
						Thread.sleep(speed);
					} catch (Exception e) {
					}	
			}
			jfMainWindow.repaint();
		}
	}
}