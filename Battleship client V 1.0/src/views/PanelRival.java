package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelRival extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JButton> cellButtons;
	
	public PanelRival(ActionListener actionListener) {
		cellButtons = new ArrayList<>();
		setLayout(new GridBagLayout());
		setBackground(Color.decode("#3D2813"));
		setBorder(BorderFactory.createEmptyBorder(1, 3, 1, 3));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridheight = 1;
		c.weighty = 0.1;
		
		for (int i = 0; i < 20; i++) {
			c.gridx = i;
//			add(new JLabel(" "+(char)(i+65)),c);
		}
		for (int i = 0; i < 20; i++) {
			c.gridy = i;
//			if(i!=0) {
//				add(new JLabel(" "+i),c);
//			}
		}
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				c.gridx = i;
				c.gridy = j; 
				c.gridheight = 1;
				c.gridwidth = 1;
				JButton jbutton = new JButton();
				jbutton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						jbutton.setBackground(Color.BLUE);
					}
				});
				jbutton.setName(i+":"+j);
				jbutton.setBorder(BorderFactory.createLineBorder(Color.decode("#15AFFF")));
				jbutton.setBackground(Color.decode("#BEFED4"));
				cellButtons.add(jbutton);
				add(jbutton,c);
			}
		}
	}
}
