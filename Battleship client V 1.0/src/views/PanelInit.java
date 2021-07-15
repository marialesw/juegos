package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Options;

public class PanelInit extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public PanelInit(ActionListener actionListener) {
		setBackground(Color.decode("#FFB469"));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridheight = 1;
		c.weighty = 0.1;
		
		for (int i = 0; i < 20; i++) {
			c.gridx = i;
			add(new JLabel(" "),c);
		}
		for (int i = 0; i < 20; i++) {
			c.gridy = i;
			add(new JLabel(" "),c);
		}
		
		c.gridx=8;
		c.gridy=7;
		c.gridwidth = 3;
		c.gridheight =1;
		JButton btnInitGame = new RoundedButton(50);
		btnInitGame.setText("Iniciar");
		btnPreferences(btnInitGame);
		btnInitGame.addActionListener(actionListener);
		btnInitGame.setActionCommand(Options.START.name());
		add(btnInitGame,c);
		
		c.gridx=8;
		c.gridy=10;
		c.gridwidth = 3;
		c.gridheight =1;
		JButton btnInstructions = new RoundedButton(50);
		btnInstructions.setText("Instrucciones");
		btnPreferences(btnInstructions);
		btnInstructions.addActionListener(actionListener);
		btnInstructions.setActionCommand(Options.INSTRUCTIONS.name());
		add(btnInstructions,c);
	}
	
	private void btnPreferences(JButton button) {
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setBackground(Color.decode("#3D2813"));
		button.setForeground(Color.decode("#FFFFFF"));
		button.setFont(new Font("Helvetica", Font.BOLD, 18));
	}
}