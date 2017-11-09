import java.awt.AWTException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
// aterai/java-swing-tips

public final class MainPanel extends JPanel {
	private final JDialog dialog = new JDialog();
	private final Timer animator;
	private final transient Image[] imglist = new Image[4];
	private final transient TrayIcon icon;

	public MainPanel() {

		super();
		this.setPreferredSize(new Dimension(320, 240));

		if (!SystemTray.isSupported())
			throw new UnsupportedOperationException("SystemTray is not supported");

		this.imglist[0] = new ImageIcon(this.getClass()
			.getResource("16x16.png")).getImage();
		this.imglist[1] = new ImageIcon(this.getClass()
			.getResource("16x16l.png")).getImage();
		this.imglist[2] = this.imglist[0];
		this.imglist[3] = new ImageIcon(this.getClass()
			.getResource("16x16r.png")).getImage();

		this.dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.dialog.setSize(new Dimension(120, 100));
		this.dialog.setLocationRelativeTo(null);
		this.dialog.setTitle("TEST: JDialog");

		// TEST: icon = new TrayIcon(new ImageIcon(getClass().getResource("anime.gif")).getImage(),
		// "TRAY", popup);
		this.icon = new TrayIcon(this.imglist[0], "TRAY", this.makeTrayPopupMenu());
		this.animator = new Timer(100, new ActionListener() {
			private int idx;

			@Override
			public void actionPerformed(ActionEvent e) {

				MainPanel.this.icon.setImage(MainPanel.this.imglist[this.idx]);
				this.idx = (this.idx + 1) % MainPanel.this.imglist.length;
			}
		});
		try {
			SystemTray.getSystemTray()
				.add(this.icon);
		} catch (AWTException ex) {
			ex.printStackTrace();
		}
	}

	private PopupMenu makeTrayPopupMenu() {

		MenuItem item1 = new MenuItem("Open:Frame");
		item1.addActionListener(e -> {
			Container c = this.getTopLevelAncestor();
			if (c instanceof Window)
				((Window) c).setVisible(true);
		});

		MenuItem item2 = new MenuItem("Open:Dialog");
		item2.addActionListener(e -> this.dialog.setVisible(true));

		MenuItem item3 = new MenuItem("Animation:Start");
		item3.addActionListener(e -> this.animator.start());

		MenuItem item4 = new MenuItem("Animation:Stop");
		item4.addActionListener(e -> {
			this.animator.stop();
			this.icon.setImage(this.imglist[0]);
		});

		MenuItem item5 = new MenuItem("Exit");
		item5.addActionListener(e -> {
			this.animator.stop();
			SystemTray tray = SystemTray.getSystemTray();
			for (TrayIcon icon : tray.getTrayIcons())
				tray.remove(icon);
			for (Frame frame : Frame.getFrames())
				frame.dispose();
		});

		PopupMenu popup = new PopupMenu();
		popup.add(item1);
		popup.add(item2);
		popup.addSeparator();
		popup.add(item3);
		popup.add(item4);
		popup.addSeparator();
		popup.add(item5);

		return popup;
	}

	public static void main(String... args) {

		EventQueue.invokeLater(() -> {
			System.out.println(Thread.currentThread());
			MainPanel.createAndShowGUI();
		});
	}

	public static void createAndShowGUI() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		JFrame frame = new JFrame("@title@");
		// frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.getContentPane()
			.add(new MainPanel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
