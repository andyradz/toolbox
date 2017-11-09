package com.codigo.aplios.toolbox.core.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class FileTableDemo {

	public static String getRelativePath(File base, File name) throws IOException {

		File parent = base.getParentFile();

		if (parent == null)
			throw new IOException("No common directory");

		String bpath = base.getCanonicalPath();
		String fpath = name.getCanonicalPath();

		if (fpath.startsWith(bpath))
			return fpath.substring(bpath.length() + 1);
		else
			return (".." + File.separator + FileTableDemo.getRelativePath(parent, name));
	}

	public static void scrollToVisible(JTable table, int rowIndex, int vColIndex) {

		if (!(table.getParent() instanceof JViewport))
			return;
		JViewport viewport = (JViewport) table.getParent();

		// This rectangle is relative to the table where the
		// northwest corner of cell (0,0) is always (0,0).
		Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);

		// The location of the viewport relative to the table
		Point pt = viewport.getViewPosition();

		// Translate the cell location so that it is relative
		// to the view, assuming the northwest corner of the
		// view is (0,0)
		rect.setLocation(rect.x - pt.x, rect.y - pt.y);

		table.scrollRectToVisible(rect);

		// Scroll the area into view
		viewport.scrollRectToVisible(rect);
		table.changeSelection(rowIndex, vColIndex, false, false);
	}

	private static void createKeybindings(JTable table) {

		InputMap im = table.getInputMap();
		ActionMap am = table.getActionMap();

		// KeyStroke spaceKey = KeyStroke.getKeyStroke(KeyEvent.KEY_PRESSED, 0);
		// im.put(spaceKey, "passed");
		// am.put("passed", new AbstractAction() {
		// @Override
		// public void actionPerformed(ActionEvent ae) {
		//
		// Enumeration<TableColumn> cols = table.getColumnModel().getColumns();
		// while (cols.hasMoreElements())
		// cols.nextElement().setCellRenderer(new StatusColumnCellRenderer());
		//
		// }
		// });
		// im.put(spaceKey, "released");
		// am.put("released", new AbstractAction() {
		// @Override
		// public void actionPerformed(ActionEvent ae) {
		//
		// Enumeration<TableColumn> cols = table.getColumnModel().getColumns();
		// //while (cols.hasMoreElements())
		// //cols.nextElement().setCellRenderer(new DefaultTableCellRenderer());
		//
		// }
		// });

		table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
			.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");

		table.getActionMap()
			.put("Enter", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent ae) {

					int column = 1;
					int row = table.getSelectedRow();
					String value = table.getModel()
						.getValueAt(row, column)
						.toString();
					boolean isReadable = true;

					try {
						// isReadable = Files.isReadable(Paths.get(value));
						isReadable = FileSystemView.getFileSystemView()
							.isTraversable(Paths.get(value)
								.toFile());
						boolean isHidden = Files.isHidden(Paths.get(value));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (!isReadable)
						return;

					if (!(Paths.get(value)
						.toFile()
						.isDirectory()))
						return;
					Arrays.fill(((FileTableModel) table.getModel()).filenames, null);
					((FileTableModel) table.getModel()).filenames = FileTableDemo.fileList(value);
					((FileTableModel) table.getModel()).fireTableDataChanged();

					table.requestFocus(false);
					table.changeSelection(0, 0, false, false);

					// File file = new File("c:/windows");
					sun.awt.shell.ShellFolder sf;
					try {
						sf = sun.awt.shell.ShellFolder.getShellFolder(Paths.get(value)
							.toFile());
						Icon icon = new ImageIcon(sf.getIcon(true));

						Image image = ((ImageIcon) icon).getImage();

						// cast it to bufferedimage
						BufferedImage buffered = (BufferedImage) image;

						// try {
						// // save to file
						// //File outputfile = new File("d://saved.png");
						// //ImageIO.write(buffered, "png", outputfile);
						// } catch (IOException e) {
						// e.printStackTrace();
						// }
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
	}

	public static void showCell(int row, int column, JTable table) {

		Rectangle rect = table.getCellRect(row, column, true);
		table.scrollRectToVisible(rect);
		table.clearSelection();
		table.setRowSelectionInterval(row, row);
		// ((AbstractTableModel)table.getModel()).fireTableDataChanged();
		// table.getModel().fireTableDataChanged(); // notify the model
	}

	public static void scrolltable(JTable table) {

		table.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				// int lastIndex =table.
				// table.changeSelection(lastIndex, 0,false,false);
			}
		});
	}

	public static String[] fileList(String directory) {

		List<String> fileNames = new ArrayList<>();
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directory))) {

			boolean parentPath = false;
			if (!parentPath) {
				parentPath = true;

				Path p2 = Paths.get(directory); // new File(directory).toPath();
				// fileNames.add(p2.getParent() == null ? p2.getRoot().toString() : p2.getParent().toString());
				// fileNames.add(p2.getParent() == null ? p2.getRoot().toString() : "[..]");
			}
			directoryStream.forEach(path -> fileNames.add(path.toString()));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		Object[] arr = fileNames.toArray();
		return Arrays.copyOf(arr, arr.length, String[].class);

		// Files.walkFileTree(Paths.get(directory), new SimpleFileVisitor<Path>() {
		// @Override
		// public FileVisitResult preVisitDirectory(Path file,
		// BasicFileAttributes attrs) {
		//
		// DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
		// @Override
		// public boolean accept(Path entry) throws IOException {
		// BasicFileAttributes attr = Files.readAttributes(entry,
		// BasicFileAttributes.class);
		//
		// return (attr.isDirectory());
		// }
		// };
		// try (DirectoryStream<Path> stream = Files.newDirectoryStream(
		// file, filter)) {
		// for (Path path : stream) {
		// System.out.println(path.toString());
		// }
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// }
		// return FileVisitResult.CONTINUE;
		//
		// }
		// });
	}

	public static void main(String[] args) throws IllegalAccessException {

		FileSystemView fsv = FileSystemView.getFileSystemView();

		File[] drives = File.listRoots();
		if ((drives != null) && (drives.length > 0))
			for (File aDrive : drives) {
				System.out.println("Drive Letter: " + aDrive);
				System.out.println("\tType: " + fsv.getSystemTypeDescription(aDrive));
				System.out.println("\tTotal space: " + aDrive.getTotalSpace());
				System.out.println("\tFree space: " + aDrive.getFreeSpace());
				System.out.println();
			}

		Path sourceFile = Paths.get("D:/kdpw.grafika/material-design-icons-master.zip");
		Path targetFile = Paths.get("D:/");
		Path relativePath = sourceFile.relativize(targetFile);

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					// FontUIResource font = new FontUIResource("Tahoma", Font.PLAIN, 12);
					// UIManager.put("Table.font", font);

					// UIManager.put("Table.foreground", Color.RED);
					break;
				}
		} catch (ClassNotFoundException | InstantiationException | javax.swing.UnsupportedLookAndFeelException ex) {

		}

		// Figure out what directory to display
		File dir;
		if (args.length > 0)
			dir = new File(args[0]);
		else
			// dir = new File(System.getProperty("user.home"));
			dir = new File("C:");

		// Create a TableModel object to represent the contents of the directory
		FileTableModel model = new FileTableModel(dir);

		// Display it all in a scrolling window and make the window appear
		// JFrame frame = new JFrame("FileTableDemo");
		JForm form = new JForm();
		JFrame frame = form.makeFrame("FileTableDemo");
		// Create a JTable and tell it to display our model
		FileTable table = new FileTable(model);
		table.setRowHeight(24);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		FileTableDemo.createKeybindings(table);
		table.setAutoCreateRowSorter(true);
		table.getColumnModel()
			.getColumn(0)
			.setMaxWidth(20);
		table.getColumnModel()
			.getColumn(2)
			.setWidth(10);
		table.clearSelection();
		table.setIntercellSpacing(new Dimension(0, 1));
		table.setSelectionBackground(Color.BLACK);
		table.setSelectionForeground(Color.WHITE);
		frame.getContentPane()
			.add(new JScrollPane(table));

		// table.setShowHorizontalLines(false);

		table.setShowVerticalLines(false);

		// table.setFocusable(true);
		table.getColumnModel()
			.getColumn(0)
			.setCellRenderer(new ImageRender());
		table.getColumnModel()
			.getColumn(1)
			.setCellRenderer(new FileRender());
		table.requestFocus(true);
		table.changeSelection(0, 0, false, false);

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				char key = (char) e.getID();
				int selectedColumn = table.getSelectedColumn();

				// Update info in table
				// for(int i = 0; i < model.getRowCount(); i++){
				// String value = (String)model.getValueAt(i, selectedColumn);
				// model.setValueAt(value + key, i, selectedColumn);
				// }
			}
		});

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			@Override
			public void paintComponent(Graphics g) {

				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				int w = this.getWidth();
				int h = this.getHeight();

				// Color color1 = getBackground();
				// Color color2 = color1.darker();

				// Paint a gradient from top to bottom
				// GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);

				// g2d.setPaint(gp);
				// g2d.fillRect(0, 0, w, h);
				// g2d.drawString("1212", 1, 9);
				super.paintComponent(g);

				g2d.dispose();
			}

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {

				Component l = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				// if (row%2 == 0){
				// l.setBackground(Color.WHITE);
				// }
				// else {
				// l.setBackground(Color.LIGHT_GRAY);
				// }

				// if (table.isCellSelected(row, col))
				// setForeground(Color.red);
				// else if (table.isRowSelected(row))
				// setForeground(Color.green);
				// else if (table.isColumnSelected(col))
				// setForeground(Color.blue);
				// else
				// setForeground(Color.black);

				return l;
			}
		});
		FileTableDemo.scrollToVisible(table, 0, 0);
		frame.setSize(1024, 860);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/**
 * The methods in this class allow the JTable component to get and display data about the files in a specified
 * directory. It represents a table with six columns: filename, size, modification date, plus three columns for flags:
 * directory, readable, writable.
 **/
class FileTableModel extends AbstractTableModel {

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
	protected String[] filenames;

	protected String[] columnNames =
			new String[]
			{ "", "name", "", "ext", "size", "last modified", "directory?", "readable?", "writable?", "md5" };

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
				Boolean.class,
				String.class };

	// This table model works for any one given directory
	public FileTableModel(File dir) {

		this.dir = dir;
		this.filenames = FileTableDemo.fileList(dir.getAbsolutePath());
	}

	// These are easy methods
	@Override
	public int getColumnCount() {

		return 10;
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
			try {
				return FileTableModel.getFileExtension(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case 4:
			DecimalFormat formatter = new DecimalFormat();
			formatter.applyPattern("#,##0.#");
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
		case 9:
			if ((!f.isFile()) || (f.getName()
				.startsWith("hiber")))
				return "";

			MessageDigest md = null;
			byte[] digest = null;
			// try {
			// //digest = MessageDigest.getInstance("MD5").digest(Files.readAllBytes(Paths.get(f.getAbsolutePath())));
			// // md = MessageDigest.getInstance("MD5");
			// // md.update(Files.readAllBytes(Paths.get(f.getAbsolutePath())));
			// // digest = md.digest();
			// } catch (NoSuchAlgorithmException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			return "";// new BigInteger(1, digest).toString(16);
		// return Arrays.toString(digest);
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

class FileRender extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {

		// Cells are by default rendered as a JLabel.
		JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		String myValue = String.valueOf(value);
		int index = myValue.lastIndexOf("\\");
		myValue = myValue.substring(index + 1);
		this.setText(myValue);
		return l;
	}
}

class ImageRender extends DefaultTableCellRenderer {

	public ImageRender() {

		// super();
	}

	/*
	 * @see TableCellRenderer#getTableCellRendererComponent(JTable, Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		sun.awt.shell.ShellFolder sf = null;
		Image image = null;

		// JComponent c =
		// (JComponent)
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		File ff = new File(table.getModel()
			.getValueAt(row, column + 1)
			.toString());

		Icon icon = null;
		try {

			// sf = sun.awt.shell.ShellFolder.getShellFolder(ff);
			icon = FileSystemView.getFileSystemView()
				.getSystemIcon(ff);
			// icon = new ImageIcon(sf.getIcon(false));
			if (icon != null) {
				image = ((ImageIcon) icon).getImage();
				this.setIcon(icon);
			} else {
				// image = (Image) UIManager.getIcon(sf.isDirectory() ? "FileView.directoryIcon" : "FileView.fileIcon");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// setText("<dir> " + String.valueOf(sf.getTotalSpace()));
		Border outside = new MatteBorder(1, 0, 1, 0, Color.BLUE);
		Border inside = new EmptyBorder(0, 1, 0, 1);
		Border highlight = new CompoundBorder(outside, inside);

		if (isSelected)
			this.setBorder(highlight);

		// setSize(table.getColumnModel().getColumn(column).getWidth() - 150,
		// getPreferredSize().height);

		// if (isSelected) {
		// setBackground(table.getSelectionBackground());
		// setForeground(table.getSelectionForeground());
		// } else {
		// setBackground(table.getBackground());
		// setForeground(table.getForeground());
		// }

		return this;
	}
}

class FileTable extends JTable {

	public FileTable(TableModel model) {

		super(model);
	}

	// ------------------------------------------------------------------------------------------------------------------

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

		{
			// TableColumn tableColumn = getColumnModel().getColumn(column);
			// int preferredWidth = tableColumn.getMinWidth();
			// int maxWidth = tableColumn.getMaxWidth();
			//
			// TableCellRenderer cellRenderer = getCellRenderer(row, column);
			// // Component c = prepareRenderer(cellRenderer, row, column);
			// int width = c.getPreferredSize().width + getIntercellSpacing().width;
			// preferredWidth = Math.max(preferredWidth, width);
			//
			// // We've exceeded the maximum width, no need to check other rows
			//
			// if (preferredWidth >= maxWidth) {
			// preferredWidth = maxWidth;
			// }
			//
			// tableColumn.setPreferredWidth(preferredWidth);
			JComponent c = (JComponent) super.prepareRenderer(renderer, row, column);

			Border outside = new MatteBorder(1, 0, 1, 0, Color.GREEN);
			Border inside = new EmptyBorder(0, 1, 0, 1);
			Border highlight = new CompoundBorder(outside, inside);

			if (this.isRowSelected(row))
				this.setBorder(highlight);

			Color alternateColor = new Color(252, 242, 206);
			Color whiteColor = Color.WHITE;
			if (!c.getBackground()
				.equals(this.getSelectionBackground())) {
				Color bg = ((row % 2) == 0 ? alternateColor
						: whiteColor);
				c.setBackground(bg);
				bg = null;
			}

			return c;
		}

		// Component component = super.prepareRenderer(renderer, row, column);
		// int rendererWidth = component.getPreferredSize().width;
		// TableColumn tableColumn = getColumnModel().getColumn(column);
		// tableColumn.setPreferredWidth(Math.min(rendererWidth + getIntercellSpacing().width,
		// tableColumn
		// .getPreferredWidth()));
		// return component;
	}

}
