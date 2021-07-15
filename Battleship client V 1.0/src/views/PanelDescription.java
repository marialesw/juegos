package views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelDescription extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lbLives;
	private JButton btnMoverBase;
	private JButton btnUseRadar;
	public PanelDescription() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		lbLives = new JLabel("Cantidad de vidas");
		btnMoverBase = new JButton("Mover Base");
		btnUseRadar = new JButton("Usar radar");
		add(lbLives);
		add(btnMoverBase);
		add(btnUseRadar);
	}
}
