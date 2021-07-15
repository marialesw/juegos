package views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class RoundedButton extends JButton{
	private static final long serialVersionUID = 1L;
    private int size;
	
    public RoundedButton(int size) {
    	this.size = size;
        setOpaque(false); // As suggested by @AVD in comment.
    }
    protected void paintComponent(Graphics g) {
    	Graphics2D g2D = (Graphics2D)g;
    	g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setColor(getBackground());
        g2D.fillRoundRect(0, 0, getWidth(), getHeight(), size, size);
        super.paintComponent(g);
    }
}