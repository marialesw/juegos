package models;

import java.util.ArrayList;

import views.JFMainWindow;

public class Game{

	private static final int COUNT_SCORE = 5;
	private static final int SPACE_MAUSE = 25;
	private static final int SPACE = 6;
	private static final int RANGE = 50;
	private JFMainWindow jfMainWindow;
	private ArrayList<Oval> ovals;
	private int score;

	public Game(JFMainWindow jfMainWindow) {
		this.jfMainWindow = jfMainWindow;
		ovals = new ArrayList<>();
	}

	public void refreshNumberOvals(int numberOvals){
		for (int i = 0; i < numberOvals; i++) {
			ovals.add(new Oval(jfMainWindow.getjPanelGame().getWidth(), 
					jfMainWindow.getjPanelGame().getHeight()));
		}
	}

	public ArrayList<Oval> getOvals() {
		return ovals;
	}

	public void shoot(double x_mause, double y_mause){
		double y = y_mause - SPACE_MAUSE;
		for (Oval oval : ovals) {
			if ((x_mause > oval.getX() + SPACE && x_mause < (oval.getX() + RANGE) - SPACE) 
					&& y > oval.getY()  && y < (oval.getY() + RANGE)-SPACE){
				oval.die();
				score = (score + COUNT_SCORE);
		}
	}		
		
}

	public int getScore() {
		return score;
	}
}