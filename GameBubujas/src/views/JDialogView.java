package views;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

import models.Constants;

public class JDialogView extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanelScoreEnd panelScoreEnd;
	
	public JDialogView() {
		setTitle(Constants.T_DIALOG);
		Image icon = new ImageIcon(getClass().getResource("/imgs/logo.png")).getImage();
        setIconImage(icon);
		setSize(new Dimension(300, 100));
		panelScoreEnd = new JPanelScoreEnd();
		this.add(panelScoreEnd);
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
