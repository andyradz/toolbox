package com.codigo.aplios.toolbox.core.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public final class MainPanel extends JPanel {
	public MainPanel() {

		super(new BorderLayout());
		JTextField textField01 = new JTextField(20) {
			// Unleash Your Creativity with Swing and the Java 2D API!
			// http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
			@Override
			protected void paintComponent(Graphics g) {

				if (!this.isOpaque()) {
					int w = this.getWidth();
					int h = this.getHeight();
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setPaint(UIManager.getColor("TextField.background"));
					g2.fillRoundRect(0, 0, w - 1, h - 1, h, h);
					g2.setPaint(Color.GRAY);
					g2.drawRoundRect(0, 0, w - 1, h - 1, h, h);
					g2.dispose();
				}
				super.paintComponent(g);
			}
		};
		textField01.setOpaque(false);
		textField01.setBackground(new Color(0x0, true));
		textField01.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
		textField01.setText("aaaaaaaaaaa");

		JTextField textField02 = new JTextField(20);
		textField02.setBorder(new RoundedCornerBorder());
		textField02.setText("bbbbbbbbbbbb");

		JPanel p = new JPanel(new GridLayout(2, 1, 5, 5));
		p.add(this.makeTitlePanel(textField01, "Override: JTextField#paintComponent(...)"));
		p.add(this.makeTitlePanel(textField02, "setBorder(new RoundedCornerBorder())"));
		this.add(p);
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setPreferredSize(new Dimension(320, 240));
	}

	private JComponent makeTitlePanel(JComponent cmp, String title) {

		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1d;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		p.add(cmp, c);
		p.setBorder(BorderFactory.createTitledBorder(title));
		return p;
	}

	public static void main(String... args) {

		EventQueue.invokeLater(() -> MainPanel.createAndShowGUI());
	}

	public static void createAndShowGUI() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		JFrame frame = new JFrame("@title@");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane()
			.add(new MainPanel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
