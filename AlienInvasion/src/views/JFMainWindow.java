package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import models.Enemy;
import models.Levels;

public class JFMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanelMainGame jPanelGame;
	public int heightPanelGame;
	public int widthPanelGame;
	private JPanelScore jpanelScore;
	private JDialogView jdialog;
	private ActionListener listener;
	private JPanelInit jPanelInit;

	public JFMainWindow(MouseListener mouseListener, ActionListener eventListener, KeyListener keyListener) {
		this.listener = eventListener;
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(Constants.T_MAIN_WINDOW);
		Image icon = new ImageIcon(getClass().getResource(Constants.LOGO_GAME)).getImage();
		setIconImage(icon);
		setSize(new Dimension(800, 500));
		addMouseListener(mouseListener);
		addKeyListener(keyListener);
		setLayout(new BorderLayout());
		setVisible(true);
	}

	public void showGame(boolean state) {
		initComponents(state);
		revalidate();
		repaint();
	}

	private void initComponents(boolean game) {
		if (game) {
			this.remove(jPanelInit);
			jdialog = new JDialogView(listener);
			jPanelGame = new JPanelMainGame();
			add(jPanelGame, BorderLayout.CENTER);
			heightPanelGame = jPanelGame.getHeight();
			widthPanelGame = jPanelGame.getWidth();
			jpanelScore = new JPanelScore();
			add(jpanelScore, BorderLayout.SOUTH);
		}else {
			if (jPanelGame != null && jpanelScore != null && jdialog != null) {
				remove(jPanelGame);
				remove(jpanelScore);
				remove(jdialog);
			}
			jPanelInit = new JPanelInit(this.getHeight(), this.getWidth(), listener);
			this.add(jPanelInit, BorderLayout.CENTER);
		}
	}
	
	public void setEnemies(ArrayList<Enemy> enemies){
		jPanelGame.setEnemies(enemies);
	}

	public int getHeighte() {
		return heightPanelGame;
	}

	public int getWidthe() {
		return widthPanelGame;
	}

	public JPanelMainGame getjPanelGame() {
		return jPanelGame;
	}

	public void setScore(int score) {
		jpanelScore.setScore(score);;
	}

	public void showScoreEnd(int score) {
		jdialog.showScore(score);
	}
	
	public void offVisibleDialog() {
		System.out.println("SE HA DFH");
		jdialog.setVisible(false);
	}
	
	public void setTimeGame(int timeGame) {
		jpanelScore.setTime(timeGame);
	}
	
	public Levels getSelectedLevel() {
		return jPanelInit.getjComboLevels();
	}

	public void showDialog() {
		jdialog.setVisible(true);
	}
}