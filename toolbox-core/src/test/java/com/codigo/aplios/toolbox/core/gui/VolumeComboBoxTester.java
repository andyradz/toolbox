package com.codigo.aplios.toolbox.core.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

public class VolumeComboBoxTester extends JFrame {

	public VolumeComboBoxTester() throws FileNotFoundException {

		super("Demo program for custom combobox");

		FileSystemView fsv = FileSystemView.getFileSystemView();

		File[] drives = File.listRoots();
		int idx = 0;
		if ((drives != null) && (drives.length > 0))
			for (File aDrive : drives) {
				// System.out.println("Drive Letter: " + aDrive);
				// System.out.println("\tType: " + fsv.getSystemTypeDescription(aDrive));
				// System.out.println("\tTotal space: " + aDrive.getTotalSpace());
				// System.out.println("\tFree space: " + aDrive.getFreeSpace());
				// System.out.println();

				sun.awt.shell.ShellFolder sf;
				sf = sun.awt.shell.ShellFolder.getShellFolder(Paths.get(aDrive.toString())
					.toFile());
				Icon icon = new ImageIcon(sf.getIcon(true));
				Image image = ((ImageIcon) icon).getImage();
				this.countryList[idx++][0] = sf.toString();
				// countryList[idx++][1] = image;
				if (idx > 1)
					break;
			}

		this.setLayout(new FlowLayout());

		VolumeComboBox customCombobox = new VolumeComboBox();
		customCombobox.setPreferredSize(new Dimension(120, 30));
		customCombobox.setEditable(false);
		customCombobox.addItems(this.countryList);

		this.add(customCombobox);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 100);
		this.setLocationRelativeTo(null); // center on screen
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		SwingUtilities.invokeLater(() -> {

			try {
				new VolumeComboBoxTester().setVisible(true);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	private String[][] countryList = new String[10][2];
}
