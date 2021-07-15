package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelScore extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelScore;
	private Font fuente;
	
	public JPanelScore() {
		setPreferredSize(new Dimension(1200, 50));
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		initComponents();
	}

	private void initComponents() {
		fuente = new Font("Times New Roman", 1, 24);
		labelScore = new JLabel("SCORE: ");
		labelScore.setFont(fuente);
		labelScore.setForeground(Color.WHITE);
		this.add(labelScore, BorderLayout.CENTER);
	}
	
	public void setScore(double score){
		labelScore.setText("SCORE:  000" +String.valueOf((int)score));
	}
}