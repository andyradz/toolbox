/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.gui.controls.panel;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

/**
 *
 * @author root
 */
public class SlideContainer extends JPanel {

	@Override
	public Insets getInsets() {

		return new Insets(0, 1, 1, 1);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		int h = this.getHeight();
		int w = this.getWidth();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		/**
		 * Top Polygon
		 */
		GeneralPath path = new GeneralPath();
		path.moveTo(70, 0);
		path.lineTo(8, 0);
		path.quadTo(0, 0, 0, 7);
		path.lineTo(0, 55);
		path.lineTo(this.getWidth() - 1, 55);
		path.lineTo(this.getWidth() - 1, 7);
		path.quadTo(this.getWidth() - 1, 0, this.getWidth() - 8, 0);
		path.lineTo(30, 0);

		Rectangle bounds1 = path.getBounds();
		GradientPaint painter = new GradientPaint(0,
				path.getBounds().y,
				true ? new Color(240, 240, 240)
						: new Color(240, 240, 240),
				0,
				(bounds1.y + bounds1.height) - 1,
				true ? new Color(240, 240, 240)
						: new Color(240, 240, 240));
		g2d.setPaint(painter);
		g2d.fill(path);

		Rectangle rectangle = new Rectangle(0, 40, this.getWidth(), 20);
		g2d.fill(rectangle);
		g2d.setColor(new Color(128, 128, 128));
		g2d.draw(path);

		/**
		 * Middle Rectangle
		 */
		g2d.setColor(new Color(128, 128, 128));
		g2d.drawLine(12, 0, this.getWidth() - 10, 0);
		g2d.drawRect(0, 30, this.getWidth() - 1, h - 40);
		g2d.setPaint(new Color(240, 240, 240));
		g2d.fillRect(1, 29, this.getWidth() - 2, h - 80);

		/**
		 * Bottom Polygon
		 */
		h = h - 30;
		path = new GeneralPath();
		path.moveTo(0, h);
		path.lineTo(0, h + 22);
		path.quadTo(0, h + 29, 8, h + 29);
		path.lineTo(this.getWidth() - 8, h + 29);
		path.quadTo(this.getWidth() - 1, h + 29, this.getWidth() - 1, h + 22);
		path.lineTo(this.getWidth() - 1, h);
		g2d.fill(path);
		g2d.setColor(new Color(128, 128, 128));
		g2d.draw(path);
		g2d.setColor(new Color(128, 128, 128));
	}
}
