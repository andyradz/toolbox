package com.codigo.aplios.toolbox.core.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;

public class AndroidIcon implements Icon {

	protected int mWidth = 150;
	protected int mHeight = 150;

	@Override
	public int getIconWidth() {

		return this.mWidth;
	}

	@Override
	public int getIconHeight() {

		return this.mHeight;
	}

	@Override
	public void paintIcon(Component arg0, Graphics g, int arg2, int arg3) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(153, 204, 0));
		g2d.fillArc(75, 55, 150, 125, 0, 180); // head circle
		g2d.fillRect(75, 125, 150, 150); // body
		g2d.fillRoundRect(75, 260, 150, 20, 20, 20); // cause bottom of body has round corners
		g2d.fillRoundRect(40, 120, 30, 110, 30, 30); // right hand
		g2d.fillRoundRect(230, 120, 30, 110, 30, 30); // left hand
		g2d.fillRoundRect(95, 260, 30, 70, 30, 30); // right leg
		g2d.fillRoundRect(175, 260, 30, 70, 30, 30); // left leg
		g2d.drawLine(125, 65, 100, 40); // right head antenna
		g2d.drawLine(175, 65, 200, 40); // left head antenna

		g2d.setColor(Color.white);
		g2d.fillOval(115, 80, 10, 10); // left eye
		g2d.fillOval(175, 80, 10, 10); // right eye
	}
}
