package com.codigo.aplios.toolbox.core.gui.gridview;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

// Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html

// http://www.java2s.com/Code/Java/Swing-Components/MultiSpanCellTableExample.htm
// http://www.java2s.com/Code/Java/Swing-Components/HideColumnTableExample.htm
// http://www.java2s.com/Code/Java/Swing-Components/JSortTable.htm

/**
 * @version 1.0 11/26/98
 */
@SuppressWarnings("serial")
public class MultipleTableCell extends JFrame {

	public MultipleTableCell() {

		super("Multi-Span Cell Example");

		AttributiveCellTableModel ml = new AttributiveCellTableModel(10, 6);
		/*
		 * AttributiveCellTableModel ml = new AttributiveCellTableModel(10,6) { public Object getValueAt(int row, int
		 * col) { return "" + row + ","+ col; } };
		 */
		final CellSpan cellAtt = (CellSpan) ml.getCellAttribute();
		final MultiSpanCellTable table = new MultiSpanCellTable(ml);
		JScrollPane scroll = new JScrollPane(table);

		JButton b_one = new JButton("Combine");
		b_one.addActionListener(e -> {

			int[] columns = table.getSelectedColumns();
			int[] rows = table.getSelectedRows();
			cellAtt.combine(rows, columns);
			table.clearSelection();
			table.revalidate();
			table.repaint();
		});
		JButton b_split = new JButton("Split");
		b_split.addActionListener(e -> {

			int column = table.getSelectedColumn();
			int row = table.getSelectedRow();
			cellAtt.split(row, column);
			table.clearSelection();
			table.revalidate();
			table.repaint();
		});
		JPanel p_buttons = new JPanel();
		p_buttons.setLayout(new GridLayout(2, 1));
		p_buttons.add(b_one);
		p_buttons.add(b_split);

		Box box = new Box(BoxLayout.X_AXIS);
		box.add(scroll);
		box.add(new JSeparator(SwingConstants.HORIZONTAL));
		box.add(p_buttons);
		this.getContentPane()
			.add(box);
		this.setSize(400, 200);
		this.setVisible(true);
	}

	public static void main(String[] args) {

		MultipleTableCell frame = new MultipleTableCell();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}
		});
	}
}

/**
 * @version 1.0 11/22/98
 */

class AttributiveCellTableModel extends DefaultTableModel {

	protected CellAttribute cellAtt;

	public AttributiveCellTableModel() {

		this((Vector) null, 0);
	}

	public AttributiveCellTableModel(int numRows, int numColumns) {

		Vector names = new Vector(numColumns);
		names.setSize(numColumns);
		this.setColumnIdentifiers(names);
		this.dataVector = new Vector();
		this.setNumRows(numRows);
		this.cellAtt = new DefaultCellAttribute(numRows, numColumns);
	}

	public AttributiveCellTableModel(Vector columnNames, int numRows) {

		this.setColumnIdentifiers(columnNames);
		this.dataVector = new Vector();
		this.setNumRows(numRows);
		this.cellAtt = new DefaultCellAttribute(numRows, columnNames.size());
	}

	public AttributiveCellTableModel(Object[] columnNames, int numRows) {

		this(DefaultTableModel.convertToVector(columnNames), numRows);
	}

	public AttributiveCellTableModel(Vector data, Vector columnNames) {

		this.setDataVector(data, columnNames);
	}

	public AttributiveCellTableModel(Object[][] data, Object[] columnNames) {

		this.setDataVector(data, columnNames);
	}

	@Override
	public void setDataVector(Vector newData, Vector columnNames) {

		if (newData == null)
			throw new IllegalArgumentException("setDataVector() - Null parameter");
		this.dataVector = new Vector(0);
		this.setColumnIdentifiers(columnNames);
		this.dataVector = newData;

		//
		this.cellAtt = new DefaultCellAttribute(this.dataVector.size(), this.columnIdentifiers.size());

		this.newRowsAdded(new TableModelEvent(this,
				0,
				this.getRowCount() - 1,
				TableModelEvent.ALL_COLUMNS,
				TableModelEvent.INSERT));
	}

	@Override
	public void addColumn(Object columnName, Vector columnData) {

		if (columnName == null)
			throw new IllegalArgumentException("addColumn() - null parameter");
		this.columnIdentifiers.addElement(columnName);
		int index = 0;
		Enumeration eeration = this.dataVector.elements();
		while (eeration.hasMoreElements()) {
			Object value;
			if ((columnData != null) && (index < columnData.size()))
				value = columnData.elementAt(index);
			else
				value = null;
			((Vector) eeration.nextElement()).addElement(value);
			index++;
		}

		//
		this.cellAtt.addColumn();

		this.fireTableStructureChanged();
	}

	@Override
	public void addRow(Vector rowData) {

		Vector newData = null;
		if (rowData == null)
			newData = new Vector(this.getColumnCount());
		else
			rowData.setSize(this.getColumnCount());
		this.dataVector.addElement(newData);

		//
		this.cellAtt.addRow();

		this.newRowsAdded(new TableModelEvent(this,
				this.getRowCount() - 1,
				this.getRowCount() - 1,
				TableModelEvent.ALL_COLUMNS,
				TableModelEvent.INSERT));
	}

	@Override
	public void insertRow(int row, Vector rowData) {

		if (rowData == null)
			rowData = new Vector(this.getColumnCount());
		else
			rowData.setSize(this.getColumnCount());

		this.dataVector.insertElementAt(rowData, row);

		//
		this.cellAtt.insertRow(row);

		this.newRowsAdded(new TableModelEvent(this, row, row, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
	}

	public CellAttribute getCellAttribute() {

		return this.cellAtt;
	}

	public void setCellAttribute(CellAttribute newCellAtt) {

		int numColumns = this.getColumnCount();
		int numRows = this.getRowCount();
		if ((newCellAtt.getSize().width != numColumns) || (newCellAtt.getSize().height != numRows))
			newCellAtt.setSize(new Dimension(numRows, numColumns));
		this.cellAtt = newCellAtt;
		this.fireTableDataChanged();
	}

	/*
	 * public void changeCellAttribute(int row, int column, Object command) { cellAtt.changeAttribute(row, column,
	 * command); }
	 *
	 * public void changeCellAttribute(int[] rows, int[] columns, Object command) { cellAtt.changeAttribute(rows,
	 * columns, command); }
	 */

}

/**
 * @version 1.0 11/22/98
 */

class DefaultCellAttribute
		// implements CellAttribute ,CellSpan {
		implements CellAttribute, CellSpan, ColoredCell, CellFont {

	//
	// !!!! CAUTION !!!!!
	// these values must be synchronized to Table data
	//
	protected int rowSize;
	protected int columnSize;
	protected int[][][] span; // CellSpan
	protected Color[][] foreground; // ColoredCell
	protected Color[][] background; //
	protected Font[][] font; // CellFont

	public DefaultCellAttribute() {

		this(1, 1);
	}

	public DefaultCellAttribute(int numRows, int numColumns) {

		this.setSize(new Dimension(numColumns, numRows));
	}

	protected void initValue() {

		for (int i = 0; i < this.span.length; i++)
			for (int j = 0; j < this.span[i].length; j++) {
				this.span[i][j][CellSpan.COLUMN] = 1;
				this.span[i][j][CellSpan.ROW] = 1;
			}
	}

	//
	// CellSpan
	//
	@Override
	public int[] getSpan(int row, int column) {

		if (this.isOutOfBounds(row, column)) {
			int[] ret_code = { 1, 1 };
			return ret_code;
		}
		return this.span[row][column];
	}

	@Override
	public void setSpan(int[] span, int row, int column) {

		if (this.isOutOfBounds(row, column))
			return;
		this.span[row][column] = span;
	}

	@Override
	public boolean isVisible(int row, int column) {

		if (this.isOutOfBounds(row, column))
			return false;
		if ((this.span[row][column][CellSpan.COLUMN] < 1) || (this.span[row][column][CellSpan.ROW] < 1))
			return false;
		return true;
	}

	@Override
	public void combine(int[] rows, int[] columns) {

		if (this.isOutOfBounds(rows, columns))
			return;
		int rowSpan = rows.length;
		int columnSpan = columns.length;
		int startRow = rows[0];
		int startColumn = columns[0];
		for (int i = 0; i < rowSpan; i++)
			for (int j = 0; j < columnSpan; j++)
				if ((this.span[startRow + i][startColumn + j][CellSpan.COLUMN] != 1)
						|| (this.span[startRow + i][startColumn + j][CellSpan.ROW] != 1))
					// System.out.println("can't combine");
					return;
		for (int i = 0, ii = 0; i < rowSpan; i++, ii--)
			for (int j = 0, jj = 0; j < columnSpan; j++, jj--) {
				this.span[startRow + i][startColumn + j][CellSpan.COLUMN] = jj;
				this.span[startRow + i][startColumn + j][CellSpan.ROW] = ii;
				// System.out.println("r " +ii +" c " +jj);
			}
		this.span[startRow][startColumn][CellSpan.COLUMN] = columnSpan;
		this.span[startRow][startColumn][CellSpan.ROW] = rowSpan;

	}

	@Override
	public void split(int row, int column) {

		if (this.isOutOfBounds(row, column))
			return;
		int columnSpan = this.span[row][column][CellSpan.COLUMN];
		int rowSpan = this.span[row][column][CellSpan.ROW];
		for (int i = 0; i < rowSpan; i++)
			for (int j = 0; j < columnSpan; j++) {
				this.span[row + i][column + j][CellSpan.COLUMN] = 1;
				this.span[row + i][column + j][CellSpan.ROW] = 1;
			}
	}

	//
	// ColoredCell
	//
	@Override
	public Color getForeground(int row, int column) {

		if (this.isOutOfBounds(row, column))
			return null;
		return this.foreground[row][column];
	}

	@Override
	public void setForeground(Color color, int row, int column) {

		if (this.isOutOfBounds(row, column))
			return;
		this.foreground[row][column] = color;
	}

	@Override
	public void setForeground(Color color, int[] rows, int[] columns) {

		if (this.isOutOfBounds(rows, columns))
			return;
		this.setValues(this.foreground, color, rows, columns);
	}

	@Override
	public Color getBackground(int row, int column) {

		if (this.isOutOfBounds(row, column))
			return null;
		return this.background[row][column];
	}

	@Override
	public void setBackground(Color color, int row, int column) {

		if (this.isOutOfBounds(row, column))
			return;
		this.background[row][column] = color;
	}

	@Override
	public void setBackground(Color color, int[] rows, int[] columns) {

		if (this.isOutOfBounds(rows, columns))
			return;
		this.setValues(this.background, color, rows, columns);
	}
	//

	//
	// CellFont
	//
	@Override
	public Font getFont(int row, int column) {

		if (this.isOutOfBounds(row, column))
			return null;
		return this.font[row][column];
	}

	@Override
	public void setFont(Font font, int row, int column) {

		if (this.isOutOfBounds(row, column))
			return;
		this.font[row][column] = font;
	}

	@Override
	public void setFont(Font font, int[] rows, int[] columns) {

		if (this.isOutOfBounds(rows, columns))
			return;
		this.setValues(this.font, font, rows, columns);
	}
	//

	//
	// CellAttribute
	//
	@Override
	public void addColumn() {

		int[][][] oldSpan = this.span;
		int numRows = oldSpan.length;
		int numColumns = oldSpan[0].length;
		this.span = new int[numRows][numColumns + 1][2];
		System.arraycopy(oldSpan, 0, this.span, 0, numRows);
		for (int i = 0; i < numRows; i++) {
			this.span[i][numColumns][CellSpan.COLUMN] = 1;
			this.span[i][numColumns][CellSpan.ROW] = 1;
		}
	}

	@Override
	public void addRow() {

		int[][][] oldSpan = this.span;
		int numRows = oldSpan.length;
		int numColumns = oldSpan[0].length;
		this.span = new int[numRows + 1][numColumns][2];
		System.arraycopy(oldSpan, 0, this.span, 0, numRows);
		for (int i = 0; i < numColumns; i++) {
			this.span[numRows][i][CellSpan.COLUMN] = 1;
			this.span[numRows][i][CellSpan.ROW] = 1;
		}
	}

	@Override
	public void insertRow(int row) {

		int[][][] oldSpan = this.span;
		int numRows = oldSpan.length;
		int numColumns = oldSpan[0].length;
		this.span = new int[numRows + 1][numColumns][2];
		if (0 < row)
			System.arraycopy(oldSpan, 0, this.span, 0, row - 1);
		System.arraycopy(oldSpan, 0, this.span, row, numRows - row);
		for (int i = 0; i < numColumns; i++) {
			this.span[row][i][CellSpan.COLUMN] = 1;
			this.span[row][i][CellSpan.ROW] = 1;
		}
	}

	@Override
	public Dimension getSize() {

		return new Dimension(this.rowSize, this.columnSize);
	}

	@Override
	public void setSize(Dimension size) {

		this.columnSize = size.width;
		this.rowSize = size.height;
		this.span = new int[this.rowSize][this.columnSize][2]; // 2: COLUMN,ROW
		this.foreground = new Color[this.rowSize][this.columnSize];
		this.background = new Color[this.rowSize][this.columnSize];
		this.font = new Font[this.rowSize][this.columnSize];
		this.initValue();
	}

	/*
	 * public void changeAttribute(int row, int column, Object command) { }
	 *
	 * public void changeAttribute(int[] rows, int[] columns, Object command) { }
	 */

	protected boolean isOutOfBounds(int row, int column) {

		if ((row < 0) || (this.rowSize <= row) || (column < 0) || (this.columnSize <= column))
			return true;
		return false;
	}

	protected boolean isOutOfBounds(int[] rows, int[] columns) {

		for (int row2 : rows)
			if ((row2 < 0) || (this.rowSize <= row2))
				return true;
		for (int column2 : columns)
			if ((column2 < 0) || (this.columnSize <= column2))
				return true;
		return false;
	}

	protected void setValues(Object[][] target, Object value, int[] rows, int[] columns) {

		for (int row2 : rows) {
			int row = row2;
			for (int column2 : columns) {
				int column = column2;
				target[row][column] = value;
			}
		}
	}
}
/*
 * (swing1.1beta3)
 *
 */

/**
 * @version 1.0 11/22/98
 */

interface CellAttribute {

	public void addColumn();

	public void addRow();

	public void insertRow(int row);

	public Dimension getSize();

	public void setSize(Dimension size);

}
/*
 * (swing1.1beta3)
 *
 */

/**
 * @version 1.0 11/22/98
 */

interface CellSpan {
	public final int ROW = 0;
	public final int COLUMN = 1;

	public int[] getSpan(int row, int column);

	public void setSpan(int[] span, int row, int column);

	public boolean isVisible(int row, int column);

	public void combine(int[] rows, int[] columns);

	public void split(int row, int column);

}
/*
 * (swing1.1beta3)
 *
 */

/**
 * @version 1.0 11/26/98
 */

class MultiSpanCellTable extends JTable {

	public MultiSpanCellTable(TableModel model) {

		super(model);
		this.setUI(new MultiSpanCellTableUI());
		this.getTableHeader()
			.setReorderingAllowed(false);
		this.setCellSelectionEnabled(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	}

	@Override
	public Rectangle getCellRect(int row, int column, boolean includeSpacing) {

		Rectangle sRect = super.getCellRect(row, column, includeSpacing);
		if ((row < 0) || (column < 0) || (this.getRowCount() <= row) || (this.getColumnCount() <= column))
			return sRect;
		CellSpan cellAtt = (CellSpan) ((AttributiveCellTableModel) this.getModel()).getCellAttribute();
		if (!cellAtt.isVisible(row, column)) {
			int temp_row = row;
			int temp_column = column;
			row += cellAtt.getSpan(temp_row, temp_column)[CellSpan.ROW];
			column += cellAtt.getSpan(temp_row, temp_column)[CellSpan.COLUMN];
		}
		int[] n = cellAtt.getSpan(row, column);

		int index = 0;
		int columnMargin = this.getColumnModel()
			.getColumnMargin();
		Rectangle cellFrame = new Rectangle();
		int aCellHeight = this.rowHeight + this.rowMargin;
		cellFrame.y = row * aCellHeight;
		cellFrame.height = n[CellSpan.ROW] * aCellHeight;

		Enumeration eeration = this.getColumnModel()
			.getColumns();
		while (eeration.hasMoreElements()) {
			TableColumn aColumn = (TableColumn) eeration.nextElement();
			cellFrame.width = aColumn.getWidth() + columnMargin;
			if (index == column)
				break;
			cellFrame.x += cellFrame.width;
			index++;
		}
		for (int i = 0; i < (n[CellSpan.COLUMN] - 1); i++) {
			TableColumn aColumn = (TableColumn) eeration.nextElement();
			cellFrame.width += aColumn.getWidth() + columnMargin;
		}

		if (!includeSpacing) {
			Dimension spacing = this.getIntercellSpacing();
			cellFrame.setBounds(cellFrame.x + (spacing.width / 2),
					cellFrame.y + (spacing.height / 2),
					cellFrame.width - spacing.width,
					cellFrame.height - spacing.height);
		}
		return cellFrame;
	}

	private int[] rowColumnAtPoint(Point point) {

		int[] retValue = { -1, -1 };
		int row = point.y / (this.rowHeight + this.rowMargin);
		if ((row < 0) || (this.getRowCount() <= row))
			return retValue;
		int column = this.getColumnModel()
			.getColumnIndexAtX(point.x);

		CellSpan cellAtt = (CellSpan) ((AttributiveCellTableModel) this.getModel()).getCellAttribute();

		if (cellAtt.isVisible(row, column)) {
			retValue[CellSpan.COLUMN] = column;
			retValue[CellSpan.ROW] = row;
			return retValue;
		}
		retValue[CellSpan.COLUMN] = column + cellAtt.getSpan(row, column)[CellSpan.COLUMN];
		retValue[CellSpan.ROW] = row + cellAtt.getSpan(row, column)[CellSpan.ROW];
		return retValue;
	}

	@Override
	public int rowAtPoint(Point point) {

		return this.rowColumnAtPoint(point)[CellSpan.ROW];
	}

	@Override
	public int columnAtPoint(Point point) {

		return this.rowColumnAtPoint(point)[CellSpan.COLUMN];
	}

	@Override
	public void columnSelectionChanged(ListSelectionEvent e) {

		this.repaint();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

		int firstIndex = e.getFirstIndex();
		int lastIndex = e.getLastIndex();
		if ((firstIndex == -1) && (lastIndex == -1))
			this.repaint();
		Rectangle dirtyRegion = this.getCellRect(firstIndex, 0, false);
		int numCoumns = this.getColumnCount();
		int index = firstIndex;
		for (int i = 0; i < numCoumns; i++)
			dirtyRegion.add(this.getCellRect(index, i, false));
		index = lastIndex;
		for (int i = 0; i < numCoumns; i++)
			dirtyRegion.add(this.getCellRect(index, i, false));
		this.repaint(dirtyRegion.x, dirtyRegion.y, dirtyRegion.width, dirtyRegion.height);
	}

}

/**
 * @version 1.0 11/26/98
 */

class MultiSpanCellTableUI extends BasicTableUI {

	@Override
	public void paint(Graphics g, JComponent c) {

		Rectangle oldClipBounds = g.getClipBounds();
		Rectangle clipBounds = new Rectangle(oldClipBounds);
		int tableWidth = this.table.getColumnModel()
			.getTotalColumnWidth();
		clipBounds.width = Math.min(clipBounds.width, tableWidth);
		g.setClip(clipBounds);

		int firstIndex = this.table.rowAtPoint(new Point(0, clipBounds.y));
		int lastIndex = this.table.getRowCount() - 1;

		Rectangle rowRect = new Rectangle(0, 0, tableWidth, this.table.getRowHeight() + this.table.getRowMargin());
		rowRect.y = firstIndex * rowRect.height;

		for (int index = firstIndex; index <= lastIndex; index++) {
			if (rowRect.intersects(clipBounds))
				// System.out.println(); // debug
				// System.out.print("" + index +": "); // row
				this.paintRow(g, index);
			rowRect.y += rowRect.height;
		}
		g.setClip(oldClipBounds);
	}

	private void paintRow(Graphics g, int row) {

		Rectangle rect = g.getClipBounds();
		boolean drawn = false;

		AttributiveCellTableModel tableModel = (AttributiveCellTableModel) this.table.getModel();
		CellSpan cellAtt = (CellSpan) tableModel.getCellAttribute();
		int numColumns = this.table.getColumnCount();

		for (int column = 0; column < numColumns; column++) {
			Rectangle cellRect = this.table.getCellRect(row, column, true);
			int cellRow, cellColumn;
			if (cellAtt.isVisible(row, column)) {
				cellRow = row;
				cellColumn = column;
				// System.out.print(" "+column+" "); // debug
			} else {
				cellRow = row + cellAtt.getSpan(row, column)[CellSpan.ROW];
				cellColumn = column + cellAtt.getSpan(row, column)[CellSpan.COLUMN];
				// System.out.print(" ("+column+")"); // debug
			}
			if (cellRect.intersects(rect)) {
				drawn = true;
				this.paintCell(g, cellRect, cellRow, cellColumn);
			} else if (drawn)
				break;
		}

	}

	private void paintCell(Graphics g, Rectangle cellRect, int row, int column) {

		int spacingHeight = this.table.getRowMargin();
		int spacingWidth = this.table.getColumnModel()
			.getColumnMargin();

		Color c = g.getColor();
		g.setColor(this.table.getGridColor());
		g.drawRect(cellRect.x, cellRect.y, cellRect.width - 1, cellRect.height - 1);
		g.setColor(c);

		cellRect.setBounds(cellRect.x + (spacingWidth / 2),
				cellRect.y + (spacingHeight / 2),
				cellRect.width - spacingWidth,
				cellRect.height - spacingHeight);

		if (this.table.isEditing() && (this.table.getEditingRow() == row)
				&& (this.table.getEditingColumn() == column)) {
			Component component = this.table.getEditorComponent();
			component.setBounds(cellRect);
			component.validate();
		} else {
			TableCellRenderer renderer = this.table.getCellRenderer(row, column);
			Component component = this.table.prepareRenderer(renderer, row, column);

			if (component.getParent() == null)
				this.rendererPane.add(component);
			this.rendererPane.paintComponent(g,
					component,
					this.table,
					cellRect.x,
					cellRect.y,
					cellRect.width,
					cellRect.height,
					true);
		}
	}
}

interface CellFont {

	public Font getFont(int row, int column);

	public void setFont(Font font, int row, int column);

	public void setFont(Font font, int[] rows, int[] columns);

}

interface ColoredCell {

	public Color getForeground(int row, int column);

	public void setForeground(Color color, int row, int column);

	public void setForeground(Color color, int[] rows, int[] columns);

	public Color getBackground(int row, int column);

	public void setBackground(Color color, int row, int column);

	public void setBackground(Color color, int[] rows, int[] columns);

}