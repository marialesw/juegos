package views;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class JDialogView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanelScoreEnd panelScoreEnd;
	private int height;
	private int width;

	public JDialogView(ActionListener listener) {
		height = 600;
		width = 700;
		panelScoreEnd = new JPanelScoreEnd(width, height, listener);
		this.add(panelScoreEnd);	
		Image icon = new ImageIcon(getClass().getResource(Constants.LOGO_GAME)).getImage();
		setIconImage(icon);
		setSize(new Dimension(width,height));
		centrar();	
	}

	private void centrar( ){
		Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
		int xEsquina = ( screen.width - getWidth( ) ) / 2;
		int yEsquina = ( screen.height - getHeight( ) ) / 2;
		setLocation( xEsquina, yEsquina );
	}

	public void showScore(double score){
		panelScoreEnd.setScore(score);
	}
}
