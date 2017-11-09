package com.codigo.aplios.toolbox.core.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.ref.WeakReference;
import java.util.Arrays;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

// @author Santhosh Kumar T - santhosh@in.fiorano.com
public class TableColumnChooser extends MouseAdapter implements ActionListener {

	public static void main(String[] args) {

		JTable table =
				new JTable(new DefaultTableModel(new String[]
				{ "Name", "Type", "Modified", "Size", "Ratio", "Packed", "CRC", "Attributes", "Path" }, 10));

		// hide some columns initially
		TableColumnChooser.hideColumns(table.getColumnModel(), new int[] { 2, 4, 5, 6, 7 });

		// don't allow user to hide "Name" and "Path" columns
		table.putClientProperty(TableColumnChooser.FIXED_COLUMNS, new int[] { 0, 8 });

		TableColumnChooser.install(table);

		JFrame frame = new JFrame("Fixed Column Table");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(table, BorderLayout.CENTER);
		frame.setSize(300, 150);
		frame.setVisible(true);

	}

	// client property used to specify fixed columns. value is of type int[] which is sorted
	// fixed column = columns that can't be toggled
	public static final String FIXED_COLUMNS = "FixedColumns"; // NOI18N

	// client property used to pass column index to actionlistener
	private static final String COLUMN_INDEX = "ColumnIndex";

	// client property used to pass jtable to actionlistener
	private static final String JTABLE = "JTable";

	private boolean isVisibleColumn(TableColumnModel model, int modelCol) {

		for (int i = 0; i < model.getColumnCount(); i++)
			if (model.getColumn(i)
				.getModelIndex() == modelCol)
				return true;
		return false;
	}

	/*-----------------------------------[ MouseListener ]------------------------------------------*/

	@Override
	public void mouseReleased(MouseEvent me) {

		if (!SwingUtilities.isRightMouseButton(me))
			return;

		JTableHeader header = (JTableHeader) me.getComponent();
		TableModel tableModel = header.getTable()
			.getModel();
		TableColumnModel columnModel = header.getTable()
			.getColumnModel();

		int fixedColumns[] = (int[]) header.getTable()
			.getClientProperty(TableColumnChooser.FIXED_COLUMNS);
		if (fixedColumns == null)
			fixedColumns = new int[] { 0 };

		JPopupMenu popup = new JPopupMenu();
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			JCheckBoxMenuItem item = new JCheckBoxMenuItem(tableModel.getColumnName(i));
			item.setSelected(this.isVisibleColumn(columnModel, i));
			item.setEnabled(Arrays.binarySearch(fixedColumns, i) < 0);
			item.putClientProperty(TableColumnChooser.COLUMN_INDEX, new Integer(i));
			item.putClientProperty(TableColumnChooser.JTABLE, header.getTable());
			item.addActionListener(this);
			popup.add(item);
		}

		popup.show(header, me.getX(), me.getY());
	}

	/*------------------------------------[ ActionListener ]--------------------------------------*/

	@Override
	public void actionPerformed(ActionEvent ae) {

		JMenuItem item = (JMenuItem) ae.getSource();

		Integer columnIndex = (Integer) item.getClientProperty(TableColumnChooser.COLUMN_INDEX);
		JTable table = (JTable) item.getClientProperty(TableColumnChooser.JTABLE);
		TableColumnModel columnModel = table.getColumnModel();

		if (!item.isSelected())
			for (int i = 0; i < columnModel.getColumnCount(); i++) {
				TableColumn col = columnModel.getColumn(i);
				if (col.getModelIndex() == columnIndex.intValue()) {
					columnModel.removeColumn(col);
					return;
				}
			}
		else
			table.addColumn(new TableColumn(columnIndex.intValue()));
	}

	/*-------------------------------------------------[ Singleton ]---------------------------------------------------*/

	private static WeakReference ref = null; // favour gc

	private TableColumnChooser() {

	}

	private static TableColumnChooser getInstance() {

		if ((TableColumnChooser.ref == null) || (TableColumnChooser.ref.get() == null))
			TableColumnChooser.ref = new WeakReference(new TableColumnChooser());
		return (TableColumnChooser) TableColumnChooser.ref.get();
	}

	/*-------------------------------------------------[ Usage ]---------------------------------------------------*/

	public static void install(JTable table) {

		table.getTableHeader()
			.addMouseListener(TableColumnChooser.getInstance());
	}

	public static void uninstall(JTable table) {

		table.getTableHeader()
			.removeMouseListener(TableColumnChooser.getInstance());
	}

	/*-------------------------------------------------[ Utilities ]---------------------------------------------------*/

	public static void hideColumns(TableColumnModel columnModel, int modelColumnIndexes[]) {

		TableColumn column[] = new TableColumn[modelColumnIndexes.length];
		for (int i = 0, j = 0; i < columnModel.getColumnCount(); i++) {
			TableColumn col = columnModel.getColumn(i);
			if (col.getModelIndex() == modelColumnIndexes[j]) {
				column[j++] = col;
				if (j >= modelColumnIndexes.length)
					break;
			}
		}
		for (TableColumn element : column)
			columnModel.removeColumn(element);
	}
}