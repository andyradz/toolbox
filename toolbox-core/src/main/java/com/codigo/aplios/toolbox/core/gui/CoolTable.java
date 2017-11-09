package com.codigo.aplios.toolbox.core.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * A cool table with dynamic frozen columns.
 *
 * @author Kurt Riede
 */
public class CoolTable extends JScrollPane {

	/**
	 *
	 */
	private static final long serialVersionUID = -1704127210927714225L;

	public static void main(String[] args) {

		final CoolTable coolTable = new CoolTable(new DefaultTableModel(20, 10), 2);
		JFrame frame = new JFrame("Cool Table Demo");
		frame.getContentPane()
			.setLayout(new GridLayout(2, 1));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton button2 = new JButton("Freeze 2 columns");
		JButton button4 = new JButton("Freeze 4 columns");
		JButton button6 = new JButton("Freeze 6 columns");
		button2.addActionListener(e -> coolTable.setFrozenColumns(2));
		button4.addActionListener(e -> coolTable.setFrozenColumns(4));
		button6.addActionListener(e -> coolTable.setFrozenColumns(6));
		buttonPanel.add(button2);
		buttonPanel.add(button4);
		buttonPanel.add(button6);
		frame.getContentPane()
			.add(buttonPanel);
		frame.getContentPane()
			.add(coolTable);
		frame.setSize(600, 300);
		Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height)
			frameSize.height = screenSize.height;
		if (frameSize.width > screenSize.width)
			frameSize.width = screenSize.width;
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private final JTable lockedTable;

	private final JTable scrollTable;

	int frozenColumns = 0;

	private final JScrollPaneAdjuster adjuster;

	public CoolTable(TableModel model, int numFrozenColumns) {

		super();
		this.adjuster = new JScrollPaneAdjuster(this);
		this.frozenColumns = numFrozenColumns;
		// create the two tables
		this.lockedTable = new JTable(model);
		this.lockedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.scrollTable = new JTable(model);
		this.scrollTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.setViewportView(this.scrollTable);

		// Put the locked-column table in the row header
		JViewport viewport = new JViewport();
		viewport.setBackground(Color.white);
		viewport.setView(this.lockedTable);
		this.setRowHeader(viewport);

		// Put the header of the locked-column table in the top left corner
		// of the scoll pane
		JTableHeader lockedHeader = this.lockedTable.getTableHeader();
		lockedHeader.setReorderingAllowed(false);
		lockedHeader.setResizingAllowed(false);
		this.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, lockedHeader);

		this.scrollTable.getSelectionModel()
			.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.lockedTable.setSelectionModel(this.scrollTable.getSelectionModel());
		this.lockedTable.getTableHeader()
			.setReorderingAllowed(false);
		this.lockedTable.getTableHeader()
			.setResizingAllowed(false);
		this.lockedTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		this.scrollTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		// Remove the fixed columns from the main table
		TableColumnModel scrollColumnModel = this.scrollTable.getColumnModel();
		for (int i = 0; i < this.frozenColumns; i++)
			scrollColumnModel.removeColumn(scrollColumnModel.getColumn(0));
		// Remove the non-fixed columns from the fixed table
		TableColumnModel lockedColumnModel = this.lockedTable.getColumnModel();
		while (this.lockedTable.getColumnCount() > this.frozenColumns)
			lockedColumnModel.removeColumn(lockedColumnModel.getColumn(this.frozenColumns));
		// Add the fixed table to the scroll pane
		this.lockedTable.setPreferredScrollableViewportSize(this.lockedTable.getPreferredSize());

		// set a new action for the tab key
		// todo search actions by action name (not by KeyStroke)
		final Action lockedTableNextColumnCellAction = this.getAction(this.lockedTable, KeyEvent.VK_TAB, 0);
		final Action scrollTableNextColumnCellAction = this.getAction(this.scrollTable, KeyEvent.VK_TAB, 0);
		final Action lockedTablePrevColumnCellAction =
				this.getAction(this.lockedTable, KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK);
		final Action scrollTablePrevColumnCellAction =
				this.getAction(this.scrollTable, KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK);

		this.setAction(this.lockedTable,
				"selectNextColumn",
				new LockedTableSelectNextColumnCellAction(lockedTableNextColumnCellAction));
		this.setAction(this.scrollTable,
				"selectNextColumn",
				new ScrollTableSelectNextColumnCellAction(scrollTableNextColumnCellAction));
		this.setAction(this.lockedTable,
				"selectPreviousColumn",
				new LockedTableSelectPreviousColumnCellAction(lockedTablePrevColumnCellAction));
		this.setAction(this.scrollTable,
				"selectPreviousColumn",
				new ScrollTableSelectPreviousColumnCellAction(scrollTablePrevColumnCellAction));

		this.setAction(this.lockedTable,
				"selectNextColumnCell",
				new LockedTableSelectNextColumnCellAction(lockedTableNextColumnCellAction));
		this.setAction(this.scrollTable,
				"selectNextColumnCell",
				new ScrollTableSelectNextColumnCellAction(scrollTableNextColumnCellAction));
		this.setAction(this.lockedTable,
				"selectPreviousColumnCell",
				new LockedTableSelectPreviousColumnCellAction(lockedTablePrevColumnCellAction));
		this.setAction(this.scrollTable,
				"selectPreviousColumnCell",
				new ScrollTableSelectPreviousColumnCellAction(scrollTablePrevColumnCellAction));

		this.setAction(this.scrollTable, "selectFirstColumn", new ScrollableSelectFirstColumnCellAction());
		this.setAction(this.lockedTable, "selectLastColumn", new LockedTableSelectLastColumnCellAction());
	}

	private void setAction(JComponent component, String name, Action action) {

		component.getActionMap()
			.put(name, action);
	}

	private void setAction(JComponent component, String name, int keyCode, int modifiers, Action action) {

		final int condition = JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
		final KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, modifiers);
		component.getInputMap(condition)
			.put(keyStroke, name);
		component.getActionMap()
			.put(name, action);
	}

	private Action getAction(JComponent component, int keyCode, int modifiers) {

		final int condition = JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
		final KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, modifiers);
		Object object = component.getInputMap(condition)
			.get(keyStroke);
		if (object == null) {
			if (component.getParent() instanceof JComponent)
				return this.getAction((JComponent) component.getParent(), keyCode, modifiers);
			else
				return null;
		} else
			return this.scrollTable.getActionMap()
				.get(object);
	}

	protected int nextRow(JTable table) {

		int row = table.getSelectedRow() + 1;
		if (row == table.getRowCount())
			row = 0;
		return row;
	}

	private int previousRow(JTable table) {

		int row = table.getSelectedRow() - 1;
		if (row == -1)
			row = table.getRowCount() - 1;
		return row;
	}

	public final int getFrozenColumns() {

		return this.frozenColumns;
	}

	public final void setFrozenColumns(final int numFrozenColumns) {

		this.rearrangeColumns(numFrozenColumns);
		this.frozenColumns = numFrozenColumns;
	}

	private void rearrangeColumns(final int numFrozenColumns) {

		TableColumnModel scrollColumnModel = this.scrollTable.getColumnModel();
		TableColumnModel lockedColumnModel = this.lockedTable.getColumnModel();
		if (this.frozenColumns < numFrozenColumns) {
			// move columns from scrollable to fixed table
			for (int i = this.frozenColumns; i < numFrozenColumns; i++) {
				TableColumn column = scrollColumnModel.getColumn(0);
				lockedColumnModel.addColumn(column);
				scrollColumnModel.removeColumn(column);
			}
			this.lockedTable.setPreferredScrollableViewportSize(this.lockedTable.getPreferredSize());
		} else if (this.frozenColumns > numFrozenColumns) {
			// move columns from fixed to scrollable table
			for (int i = numFrozenColumns; i < this.frozenColumns; i++) {
				TableColumn column = lockedColumnModel.getColumn(lockedColumnModel.getColumnCount() - 1);
				scrollColumnModel.addColumn(column);
				scrollColumnModel.moveColumn(scrollColumnModel.getColumnCount() - 1, 0);
				lockedColumnModel.removeColumn(column);
			}
			this.lockedTable.setPreferredScrollableViewportSize(this.lockedTable.getPreferredSize());
		}
	}

	public class JScrollPaneAdjuster implements PropertyChangeListener, Serializable {

		private JScrollPane pane;

		private transient Adjuster x, y;

		public JScrollPaneAdjuster(JScrollPane pane) {

			this.pane = pane;
			this.x = new Adjuster(pane.getViewport(), pane.getColumnHeader(), Adjuster.X);
			this.y = new Adjuster(pane.getViewport(), pane.getRowHeader(), Adjuster.Y);
			pane.addPropertyChangeListener(this);
		}

		public void dispose() {

			this.x.dispose();
			this.y.dispose();
			this.pane.removePropertyChangeListener(this);
			this.pane = null;
		}

		@Override
		public void propertyChange(PropertyChangeEvent e) {

			String name = e.getPropertyName();
			if (name.equals("viewport")) {
				this.x.setViewport((JViewport) e.getNewValue());
				this.y.setViewport((JViewport) e.getNewValue());
			} else if (name.equals("rowHeader"))
				this.y.setHeader((JViewport) e.getNewValue());
			else if (name.equals("columnHeader"))
				this.x.setHeader((JViewport) e.getNewValue());
		}

		private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

			in.defaultReadObject();
			this.x = new Adjuster(this.pane.getViewport(), this.pane.getColumnHeader(), Adjuster.X);
			this.y = new Adjuster(this.pane.getViewport(), this.pane.getRowHeader(), Adjuster.Y);
		}

		private class Adjuster implements ChangeListener, Runnable {

			public static final int X = 1, Y = 2;
			private JViewport viewport, header;
			private int type;

			public Adjuster(JViewport viewport, JViewport header, int type) {

				this.viewport = viewport;
				this.header = header;
				this.type = type;
				if (header != null)
					header.addChangeListener(this);
			}

			public void setViewport(JViewport newViewport) {

				this.viewport = newViewport;
			}

			public void setHeader(JViewport newHeader) {

				if (this.header != null)
					this.header.removeChangeListener(this);
				this.header = newHeader;
				if (this.header != null)
					this.header.addChangeListener(this);
			}

			@Override
			public void stateChanged(ChangeEvent e) {

				if ((this.viewport == null) || (this.header == null))
					return;
				if (this.type == Adjuster.X) {
					if (this.viewport.getViewPosition().x != this.header.getViewPosition().x)
						SwingUtilities.invokeLater(this);
				} else if (this.viewport.getViewPosition().y != this.header.getViewPosition().y)
					SwingUtilities.invokeLater(this);
			}

			@Override
			public void run() {

				if ((this.viewport == null) || (this.header == null))
					return;
				Point v = this.viewport.getViewPosition(), h = this.header.getViewPosition();
				if (this.type == Adjuster.X) {
					if (v.x != h.x)
						this.viewport.setViewPosition(new Point(h.x, v.y));
				} else if (v.y != h.y)
					this.viewport.setViewPosition(new Point(v.x, h.y));
			}

			public void dispose() {

				if (this.header != null)
					this.header.removeChangeListener(this);
				this.viewport = this.header = null;
			}
		}
	}

	private final class LockedTableSelectLastColumnCellAction extends AbstractAction {
		private LockedTableSelectLastColumnCellAction() {

			super();
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == CoolTable.this.lockedTable)
				CoolTable.this.lockedTable.transferFocus();
			CoolTable.this.scrollTable.changeSelection(CoolTable.this.scrollTable.getSelectedRow(),
					CoolTable.this.scrollTable.getColumnCount() - 1,
					false,
					false);
		}
	}

	private final class ScrollableSelectFirstColumnCellAction extends AbstractAction {
		private ScrollableSelectFirstColumnCellAction() {

			super();
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == CoolTable.this.scrollTable)
				CoolTable.this.scrollTable.transferFocusBackward();
			CoolTable.this.lockedTable.changeSelection(CoolTable.this.lockedTable.getSelectedRow(), 0, false, false);
		}
	}

	private final class LockedTableSelectNextColumnCellAction extends AbstractAction {
		private final Action lockedTableNextColumnCellAction;

		private LockedTableSelectNextColumnCellAction(Action lockedTableNextColumnCellAction) {

			super();
			this.lockedTableNextColumnCellAction = lockedTableNextColumnCellAction;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (CoolTable.this.lockedTable.getSelectedColumn() == (CoolTable.this.lockedTable.getColumnCount() - 1)) {
				CoolTable.this.lockedTable.transferFocus();
				CoolTable.this.scrollTable
					.changeSelection(CoolTable.this.lockedTable.getSelectedRow(), 0, false, false);
			} else
				this.lockedTableNextColumnCellAction.actionPerformed(e);
		}
	}

	private final class ScrollTableSelectNextColumnCellAction extends AbstractAction {
		private final Action scrollTableNextColumnCellAction;

		private ScrollTableSelectNextColumnCellAction(Action scrollTableNextColumnCellAction) {

			super();
			this.scrollTableNextColumnCellAction = scrollTableNextColumnCellAction;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (CoolTable.this.scrollTable.getSelectedColumn() == (CoolTable.this.scrollTable.getColumnCount() - 1)) {
				CoolTable.this.scrollTable.transferFocusBackward();
				CoolTable.this.lockedTable
					.changeSelection(CoolTable.this.nextRow(CoolTable.this.scrollTable), 0, false, false);
				return;
			} else
				this.scrollTableNextColumnCellAction.actionPerformed(e);
		}
	}

	private final class ScrollTableSelectPreviousColumnCellAction extends AbstractAction {
		private final Action scrollTablePrevColumnCellAction;

		private ScrollTableSelectPreviousColumnCellAction(Action scrollTablePrevColumnCellAction) {

			super();
			this.scrollTablePrevColumnCellAction = scrollTablePrevColumnCellAction;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (CoolTable.this.scrollTable.getSelectedColumn() == 0) {
				CoolTable.this.scrollTable.transferFocusBackward();
				CoolTable.this.lockedTable.changeSelection(CoolTable.this.scrollTable.getSelectedRow(),
						CoolTable.this.lockedTable.getColumnCount() - 1,
						false,
						false);
				return;
			} else
				this.scrollTablePrevColumnCellAction.actionPerformed(e);
		}
	}

	private final class LockedTableSelectPreviousColumnCellAction extends AbstractAction {
		private final Action lockedTablePrevColumnCellAction;

		private LockedTableSelectPreviousColumnCellAction(Action lockedTablePrevColumnCellAction) {

			super();
			this.lockedTablePrevColumnCellAction = lockedTablePrevColumnCellAction;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (CoolTable.this.lockedTable.getSelectedColumn() == 0) {
				CoolTable.this.lockedTable.transferFocus();
				CoolTable.this.scrollTable.changeSelection(CoolTable.this.previousRow(CoolTable.this.scrollTable),
						CoolTable.this.scrollTable.getColumnCount() - 1,
						false,
						false);
				return;
			} else
				this.lockedTablePrevColumnCellAction.actionPerformed(e);
		}
	}
}
