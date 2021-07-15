package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

public class Window extends JFrame{

	private static final long serialVersionUID = 1L;
	private ArrayList<Point2D> pointList;
	
	public class Panel extends JPanel{
		
		private static final int MIDDLE = 500;
		private static final String GRAY = "#caccd1";
		private static final String GREEN = "#7ac143";
		private static final int INITIAL_SPACE = 100;
		private static final int BALL_SIZE = 50;
		private static final int INTERBALL_SPACE = 10;
		private static final long serialVersionUID = 1L;
		private Point point;
		
		public Panel() {
			pointList = new ArrayList<>();
			point = new Point(0, BALL_SIZE);
		}
		
		public void addBall() {
			animate();
			pointList.add(new Point(500, pointList.size() * (BALL_SIZE + INTERBALL_SPACE)));
			setPreferredSize(new Dimension(1000, INITIAL_SPACE + pointList.size() * (BALL_SIZE + INTERBALL_SPACE)));
			repaint();
			revalidate();
		}
		
		private void animate() {
			point = new Point(0, BALL_SIZE);
			SwingWorker<Void, Void> animate = new SwingWorker<Void, Void>(){
				@Override
				protected Void doInBackground() throws Exception {
					while (point.getX() < MIDDLE) {
						point.setLocation(point.getX() + 1, point.getX() + 1);
						repaint(0,0,1000,100);
						Thread.sleep(10);
					}
					return null;
				}
			};
			animate.execute();
			
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.YELLOW);
			g.fillOval((int)point.getX(), (int)point.getY(), BALL_SIZE, BALL_SIZE);
			setBackground(Color.WHITE);
			g.setColor(Color.decode(GREEN));
			for (Point2D point : pointList) {
				g.fillOval((int)point.getX() , ((int)point.getY()) + INITIAL_SPACE, BALL_SIZE, BALL_SIZE);
				g.setColor(Color.decode(GRAY));
			}
		}
	}
	
	private Panel panel;

	public Window() {
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel = new Panel();
		add(new JScrollPane(panel), BorderLayout.CENTER);

		JButton btnAddBall = new JButton("Add");
		btnAddBall.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.addBall();
			}
		});
		add(btnAddBall, BorderLayout.PAGE_END);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Window();
	}
}