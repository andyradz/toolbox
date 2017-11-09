package com.codigo.aplios.toolbox.core.gui.transparent;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

final class Surface2 extends JPanel implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -2876784102291476986L;
	private Timer timer;
	private int count;
	private final int INITIAL_DELAY = 200;
	private final int DELAY = 500;
	private final int NUMBER_OF_LINES = 8;
	private final int STROKE_WIDTH = 12;

	private final double[][] trs =
			{
				{ 0.0, 0.15, 0.30, 0.5, 0.65, 0.80, 0.9, 1.0 },
				{ 1.0, 0.0, 0.15, 0.30, 0.5, 0.65, 0.8, 0.9 },
				{ 0.9, 1.0, 0.0, 0.15, 0.3, 0.5, 0.65, 0.8 },
				{ 0.8, 0.9, 1.0, 0.0, 0.15, 0.3, 0.5, 0.65 },
				{ 0.65, 0.8, 0.9, 1.0, 0.0, 0.15, 0.3, 0.5 },
				{ 0.5, 0.65, 0.8, 0.9, 1.0, 0.0, 0.15, 0.3 },
				{ 0.3, 0.5, 0.65, 0.8, 0.9, 1.0, 0.0, 0.15 },
				{ 0.15, 0.3, 0.5, 0.65, 0.8, 0.9, 1.0, 0.0 } };

	public Surface2() {

		this.initTimer();
	}

	private void initTimer() {

		this.timer = new Timer(this.DELAY, this);
		this.timer.setInitialDelay(this.INITIAL_DELAY);
		this.timer.start();
	}

	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		int width = this.getWidth();
		int height = this.getHeight();
		if (0 == (this.count % 8))
			g2d.setColor(Color.red);
		else
			g2d.setColor(Color.BLUE);
		g2d.setStroke(new BasicStroke(this.STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
		g2d.translate(width / 2, height / 2);

		for (int i = 0; i < this.NUMBER_OF_LINES; i++) {
			// for (int i = 0; i < 1; i++) {

			float alpha = (float) this.trs[this.count % this.NUMBER_OF_LINES][i];
			AlphaComposite acomp = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha);
			g2d.setComposite(acomp);

			g2d.rotate(Math.PI / 4f);
			g2d.drawLine(0, -20, 0, -120);
		}

		g2d.dispose();
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		this.doDrawing(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		this.repaint();
		this.count++;
	}
}

public class WaitingEx extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 4594864986140268822L;

	public WaitingEx() {

		this.initUI();
	}

	private void initUI() {

		this.add(new Surface2());

		this.setTitle("Waiting");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			WaitingEx ex = new WaitingEx();
			ex.setVisible(true);
		});
	}
}
