package com.codigo.aplios.toolbox.core.gui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.LayerUI;

public class Zoomix extends JPanel {
	/**
	 *
	 */
	private static final long	serialVersionUID	= -8072710216485368234L;
	private final JTabbedPane	tabbedPane0			= new CloseableTabbedPane();
	private final JTabbedPane	tabbedPane1			= new JTabbedPane();
	private final JButton		addTabButton		= new JButton("add tab");
	private final JButton		fileButton			= new JButton("Select File");

	public Zoomix() {

		super(new BorderLayout());

		for (JTabbedPane t : Arrays.asList(this.tabbedPane0, this.tabbedPane1)) {
			t.addTab("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", new JLabel("aaa"));
			t.addTab("bbbbbbbbaa", new JLabel("bbb"));
			t.addTab("ccc", new JLabel("ccc"));
			t.addTab("d", new JLabel("ddd"));
		}

		// EventQueue.invokeLater(new Runnable() {
		// @Override public void run() {
		// JPanel gp = new CloseableTabbedPaneGlassPane(tabbedPane);
		// tabbedPane.getRootPane().setGlassPane(gp);
		// gp.setOpaque(false);
		// gp.setVisible(true);
		// }
		// });

		this.addTabButton.addActionListener(e -> {
			String title = new Date().toString();
			for (JTabbedPane t : Arrays.asList(this.tabbedPane0, this.tabbedPane1))
				t.addTab(title, new JLabel(title));
		});

		this.fileButton.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Open a  picture file");

			FileFilter fileFilter = new FileFilter() {

				@Override
				public boolean accept(File f) {

					if (f.isFile()) return true;
					return false;
				}

				@Override
				public String getDescription() {

					return "File element";
				}
			};
			fileChooser.setFileFilter(fileFilter);

			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println("we selected: " + selectedFile);
			}
		});

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sp.setTopComponent(this.tabbedPane0);
		sp.setBottomComponent(new JLayer<>(this.tabbedPane1,
			new CloseableTabbedPaneLayerUI()));

		this.add(sp);
		this.add(this.addTabButton, BorderLayout.SOUTH);
		// add(fileButton, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(320,
			240));
	}

	public static void main(String... args) {

		EventQueue.invokeLater(() -> Zoomix.createAndShowGUI());
	}

	public static void createAndShowGUI() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			int g = CloseableTabbedPaneLayerUI.GAP;
			UIManager.put("TabbedPane.tabInsets", new Insets(g,
				16 + g,
				g,
				16 + g));
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		JFrame frame = new JFrame("@title@");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane()
			.add(new Zoomix());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

class CloseTabIcon implements Icon {
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {

		Graphics2D g2 = (Graphics2D) g.create();
		g2.translate(x, y);
		if ((c instanceof AbstractButton)
			&& ((AbstractButton) c).getModel()
				.isRollover())
			g2.setPaint(Color.ORANGE);
		else
			g2.setPaint(Color.BLACK);
		g2.drawLine(4, 4, 11, 11);
		g2.drawLine(4, 5, 10, 11);
		g2.drawLine(5, 4, 11, 10);
		g2.drawLine(11, 4, 4, 11);
		g2.drawLine(11, 5, 5, 11);
		g2.drawLine(10, 4, 4, 10);
		g2.dispose();
	}

	@Override
	public int getIconWidth() {

		return 16;
	}

	@Override
	public int getIconHeight() {

		return 16;
	}
}

class CloseableTabbedPane extends JTabbedPane {
	/**
	 *
	 */
	private static final long	serialVersionUID	= 3157997108659380271L;
	private static final Icon	CLOSE_ICON			= new CloseTabIcon();

	@Override
	public void addTab(String title, final Component content) {

		JPanel tab = new JPanel(new BorderLayout());
		tab.setOpaque(false);
		JLabel label = new JLabel(title);
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));
		JButton button = new JButton(CloseableTabbedPane.CLOSE_ICON);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.addActionListener(e -> this.removeTabAt(this.indexOfComponent(content)));
		tab.add(label, BorderLayout.WEST);
		tab.add(button, BorderLayout.EAST);
		tab.setBorder(BorderFactory.createEmptyBorder(2, 1, 1, 1));
		super.addTab(title, content);
		this.setTabComponentAt(this.getTabCount() - 1, tab);
	}
}

class CloseableTabbedPaneLayerUI extends LayerUI<JTabbedPane> {
	/**
	 *
	 */
	private static final long	serialVersionUID	= -4287848443508261422L;
	public static final int		GAP					= 2;
	private final JComponent	rubberStamp			= new JPanel();
	private final Point			pt					= new Point();
	private final JButton		button				= new JButton(new CloseTabIcon()) {
														/**
														 *
														 */
														private static final long serialVersionUID
																= 2657417461577763033L;

														@Override
														public void updateUI() {

															super.updateUI();
															this.setBorder(BorderFactory.createEmptyBorder());
															this.setFocusPainted(false);
															this.setBorderPainted(false);
															this.setContentAreaFilled(false);
															this.setRolloverEnabled(false);
														}
													};
	private final Dimension		d					= this.button.getPreferredSize();
	private final Rectangle		repaintRect			= new Rectangle(this.d.width * 2,
		this.d.height * 2);

	private Rectangle getTabButtonRect(JTabbedPane tabbedPane, int index) {

		Rectangle r = tabbedPane.getBoundsAt(index);
		// Dimension d = button.getPreferredSize();
		r.translate(r.width - this.d.width - CloseableTabbedPaneLayerUI.GAP, (r.height - this.d.height) / 2);
		r.setSize(this.d);
		return r;
	}

	@Override
	public void paint(Graphics g, JComponent c) {

		super.paint(g, c);
		if (c instanceof JLayer) {
			JTabbedPane tabbedPane = (JTabbedPane) (JLayer.class.cast(c)
				.getView());
			for (int i = 0; i < tabbedPane.getTabCount(); i++) {
				Rectangle r = this.getTabButtonRect(tabbedPane, i);
				this.button.getModel()
					.setRollover(r.contains(this.pt));
				SwingUtilities.paintComponent(g, this.button, this.rubberStamp, r);
			}
		}
	}

	@Override
	public void installUI(JComponent c) {

		super.installUI(c);
		if (c instanceof JLayer) JLayer.class.cast(c)
			.setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
	}

	@Override
	public void uninstallUI(JComponent c) {

		if (c instanceof JLayer) JLayer.class.cast(c)
			.setLayerEventMask(0);
		super.uninstallUI(c);
	}

	@Override
	protected void processMouseEvent(MouseEvent e, JLayer<? extends JTabbedPane> l) {
		//
		// if (e.getID() == MouseEvent.MOUSE_CLICKED) {
		// this.pt.setLocation(e.getPoint());
		// JTabbedPane tabbedPane = l.getView();
		// int index = tabbedPane.indexAtLocation(this.pt.x, this.pt.y);
		// if ((index >= 0)
		// && this.getTabButtonRect(tabbedPane, index)
		// .contains(this.pt))
		// tabbedPane.removeTabAt(index);
		// }
	}

	@Override
	protected void processMouseMotionEvent(MouseEvent e, JLayer<? extends JTabbedPane> l) {

		Point loc = e.getPoint();
		this.pt.setLocation(loc);
		if (l.getView()
			.indexAtLocation(this.pt.x, this.pt.y) >= 0) {
			// Dimension d = button.getPreferredSize();
			loc.translate(-this.d.width, -this.d.height);
			this.repaintRect.setLocation(loc);
			l.repaint(this.repaintRect);
		}
	}
	// System.out.format("%d : %d%n", prevIndex, index);
	// if (index >= 0) {
	// if (prevIndex >= 0 && prevIndex != index) {
	// Rectangle rect = tabbedPane.getBoundsAt(prevIndex);
	// rect.add(tabbedPane.getBoundsAt(index));
	// System.out.println(rect);
	// tabbedPane.repaint(rect);
	// } else {
	// tabbedPane.repaint(tabbedPane.getBoundsAt(index));
	// }
	// }
	// prevIndex = index;
	// }
	// private int prevIndex;
}

// class CloseableTabbedPaneGlassPane extends JPanel {
// private final Point pt = new Point();
// private final JButton button = new JButton("x") {
// @Override public Dimension getPreferredSize() {
// return new Dimension(16, 16);
// }
// };
// private final JTabbedPane tabbedPane;
// private final Rectangle buttonRect = new Rectangle(button.getPreferredSize());
//
// protected CloseableTabbedPaneGlassPane(JTabbedPane tabbedPane) {
// super();
// this.tabbedPane = tabbedPane;
// MouseAdapter h = new Handler();
// tabbedPane.addMouseListener(h);
// tabbedPane.addMouseMotionListener(h);
// button.setBorder(BorderFactory.createEmptyBorder());
// button.setFocusPainted(false);
// button.setBorderPainted(false);
// button.setContentAreaFilled(false);
// button.setRolloverEnabled(false);
// }
// @Override protected void paintComponent(Graphics g) {
// Point glassPt = SwingUtilities.convertPoint(tabbedPane, 0, 0, this);
// for (int i = 0; i < tabbedPane.getTabCount(); i++) {
// Rectangle tabRect = tabbedPane.getBoundsAt(i);
// int x = tabRect.x + tabRect.width - buttonRect.width - 2;
// int y = tabRect.y + (tabRect.height - buttonRect.height) / 2;
// buttonRect.setLocation(x, y);
// button.setForeground(buttonRect.contains(pt) ? Color.RED : Color.BLACK);
// buttonRect.translate(glassPt.x, glassPt.y);
// SwingUtilities.paintComponent(g, button, this, buttonRect);
// }
// }
// class Handler extends MouseAdapter {
// @Override public void mouseClicked(MouseEvent e) {
// pt.setLocation(e.getPoint());
// int index = tabbedPane.indexAtLocation(pt.x, pt.y);
// if (index >= 0) {
// Rectangle tabRect = tabbedPane.getBoundsAt(index);
// int x = tabRect.x + tabRect.width - buttonRect.width - 2;
// int y = tabRect.y + (tabRect.height - buttonRect.height) / 2;
// buttonRect.setLocation(x, y);
// if (buttonRect.contains(pt)) {
// tabbedPane.removeTabAt(index);
// }
// }
// tabbedPane.repaint();
// }
// @Override public void mouseMoved(MouseEvent e) {
// pt.setLocation(e.getPoint());
// int index = tabbedPane.indexAtLocation(pt.x, pt.y);
// if (index >= 0) {
// tabbedPane.repaint(tabbedPane.getBoundsAt(index));
// } else {
// tabbedPane.repaint();
// }
// }
// }
// }