package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import models.Constants;
import models.Oval;

public class JFMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanelGame jPanelGame;
	private JPanelScore jPanelScore;
	private JDialogView dialogView;
	private JPanelEast jPanelEast;

	public JFMainWindow(MouseListener listener) {
		setTitle(Constants.T_MAIN_WINDOW);
		Image icon = new ImageIcon(getClass().getResource("/imgs/logo.png")).getImage();
		setIconImage(icon);
		setLayout(new BorderLayout());
		addMouseListener(listener);
		setExtendedState(MAXIMIZED_BOTH);
		setSize(new Dimension(800, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
		setVisible(true);
	}

	private void initComponents() {
		dialogView = new JDialogView();
		jPanelEast = new JPanelEast();
		add(jPanelEast, BorderLayout.EAST);
		jPanelGame = new JPanelGame();
		add(jPanelGame, BorderLayout.CENTER);
		jPanelScore = new JPanelScore();
		add(jPanelScore, BorderLayout.SOUTH);
	}

	public JPanelGame getjPanelGame() {
		return jPanelGame;
	}

	public void refresh(ArrayList<Oval> ovals) {
		jPanelGame.refresh(ovals);
	}

	public void setScore(double score){
		jPanelScore.setScore(score);
	}

	public void showDialog(){
		dialogView.setVisible(true);
	}

	public void showScoreEnd(double score){
		dialogView.showScore(score);
	}
}
