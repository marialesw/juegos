package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelScoreEnd extends JPanel{

	private static final String SCORE = "SCORE: ";
	private static final long serialVersionUID = 1L;
	private static final String URL_IMAGE_INIT = "/imgs/gameOver.png";
	private Font fuente;
	private JLabel labelScore;
	private JLabel labelImage;
	private ImageIcon image;
	private ImageIcon icon;
	private GridBagConstraints gbc;
	private JButton jButtonRestartGame;

	public JPanelScoreEnd(int width, int height, ActionListener listener) {
		setBackground(Color.gray);
		setLayout(new GridBagLayout());
		initComponents(width, height, listener);
	}

	private void initComponents(int width, int height, ActionListener listener) {
		gbc = new GridBagConstraints();
		generateBasicGridX(gbc);
		generateBasicGridY(gbc);
		gbc.insets = new Insets(5, 0, 5, 0);
		
		fuente = new Font("Times New Roman", 1, 22);
		labelScore = new JLabel(SCORE);
		labelScore.setFont(fuente);
		labelScore.setForeground(Color.white);
		gbc.gridx = 9;
		gbc.gridy = 8;
		this.add(labelScore, gbc);
		
		labelImage = new JLabel();
		labelImage.setBounds(0, 0, width, height);
		image = new ImageIcon(getClass().getResource(URL_IMAGE_INIT));
		icon = new ImageIcon(image.getImage().getScaledInstance(labelImage.getWidth(),labelImage.getHeight(), Image.SCALE_DEFAULT));
		labelImage.setIcon(icon);
		
		jButtonRestartGame = new JButton(Constants.RESTART_GAME);
		fuente = new Font(Constants.FONT_BRADLEY, 1 , 40);
		jButtonRestartGame.setFont(fuente);
		jButtonRestartGame.setForeground(new Color(255,0,0));
		jButtonRestartGame.setBackground(Color.black);
		jButtonRestartGame.setBorder(null);
		jButtonRestartGame.setActionCommand(Constants.RESTART_GAME);
		jButtonRestartGame.addActionListener(listener);
		gbc.gridx = 9;
		gbc.gridy = 10;
		this.add(jButtonRestartGame, gbc);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.drawImage(icon.getImage(), 0, 0, labelImage.getWidth(), labelImage.getHeight() , this);
		setOpaque(false);
		super.paint(g);
	}
	
	public void setScore(double score){
		labelScore.setText("SCORE:  000" +String.valueOf((int)score));
	}
	
	private void generateBasicGridX(GridBagConstraints gbc){
		gbc.weightx = 1;
		gbc.gridheight = 1;
		for (int i = 0; i < 12; i++) {
			gbc.gridx = i;
			add(new JLabel(" " ), gbc);
		}
	}

	private void generateBasicGridY(GridBagConstraints gbc){
		gbc.weighty = 1;
		gbc.gridheight = 1;
		for (int i = 0; i < 12; i++) {
			gbc.gridy = i;
			add(new JLabel(""), gbc);
		}
	}
}
