package com.codigo.aplios.toolbox.core.gui.transparent;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Surface extends JPanel {

	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.setPaint(Color.blue);

		for (int i = 1; i <= 10; i++) {

			float alpha = i * 0.1f;
			AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g2d.setComposite(alcom);
			g2d.fillRect(50 * i, 20, 40, 40);
		}

		g2d.dispose();
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		this.doDrawing(g);
	}
}

public class TransparentRectangle extends JFrame {

	public TransparentRectangle() {

		this.initUI();
	}

	private void initUI() {

		this.add(new Surface2());

		this.setTitle("Transparent rectangles");
		this.setSize(590, 120);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {

			TransparentRectangle ex = new TransparentRectangle();
			ex.setVisible(true);
		});
	}
}
