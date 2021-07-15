package controllers;

import models.Game;
import views.JFMainWindow;

public class ThreadGame implements Runnable {

	private Thread gameThread;
	private Game game;
	private JFMainWindow jfMainWindow;

	public ThreadGame(Game game, JFMainWindow jfMainWindow) {
		this.game = game;
		this.jfMainWindow = jfMainWindow;
		gameThread = new Thread(this);
	}

	@Override
	public void run() {
		while (true) {
			game.refreshNumberOvals(3);
			jfMainWindow.refresh(game.getOvals());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopThread(){
	}
	
	public void initThreadCreateOvals() {
		gameThread.start();
	}

}
