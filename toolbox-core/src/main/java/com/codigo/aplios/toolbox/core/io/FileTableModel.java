/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andrzej.radziszewski
 */
public class FileTableModel extends AbstractTableModel {

	private static String getFileExtension(File file) throws FileNotFoundException {

		sun.awt.shell.ShellFolder sf = sun.awt.shell.ShellFolder.getShellFolder(file);
		// ShellFolderColumnInfo[] na= sf.getFolderColumns();
		String fileName = sf.getDisplayName();
		if (sf.isDirectory())
			return "";
		if ((fileName.lastIndexOf(".") != -1) && (fileName.lastIndexOf(".") != 0))
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	protected File dir;

	public String[] filenames;

	protected String[] columnNames =
			new String[]
			{ "", "name", "", "ext", "size", "last modified", "directory?", "readable?", "writable?", };

	protected Class[] columnClasses =
			new Class[]
			{
				ImageIcon.class,
				String.class,
				String.class,
				String.class,
				String.class,
				Date.class,
				Boolean.class,
				Boolean.class,
				Boolean.class };

	// This table model works for any one given directory
	public FileTableModel(File dir) {

		this.dir = dir;
		this.filenames = dir.list(); // Store a list of files in the directory
		System.out.println(dir.getAbsolutePath());
		this.filenames = FileTableDemo.fileList(dir.getAbsolutePath());
	}

	public void reload(File dir) {

		this.dir = dir;
		this.filenames = dir.list(); // Store a list of files in the directory
		System.out.println(dir.getAbsolutePath());
		this.filenames = FileTableDemo.fileList(dir.getAbsolutePath());
	}

	// These are easy methods
	@Override
	public int getColumnCount() {

		return 8;
	} // A constant for this model

	@Override
	public int getRowCount() {

		return this.filenames.length;
	} // # of files in dir

	// Information about each column
	@Override
	public String getColumnName(int col) {

		return this.columnNames[col];
	}

	@Override
	public Class getColumnClass(int col) {

		return this.columnClasses[col];
	}

	// The method that must actually return the value of each cell
	@Override
	public Object getValueAt(int row, int col) {

		final File f = Paths.get(this.filenames[row])
			.toFile();
		switch (col) {
		// case 0:
		// sun.awt.shell.ShellFolder sf;
		// Image image = null;
		// File ff = new File("C:/Windows");
		// try {
		// sf = sun.awt.shell.ShellFolder.getShellFolder(ff);
		// Icon icon = new ImageIcon(sf.getIcon(true));
		// image = ((ImageIcon) icon).getImage();
		//
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		// return image;
		case 1:
			return f.getAbsolutePath();
		case 2:
			return "<dir>";
		case 3:

		case 4:
			DecimalFormat formatter = new DecimalFormat();
			formatter.applyPattern("#,##0.###");
			DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
			symbols.setGroupingSeparator(' ');
			formatter.setDecimalFormatSymbols(symbols);
			double v = f.length() / 1024d;
			BigDecimal d = BigDecimal.valueOf(f.length() / 1024d);
			d.setScale(1, RoundingMode.HALF_UP);
			return formatter.format(v) + " kB";
		case 5:
			return new Date(f.lastModified());
		case 6:
			return f.isDirectory() ? Boolean.TRUE
					: Boolean.FALSE;
		case 7:
			return f.canRead() ? Boolean.TRUE
					: Boolean.FALSE;
		case 8:
			return f.canWrite() ? Boolean.TRUE
					: Boolean.FALSE;
		default:
			return null;
		}
	}

	// public Font getFont() {
	//
	// try {
	// InputStream is = GUI.class.getResourceAsStream("TestFont.ttf");
	// Font font = Font.createFont(Font.TRUETYPE_FONT, is);
	// return font;
	// } catch (FontFormatException | IOException ex) {
	//
	// return super.getFont();
	// }
	// }
}
