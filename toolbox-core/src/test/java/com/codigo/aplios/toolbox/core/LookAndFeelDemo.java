package com.codigo.aplios.toolbox.core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

/**
 * @author dp0470
 * @since 0.0.01
 * @version 1.0
 */
public class LookAndFeelDemo implements ActionListener {
	private static String labelPrefix = "Number of button clicks: ";
	private int numClicks = 0;
	final JLabel label = new JLabel(LookAndFeelDemo.labelPrefix + "0    ");

	// Specify the look and feel to use by defining the LOOKANDFEEL constant
	// Valid values are: null (use the default), "Metal", "System", "Motif",
	// and "GTK"
	final static String LOOKANDFEEL = "System";

	// If you choose the Metal L&F, you can also choose a theme.
	// Specify the theme to use by defining the THEME constant
	// Valid values are: "DefaultMetal", "Ocean", and "Test"
	final static String THEME = "Test";

	public Component createComponents() {

		JButton button = new JButton("I'm a Swing button!");
		button.setMnemonic(KeyEvent.VK_I);
		button.addActionListener(this);
		this.label.setLabelFor(button);

		JPanel pane = new JPanel(new GridLayout(0, 1));
		pane.add(button);
		pane.add(this.label);
		pane.setBorder(BorderFactory.createEmptyBorder(30, // top
				30, // left
				10, // bottom
				30) // right
		);

		return pane;
	}

	private void hlin() {

		List<String> l = Collections.EMPTY_LIST;
		l = new ArrayList<>();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		this.numClicks++;
		this.label.setText(LookAndFeelDemo.labelPrefix + this.numClicks);
	}

	private static void initLookAndFeel() {

		String lookAndFeel = null;

		if (LookAndFeelDemo.LOOKANDFEEL != null) {
			if (LookAndFeelDemo.LOOKANDFEEL.equals("Metal"))
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			// an alternative way to set the Metal L&F is to replace the
			// previous line with:
			// lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
			else if (LookAndFeelDemo.LOOKANDFEEL.equals("System"))
				lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			else if (LookAndFeelDemo.LOOKANDFEEL.equals("Motif"))
				lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			else if (LookAndFeelDemo.LOOKANDFEEL.equals("GTK"))
				lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
			else {
				System.err.println("Unexpected value of LOOKANDFEEL specified: " + LookAndFeelDemo.LOOKANDFEEL);
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			}

			try {

				UIManager.setLookAndFeel(lookAndFeel);

				// If L&F = "Metal", set the theme

				if (LookAndFeelDemo.LOOKANDFEEL.equals("Metal")) {
					if (LookAndFeelDemo.THEME.equals("DefaultMetal"))
						MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
					else if (LookAndFeelDemo.THEME.equals("Ocean"))
						MetalLookAndFeel.setCurrentTheme(new OceanTheme());
					// else
					// MetalLookAndFeel.setCurrentTheme(new TestTheme());

					UIManager.setLookAndFeel(new MetalLookAndFeel());
				}

			}

			catch (ClassNotFoundException e) {
				System.err.println("Couldn't find class for specified look and feel:" + lookAndFeel);
				System.err.println("Did you include the L&F library in the class path?");
				System.err.println("Using the default look and feel.");
			}

			catch (UnsupportedLookAndFeelException e) {
				System.err.println("Can't use the specified look and feel (" + lookAndFeel + ") on this platform.");
				System.err.println("Using the default look and feel.");
			}

			catch (Exception e) {
				System.err.println("Couldn't get specified look and feel (" + lookAndFeel + "), for some reason.");
				System.err.println("Using the default look and feel.");
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 */
	private static void createAndShowGUI() {

		// Set the look and feel.
		LookAndFeelDemo.initLookAndFeel();

		// Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);

		// Create and set up the window.
		JFrame frame = new JFrame("SwingApplication");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		LookAndFeelDemo app = new LookAndFeelDemo();
		Component contents = app.createComponents();
		frame.getContentPane()
			.add(contents, BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(() -> LookAndFeelDemo.createAndShowGUI());
	}
}
