package views;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelPlayerGame extends JPanel {
	

	private static final long serialVersionUID = 1L;

	public PanelPlayerGame(JPanel panelDescription,JPanel panelPlayer) {
		setLayout(new BorderLayout());
		add(panelDescription,BorderLayout.LINE_START);
		add(panelDescription,BorderLayout.CENTER);
		
	}

}
