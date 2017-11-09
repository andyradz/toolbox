package com.codigo.aplios.toolbox.core.gui.transparent;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Surface1 extends JPanel implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -4511059221756686552L;
	private Image img;
	private Timer timer;
	private float alpha = 1.0f;

	private final int DELAY = 40;
	private final int INITIAL_DELAY = 5;

	public Surface1() {

		this.loadImage();
		this.setSurfaceSize();
		this.initTimer();
	}

	private void loadImage() {

		this.img = new ImageIcon("mushrooms.jpg").getImage();
	}

	private void setSurfaceSize() {

		int h = this.img.getHeight(this);
		int w = this.img.getWidth(this);
		this.setPreferredSize(new Dimension(w, h));
	}

	private void initTimer() {

		this.timer = new Timer(this.DELAY, this);
		this.timer.setInitialDelay(this.INITIAL_DELAY);
		this.timer.start();
	}

	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

		AlphaComposite acomp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha);
		g2d.setComposite(acomp);
		g2d.drawImage(this.img, 0, 0, null);

		g2d.dispose();
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		this.doDrawing(g);
	}

	private void step() {

		this.alpha += -0.01f;

		if (this.alpha <= 0) {
			this.alpha = 0;
			this.timer.stop();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		this.step();
		this.repaint();
	}
}

public class FadeOutEx extends JFrame {

	public FadeOutEx() {

		this.initUI();
	}

	private void initUI() {

		this.add(new Surface1());

		// pack();

		this.setTitle("Fade out");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {

			FadeOutEx ex = new FadeOutEx();
			ex.setVisible(true);
		});
	}
}
