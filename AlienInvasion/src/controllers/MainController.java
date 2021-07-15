package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;
import models.Enemy;
import models.EstateEnemy;
import models.Levels;
import models.Manager;
import persistence.FileJson;
import views.Constants;
import views.JFMainWindow;

public class MainController implements MouseListener, Runnable, ActionListener, KeyListener{

	private static final int TIME_MOVE_EASY = 50;
	private static final int TIME_MOVE_HARD = 30;
	private static final int TIME_MOVE_MEDIUM = 40;
	private static final int TIME_DIE_ENEMY = 3000;
	private static final int TIME_REFRESH = 50;
	private static final int TIME_GAME = 60000;
	private Manager manager;
	private JFMainWindow jfMainWindow;
	private Timer timerGeneralGame;
	private Timer timerEndGame;
	private Timer timerDieEnemy;
	private Thread threadMove;
	private int timeGame;
	private int speedMove;
	private boolean stopThreadMove;
	private boolean pauseThreadMove;
	private boolean music;

	public MainController() {
		playSound("/sounds/Interplanetary.wav");
		jfMainWindow = new JFMainWindow(this, this, this);
		jfMainWindow.showGame(false);
	}

	private void initGame(Levels level) {
		if (timerGeneralGame != null) {
			timerGeneralGame = null;
		}
		controllerGeneralGame();
		jfMainWindow.showGame(true);
		manager = new Manager(jfMainWindow.getjPanelGame().getHeight(),
				jfMainWindow.getjPanelGame().getWidth(), level);
		verifyLevel();
		manager.startThread();
		controllerDieEnemy();
		threadMove = new Thread(this);
		controllerEndGame();
		threadMove.start();
		timeGame = TIME_GAME;
		music = true;
	}
	
	public void restartGame() {
	}

	private void verifyLevel() {
		if (manager.getLevel() == Levels.EASY) {
			speedMove = TIME_MOVE_EASY;
		}else if(manager.getLevel() == Levels.HARD) {
			speedMove = TIME_MOVE_HARD;
		}else if (manager.getLevel() == Levels.MEDIUM) {
			speedMove = TIME_MOVE_MEDIUM;
		}
	}

	private void controllerDieEnemy() {
		timerDieEnemy = new Timer(TIME_DIE_ENEMY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Enemy enemy : manager.getEnemyList()) {
					if (!enemy.isAlive()) {
						if (enemy.getState() == EstateEnemy.ALIEN_DEAD) {
							verifyStateAlien(enemy);
						}else if (enemy.getState() == EstateEnemy.EGG_DIE) {
							verifyStateEgg(enemy);
						}
					}
				}
			};
		});
		timerDieEnemy.start();	
	}

	protected void verifyStateAlien(Enemy enemy) {
		enemy.setState(EstateEnemy.DIE_FINAL);														
	}

	protected void verifyStateEgg(Enemy enemy) {
		enemy.setState(EstateEnemy.DIE_FINAL_EGG);
	}

	private void controllerEndGame() {
		timerEndGame = new Timer(TIME_GAME, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				endGame();
			};
		});
		timerEndGame.start();
	}

	private void controllerGeneralGame() {
		timerGeneralGame = 	new Timer(TIME_REFRESH, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jfMainWindow.setTimeGame(timeGame);
				jfMainWindow.setScore(manager.getScore());
				jfMainWindow.setEnemies(manager.getEnemyList());
				jfMainWindow.repaint();
			};
		});
		timerGeneralGame.start();
	}

	@Override
	public void mousePressed(MouseEvent event) {
		manager.shoot(event.getLocationOnScreen().getX(), event.getLocationOnScreen().getY());
	}

	private void endGame() {
		timerGeneralGame.stop();
		manager.dieEnemies();
		jfMainWindow.repaint();
		saveGame();
		jfMainWindow.getjPanelGame().addMouseListener(null);
		timerDieEnemy.stop();
		manager.setAlive(false);
		jfMainWindow.showScoreEnd(manager.getScore());
		jfMainWindow.showDialog();
	}
	
	private void saveGame() {
		try {
			FileJson.writeFileCity(manager.getEnemyList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (!stopThreadMove) {
			executeTask();
			synchronized (threadMove) {
				while (pauseThreadMove) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(stopThreadMove) {
					break;
				}
			}
			try {
				Thread.sleep(speedMove);
			} catch (Exception e) {
			}
		}
	}

	private void executeTask() {
		timeGame = timeGame - speedMove;
		ArrayList<Enemy> enemies = manager.getEnemyList();
		if (enemies != null) {
			for (Enemy enemy : enemies) {
				if (enemy.isAlive()) {
					enemy.move();
				}
				if (enemy.getState() == EstateEnemy.GAME_OVER) {
					endGame();
				}
			}
		}
	}

	public void playSound(final String path){
		new Thread(new Runnable() { 
			@Override
			public void run() {					
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
					clip.open(inputStream);
					clip.start();
				} catch (LineUnavailableException |UnsupportedAudioFileException | IOException e) {
					e.printStackTrace();
				}
				}
		}).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case Constants.BUTTON_PLAY:
			if (jfMainWindow.getSelectedLevel() != null) {
				initGame(jfMainWindow.getSelectedLevel());		
			}
			break;
		case Constants.RESTART_GAME:
			jfMainWindow.offVisibleDialog();
			jfMainWindow.showGame(false);
			break;
		default:
			break;
		}
	}

	public synchronized void stopThreadMove() {
		stopThreadMove = true;
		pauseThreadMove = false;
		notify();
	}

	public synchronized void pauseThreadMove() {
		pauseThreadMove = true;
		stopThreadMove = false;
		notify();
	}

	public synchronized void resumeThreadMove() {
		pauseThreadMove = false;
		notify();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) {
			manager.pauseThread();
			pauseThreadMove();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

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
	public void mouseClicked(MouseEvent e) {
	}
}
