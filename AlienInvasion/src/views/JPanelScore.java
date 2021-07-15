package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelScore extends JPanel {

	private static final String TIME_000 = "TIME:  000";
	private static final String PRESS_SPACE_TO_PAUSE = "Press space to pause";
	private static final String BOMBA= "/imgs/bomba.png";
	private static final long serialVersionUID = 1L;
	private JLabel labelScore;
	private Font fuente;
	private JLabel labelTime;
	private JLabel labelSpace;
	private JLabel labelBomba;
	private ImageIcon image;
	private ImageIcon icon;

	public JPanelScore() {
		setPreferredSize(new Dimension(1200, 50));
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		initComponents();
	}

	private void initComponents() {
		fuente = new Font(Constants.TIMES_NEW_ROMAN, 1, 24);
		labelScore = new JLabel("SCORE: ");
		labelScore.setFont(fuente);
		labelScore.setForeground(Color.WHITE);
		this.add(labelScore, BorderLayout.WEST);
		
		labelBomba = new JLabel();
		labelBomba.setBounds(1100, 0, 50, 50);
		image = new ImageIcon(getClass().getResource(BOMBA));
		icon = new ImageIcon(image.getImage().getScaledInstance(labelBomba.getWidth(),
				labelBomba.getHeight(), Image.SCALE_DEFAULT));
		labelBomba.setIcon(icon);
		this.add(labelBomba, BorderLayout.EAST);
		
		labelTime = new JLabel(TIME_000);
		labelTime.setFont(fuente);
		labelTime.setForeground(Color.WHITE);
		this.add(labelTime, BorderLayout.EAST);
		
		labelSpace = new JLabel(PRESS_SPACE_TO_PAUSE);
		labelSpace.setFont(fuente);
		labelSpace.setForeground(Color.WHITE);
		//this.add(labelSpace, BorderLayout.CENTER);
	}

	public void setScore(double score){
		labelScore.setText("SCORE:  000" +String.valueOf((int)score));
	}

	public void setTime(int time){
		labelTime.setText("TIME: " +getTimeReal(String.valueOf(time - 405)));
	}

	private String getTimeReal(String miliseconds) {
		String result = "";
		if (miliseconds.length() == 5) {
			result =  "00: " + miliseconds.charAt(0) + miliseconds.charAt(1) +
					" : " + miliseconds.charAt(2) + miliseconds.charAt(3) ;
		}else if (miliseconds.length() == 4) {
			result =  "00: " + "0" + miliseconds.charAt(0) + " : " + miliseconds.charAt(1) + miliseconds.charAt(2) ;
		}else if (miliseconds.length() == 3) {
			result =  "00: " + "00" + " : " + miliseconds.charAt(0) + miliseconds.charAt(1);
		}else if (miliseconds.length() == 2) {
			result =  "00: " + "00" + " : 0" + miliseconds.charAt(0);
		}
		return result;
		
	}
}