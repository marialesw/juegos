package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelScoreEnd extends JPanel{

	private static final long serialVersionUID = 1L;
	private Font fuente;
	private JLabel labelScore;

	public JPanelScoreEnd() {
		setBackground(Color.gray);
		initComponents();
	}

	private void initComponents() {
		fuente = new Font("Times New Roman", 1, 22);
		labelScore = new JLabel("SCORE: ");
		labelScore.setFont(fuente);
		this.add(labelScore, BorderLayout.CENTER);
	}
	
	public void setScore(double score){
		labelScore.setText("SCORE:  000" +String.valueOf((int)score));
	}
}
