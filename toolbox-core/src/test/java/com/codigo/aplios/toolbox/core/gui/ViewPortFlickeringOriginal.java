package com.codigo.aplios.toolbox.core.gui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.RepaintManager;
import javax.swing.UIManager;
import javax.swing.table.TableModel;

public class ViewPortFlickeringOriginal {

	private JFrame frame = new JFrame("Table");
	private JViewport viewport = new JViewport();
	private Rectangle RECT = new Rectangle();
	private Rectangle RECT1 = new Rectangle();
	private JTable table = new JTable(100000, 5);
	private javax.swing.Timer timer;
	private int count = 0;
	private GradientViewPortOriginal tableViewPort;
	private static boolean loggerOpacity;
	private JPanel panel = new JPanel();
	private static JButton button;

	public ViewPortFlickeringOriginal() {

		this.table.setRowHeight(20);
		this.tableViewPort = new GradientViewPortOriginal(this.table);
		this.viewport = this.tableViewPort.getViewport();
		this.viewport.addChangeListener(e -> {

			if (ViewPortFlickeringOriginal.this.tableViewPort.bolStart) {
				ViewPortFlickeringOriginal.this.RECT = ViewPortFlickeringOriginal.this.table.getCellRect(0, 0, true);
				ViewPortFlickeringOriginal.this.RECT1 = ViewPortFlickeringOriginal.this.table
					.getCellRect(ViewPortFlickeringOriginal.this.table.getRowCount() - 1, 0, true);
				Rectangle viewRect = ViewPortFlickeringOriginal.this.viewport.getViewRect();
				if (viewRect.intersects(ViewPortFlickeringOriginal.this.RECT)) {
					System.out.println("Visible RECT -> " + ViewPortFlickeringOriginal.this.RECT);
					ViewPortFlickeringOriginal.this.tableViewPort.paintBackGround(new Color(250, 150, 150));
				} else if (viewRect.intersects(ViewPortFlickeringOriginal.this.RECT1)) {
					System.out.println("Visible RECT1 -> " + ViewPortFlickeringOriginal.this.RECT1);
					ViewPortFlickeringOriginal.this.tableViewPort.paintBackGround(new Color(150, 250, 150));
				} else {
					System.out.println("Visible RECT1 -> ???? ");
					ViewPortFlickeringOriginal.this.tableViewPort.paintBackGround(new Color(150, 150, 250));
				}
			}
		});
		this.frame.add(this.tableViewPort);
		ViewPortFlickeringOriginal.button = new JButton("Change Opacity for Java6 / Win7");
		ViewPortFlickeringOriginal.button.setBounds(100, 100, 50, 50);
		ViewPortFlickeringOriginal.button.setVisible(true);
		this.panel.add(ViewPortFlickeringOriginal.button);
		ViewPortFlickeringOriginal.loggerOpacity = true;
		ViewPortFlickeringOriginal.button.addActionListener(evt -> {
			ViewPortFlickeringOriginal.this.count = 0;
			ViewPortFlickeringOriginal.this.timer.restart();
			ViewPortFlickeringOriginal.this.table.removeAll();
			// Object src = evt.getSource();
			// if (src == button && loggerOpacity) {
			// AWTUtilities.setWindowOpacity(frame, 0.80f);
			// }
		});
		this.frame.add(this.panel, BorderLayout.SOUTH);
		this.frame.setPreferredSize(new Dimension(600, 300));
		this.frame.pack();
		this.frame.setLocation(50, 100);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RepaintManager.setCurrentManager(new RepaintManager() {

			@Override
			public void addDirtyRegion(JComponent c, int x, int y, int w, int h) {

				Container con = c.getParent();
				while (con instanceof JComponent) {
					if (!con.isVisible())
						return;
					if (con instanceof GradientViewPortOriginal) {
						c = (JComponent) con;
						x = 0;
						y = 0;
						w = con.getWidth();
						h = con.getHeight();
					}
					con = con.getParent();
				}
				super.addDirtyRegion(c, x, y, w, h);
			}
		});
		this.frame.setVisible(true);
		this.start();
	}

	private void start() {

		this.timer = new javax.swing.Timer(100, this.updateCol());
		this.timer.start();
	}

	public Action updateCol() {

		return new AbstractAction("text load action") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("updating row " + (ViewPortFlickeringOriginal.this.count + 1));
				TableModel model = ViewPortFlickeringOriginal.this.table.getModel();
				int cols = model.getColumnCount();
				int row = 0;
				for (int j = 0; j < cols; j++) {
					row = ViewPortFlickeringOriginal.this.count;
					ViewPortFlickeringOriginal.this.table.changeSelection(row, 0, false, false);
					ViewPortFlickeringOriginal.this.timer.setDelay(10);
					Object value = "row " + (ViewPortFlickeringOriginal.this.count + 1) + " item " + (j + 1);
					model.setValueAt(value, ViewPortFlickeringOriginal.this.count, j);
				}
				ViewPortFlickeringOriginal.this.count++;
				if (ViewPortFlickeringOriginal.this.count >= ViewPortFlickeringOriginal.this.table.getRowCount()) {
					ViewPortFlickeringOriginal.this.timer.stop();
					ViewPortFlickeringOriginal.this.table.changeSelection(0, 0, false, false);
					java.awt.EventQueue.invokeLater(() -> {

						ViewPortFlickeringOriginal.this.table.clearSelection();
						ViewPortFlickeringOriginal.this.tableViewPort.bolStart = true;
					});
				}
			}
		};
	}

	public static void main(String[] args) {

		try {
			// Significantly improves the look of the output in
			// terms of the file names returned by FileSystemView!
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception weTried) {
		}

		java.awt.EventQueue.invokeLater(() -> {

			ViewPortFlickeringOriginal viewPortFlickering = new ViewPortFlickeringOriginal();
		});
	}
}

class GradientViewPortOriginal extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private final int h = 50;
	private BufferedImage img = null;
	private BufferedImage shadow = new BufferedImage(1, this.h, BufferedImage.TYPE_INT_ARGB);
	private JViewport viewPort;
	public boolean bolStart = false;

	// -----------------------------------------------------------------------------------------------------------------

	public GradientViewPortOriginal(JComponent com) {

		super(com);
		this.viewPort = this.getViewport();
		this.viewPort.setScrollMode(JViewport.BLIT_SCROLL_MODE);
		this.viewPort.setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
		this.viewPort.setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		this.paintBackGround(new Color(250, 150, 150));
	}

	// -----------------------------------------------------------------------------------------------------------------

	public void paintBackGround(Color g) {

		Graphics2D g2 = this.shadow.createGraphics();
		g2.setPaint(g);
		g2.fillRect(0, 0, 1, this.h);
		g2.setComposite(AlphaComposite.DstIn);
		g2.setPaint(new GradientPaint(0, 0, new Color(0, 0, 0, 0f), 0, this.h, new Color(0.1f, 0.8f, 0.8f, 0.5f)));
		g2.fillRect(0, 0, 1, this.h);
		g2.dispose();
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public void paint(Graphics g) {

		if ((this.img == null) || (this.img.getWidth() != this.getWidth())
				|| (this.img.getHeight() != this.getHeight()))
			this.img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = this.img.createGraphics();
		super.paint(g2);
		Rectangle bounds = this.getViewport()
			.getVisibleRect();
		g2.scale(bounds.getWidth(), -1);
		int y = (this.getColumnHeader() == null) ? 0
				: this.getColumnHeader()
					.getHeight();
		g2.drawImage(this.shadow, bounds.x, -bounds.y - y - this.h, null);
		g2.scale(1, -1);
		g2.drawImage(this.shadow, bounds.x, ((bounds.y + bounds.height) - this.h) + y, null);
		g2.dispose();
		g.drawImage(this.img, 0, 0, null);
	}

	// -----------------------------------------------------------------------------------------------------------------
}
