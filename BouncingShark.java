import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

@SuppressWarnings("serial")
public class BouncingShark extends JFrame {
	private static final int CANVAS_WIDTH = 1300;
	private static final int CANVAS_HEIGHT = 800;
	private static final int UPDATE_PERIOD = 25;

	private DrawCanvas canvas;

	private int x = 400, y = 300;
	private int xSpeed = 3, ySpeed = 5;
	private int x2 = 800, y2 = 600;
	private int x2Speed = 5, y2Speed = 3;
	
	private boolean right2left = true;
	private boolean right2left1 = true;

	public BouncingShark() {
		canvas = new DrawCanvas();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		this.setContentPane(canvas);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setTitle("Bouncing Shark");
		this.setVisible(true);

		ActionListener updateTask = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				update(); 
				repaint();
			}
		};
		new Timer(UPDATE_PERIOD, updateTask).start();
	}

	public void update() {
		x += xSpeed;
		y += ySpeed;
		if (x > CANVAS_WIDTH - 270|| x < 270) {
			xSpeed = - xSpeed;
		}
		if (y > CANVAS_HEIGHT - 160|| y < 0) {
			ySpeed = - ySpeed;
		}
		
		x2 += x2Speed;
		y2 += y2Speed;
		if (x2 > CANVAS_WIDTH - 270|| x2 < 270) {
			x2Speed = - x2Speed;
		}
		if (y2 > CANVAS_HEIGHT - 160|| y2 < 0) {
			y2Speed = - y2Speed;
		}
	}

	private class DrawCanvas extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); 
			
			Graphics2D g2 = (Graphics2D) g;
			
			Rectangle bounds = g2.getClipBounds();
			GradientPaint change = new GradientPaint(0, 0, Color.blue, bounds.width, bounds.height, Color.cyan);
			g2.setPaint(change);
			g2.fillRect(0, 0, bounds.width, bounds.height);
			
			//setBackground(Color.cyan);
			AffineTransform at = new AffineTransform();
			at.translate((double) x, (double) y);
			AffineTransform savedTransform = g2.getTransform();
			
			if (x >= bounds.width - 270)
				right2left = false;
			else if (x <= 270)
				right2left = true;
			if (right2left)
				at.scale(-1, 1);

			g2.setTransform(at);

			// Shark
			Polygon p = new Polygon();
			int px[] = { 0, 90, 120, 120, 90, 150, 270, 270, 150, 120, 120, 100, 120, 30 };
			int py[] = { 30, 30, 0, 30, 30, 30, 120, 50, 140, 140, 160, 140, 140, 140 };
			for (int i = 0; i < px.length; i++)
				p.addPoint(px[i], py[i]);

			// Fill
			g2.setColor(Color.gray);
			g2.fillPolygon(p);

			// Outline
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(3));
			g2.drawPolygon(p);

			// Eye
			g2.drawOval(50, 45, 15, 15);
			g2.fillOval(50, 45, 15, 15);

			// Teeth
			Polygon p1 = new Polygon();
			int px1[] = { 25, 90, 30, 40, 40, 50, 50, 60, 60, 70, 70, 80, 80, 90, 90, 110, 90 };
			int py1[] = { 120, 120, 120, 130, 120, 130, 120, 130, 120, 130, 120, 130, 120, 130, 120, 110, 120 };
			for (int i = 0; i < px1.length; i++)
				p1.addPoint(px1[i], py1[i]);

			// Fill
			g2.setColor(Color.white);
			g2.fillPolygon(p1);

			// Outline
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(3));
			g2.drawPolygon(p1);

			// Nose
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(2));
			g2.drawLine(15, 45, 20, 60);

			// Gills
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(2));
			g2.drawArc(80, 35, 15, 30, 0, -90);
			g2.drawArc(90, 35, 15, 30, 0, -90);
			g2.drawArc(100, 35, 15, 30, 0, -90);

			g2.setTransform(savedTransform);
			
			
			AffineTransform at1 = new AffineTransform();
			at1.translate((double) x2, (double) y2);
			AffineTransform savedTransform1 = g2.getTransform();
			
			if (x2 >= bounds.width - 270)
				right2left1 = false;
			else if (x2 <= 270)
				right2left1 = true;
			if (right2left1)
				at1.scale(-1, 1);

			g2.setTransform(at1);

			// Shark
			Polygon p0 = new Polygon();
			int px0[] = { 0, 90, 120, 120, 90, 150, 270, 270, 150, 120, 120, 100, 120, 30 };
			int py0[] = { 30, 30, 0, 30, 30, 30, 120, 50, 140, 140, 160, 140, 140, 140 };
			for (int i = 0; i < px0.length; i++)
				p0.addPoint(px0[i], py0[i]);

			// Fill
			g2.setColor(Color.gray);
			g2.fillPolygon(p0);

			// Outline
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(3));
			g2.drawPolygon(p0);

			// Eye
			g2.drawOval(50, 45, 15, 15);
			g2.fillOval(50, 45, 15, 15);

			// Teeth
			Polygon p01 = new Polygon();
			int px01[] = { 25, 90, 30, 40, 40, 50, 50, 60, 60, 70, 70, 80, 80, 90, 90, 110, 90 };
			int py01[] = { 120, 120, 120, 130, 120, 130, 120, 130, 120, 130, 120, 130, 120, 130, 120, 110, 120 };
			for (int i = 0; i < px01.length; i++)
				p01.addPoint(px01[i], py01[i]);

			// Fill
			g2.setColor(Color.white);
			g2.fillPolygon(p01);

			// Outline
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(3));
			g2.drawPolygon(p01);

			// Nose
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(2));
			g2.drawLine(15, 45, 20, 60);

			// Gills
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(2));
			g2.drawArc(80, 35, 15, 30, 0, -90);
			g2.drawArc(90, 35, 15, 30, 0, -90);
			g2.drawArc(100, 35, 15, 30, 0, -90);

			g2.setTransform(savedTransform1);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new BouncingShark();
			}
		});
	}
}