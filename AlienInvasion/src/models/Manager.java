package models;

import java.util.ArrayList;
import java.util.Iterator;

public class Manager implements Runnable{

	private static final int TIME_SLEEP = 3000;
	private static final int NUMBER_ENEMIES_HARD = 7;
	private static final int NUMBER_ENEMIES_MEDIUM = 5;
	private static final int NUMBER_ENEMIES_EASY = 3;
	private static final int COUNT_SCORE = 5;
	private static final int SPACE_MAUSE = 25;
	private static final int SPACE = 6;
	private static final int RANGE = 50;
	private ArrayList<Enemy> enemyList;
	private int score;
	private Thread thrGeneratorEnemies;
	private int height;
	private int width;
	private boolean alive;
	private boolean pauseThreadGenerator;
	private boolean stopThreadGenerator;
	private int numberEnemies;
	private Levels level;

	public Manager(int height, int width, Levels level) {
		this.level = level;
		refreshLevel();
		alive = true;
		this.height = height;
		this.width = width;
		enemyList = new ArrayList<>();
		thrGeneratorEnemies = new Thread(this);
	}

	private void refreshLevel() {
		if (level == Levels.HARD) {
			numberEnemies = NUMBER_ENEMIES_HARD;
		}else if (level == Levels.MEDIUM) {
			numberEnemies = NUMBER_ENEMIES_MEDIUM;
		}else{
			numberEnemies = NUMBER_ENEMIES_EASY;
		}
	}

	public void startThread() {
		thrGeneratorEnemies.start();
	}

	public void addEnemy(int enemys, int height, int width){
		for (int i = 0; i < enemys; i++) {
			enemyList.add(new Enemy(height, width));
		}
	}

	public ArrayList<Enemy> getEnemyList() {
		return new ArrayList<>(enemyList);
	}

	public void shoot(double x_mause, double y_mause){
		double y = y_mause - SPACE_MAUSE;
		Iterator<Enemy> iteratorEnemies = enemyList.iterator();
		while (iteratorEnemies.hasNext()) {
			Enemy enemie = iteratorEnemies.next();
			if ((x_mause > enemie.getX() + SPACE && x_mause < (enemie.getX() + RANGE) - SPACE) 
					&& y > enemie.getY()  && y < (enemie.getY() + RANGE)-SPACE){
				enemie.die();
				score = (score + COUNT_SCORE);
			}
		}
	}

	public int getScore() {
		return score;
	}

	public void dieEnemies() {
		Iterator<Enemy> iteratorEnemies = enemyList.iterator();
		while (iteratorEnemies.hasNext()) {
			Enemy enemie = iteratorEnemies.next();
			enemie.die();
		}
	}

@Override
public void run() {
	while (!stopThreadGenerator) {
		addEnemy(numberEnemies, height, width);	
		synchronized (this) {
			while (pauseThreadGenerator) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(stopThreadGenerator) {
				break;
			}  
		}
		try {
			Thread.sleep(TIME_SLEEP);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

public synchronized void stopGenerator() {
	stopThreadGenerator = true;
	pauseThreadGenerator = false;
	notify();
}

public synchronized void pauseThread() {
	pauseThreadGenerator = true;
	stopThreadGenerator = false;
	notify();
}

public boolean isAlive() {
	return alive;
}

public Levels getLevel() {
	return level;
}

public void setAlive(boolean alive) {
	this.alive = alive;
}
}