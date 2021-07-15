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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Levels;

public class JPanelInit extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String URL_IMAGE_INIT = "/imgs/imageInit.png";
	private GridBagConstraints gbc;
	private JLabel labelImage;
	private ImageIcon image;
	private ImageIcon icon;
	private JLabel labelLevel;
	private Font fuente;
	private JButton jbuttonPlay;
	private JComboBox<Levels> jComboLevels;

	public JPanelInit(int height, int width, ActionListener event) {	
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		generateBasicGridX(gbc);
		generateBasicGridY(gbc);
		gbc.insets = new Insets(5, 0, 5, 0);
		
		fuente = new Font(Constants.FONT_BRADLEY, 2 , 40);
		labelImage = new JLabel();
		labelImage.setBounds(0, 0, width, height);
		image = new ImageIcon(getClass().getResource(URL_IMAGE_INIT));
		icon = new ImageIcon(image.getImage().getScaledInstance(labelImage.getWidth(),labelImage.getHeight(), Image.SCALE_DEFAULT));
		labelImage.setIcon(icon);

		labelLevel = new JLabel("LEVEL");
		labelLevel.setFont(fuente);
		labelLevel.setForeground(new Color(153,255,51));		
		gbc.gridx = 1;
		gbc.gridy = 5;
		this.add(labelLevel, gbc);

		fuente = new Font(Constants.FONT_BRADLEY, 0 , 30);

		jComboLevels = new JComboBox<>(Levels.values());
		jComboLevels.setFont(fuente);
		jComboLevels.setForeground(Color.red);
		jComboLevels.setBackground(new Color(52,52,52));
//		jComboLevels.setBorder();
		gbc.gridx = 2;
		gbc.gridy = 5;
		this.add(jComboLevels, gbc);
				
		jbuttonPlay = new JButton(Constants.BUTTON_PLAY);
		fuente = new Font(Constants.FONT_BRADLEY, 1 , 50);
		jbuttonPlay.setFont(fuente);
		jbuttonPlay.setForeground(new Color(255,0,0));
		jbuttonPlay.setBackground(Color.black);
		jbuttonPlay.setBorder(null);
		gbc.gridx = 2;
		gbc.gridy = 8;
		jbuttonPlay.setActionCommand(Constants.BUTTON_PLAY);
		jbuttonPlay.addActionListener(event);
		this.add(jbuttonPlay, gbc);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.drawImage(icon.getImage(), 0, 0, labelImage.getWidth(), labelImage.getHeight() , this);
		setOpaque(false);
		super.paint(g);
	}
	
	public Levels getjComboLevels() {
		return (Levels) jComboLevels.getSelectedItem();
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