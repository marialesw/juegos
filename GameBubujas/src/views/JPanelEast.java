package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class JPanelEast extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField speed;
	private GridBagConstraints gbc;
	private Font font;
	private JLabel jLabelTitle;

	public JPanelEast() {
		setPreferredSize(new Dimension(200, 200));
		setLayout(new GridBagLayout());
		setBackground(Color.BLACK);
		initComponents();
		setVisible(true);
	}

	private void initComponents() {
		gbc = new GridBagConstraints();
		generateBasicGridX(gbc);
		generateBasicGridY(gbc);
		gbc.insets = new Insets(5, 0, 5, 0);
		
		TitledBorder tb = BorderFactory.createTitledBorder("SPEED");
		font = new Font("Berlin Sans FB Demi", 0, 16);
		tb.setTitleFont(font);
		tb.setTitleColor(Color.white);
		setBorder(tb);
		
		jLabelTitle = new JLabel("Speed");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		jLabelTitle.setPreferredSize(new Dimension(200, 40));
		font = new Font("Arial", 1, 18);
		jLabelTitle.setForeground(Color.yellow);
		jLabelTitle.setFont(font);
		this.add(jLabelTitle, gbc);
		
		speed = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		speed.setPreferredSize(new Dimension(10, 10));
		speed.setFont(font);
		this.add(speed, gbc);
	}
	
	private void generateBasicGridX(GridBagConstraints gbc){
		gbc.weightx = 1;
		gbc.gridheight = 1;
		for (int i = 0; i < 4; i++) {
			gbc.gridx = i;
			add(new JLabel(" " + i ), gbc);
		}
	}

	private void generateBasicGridY(GridBagConstraints gbc){
		gbc.weighty = 1;
		gbc.gridheight = 1;
		for (int i = 0; i < 3; i++) {
			gbc.gridy = i;
			add(new JLabel("" +i), gbc);
		}
	}
}
