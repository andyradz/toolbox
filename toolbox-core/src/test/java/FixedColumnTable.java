
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/*
 * Prevent the specified number of columns from scrolling horizontally in the scroll pane. The table must already exist
 * in the scroll pane.
 *
 * The functionality is accomplished by creating a second JTable (fixed) that will share the TableModel and
 * SelectionModel of the main table. This table will be used as the row header of the scroll pane.
 *
 * The fixed table created can be accessed by using the getFixedTable() method. will be returned from this method. It
 * will allow you to:
 *
 * You can change the model of the main table and the change will be reflected in the fixed model. However, you cannot
 * change the structure of the model.
 */
public class FixedColumnTable implements ChangeListener, PropertyChangeListener {

	public static void main(String[] args) {

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Code");
		model.addColumn("Name");
		model.addColumn("Quantity");
		model.addColumn("Unit Price");
		model.addColumn("Price");
		model.addColumn("Price1");
		model.addColumn("Price2");
		model.addColumn("Price3");
		model.addColumn("Price4");
		model.addColumn("Price5");

		Object[] rowData = { "A1", "1", "12", 22, 55 };
		model.addRow(rowData);
		model.addRow(rowData);
		model.addRow(rowData);
		model.addRow(rowData);

		JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel()
			.getColumn(0)
			.setPreferredWidth(250);
		JScrollPane scrollPane = new JScrollPane(table);
		FixedColumnTable fct = new FixedColumnTable(1, scrollPane);

		JFrame frame = new JFrame("JTable Test Display");

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		panel.add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane()
			.add(scrollPane);

		frame.pack();
		frame.setVisible(true);

	}

	private JTable main;
	private JTable fixed;
	private JScrollPane scrollPane;

	/*
	 * Specify the number of columns to be fixed and the scroll pane containing the table.
	 */
	public FixedColumnTable(int fixedColumns, JScrollPane scrollPane) {

		this.scrollPane = scrollPane;

		this.main = ((JTable) scrollPane.getViewport()
			.getView());
		this.main.setAutoCreateColumnsFromModel(false);
		this.main.addPropertyChangeListener(this);

		// Use the existing table to create a new table sharing
		// the DataModel and ListSelectionModel

		int totalColumns = this.main.getColumnCount();

		this.fixed = new JTable();
		this.fixed.setAutoCreateColumnsFromModel(false);
		this.fixed.setModel(this.main.getModel());
		this.fixed.setSelectionModel(this.main.getSelectionModel());
		this.fixed.setFocusable(false);

		// Remove the fixed columns from the main table
		// and add them to the fixed table

		for (int i = 0; i < fixedColumns; i++) {
			TableColumnModel columnModel = this.main.getColumnModel();
			TableColumn column = columnModel.getColumn(0);
			columnModel.removeColumn(column);
			this.fixed.getColumnModel()
				.addColumn(column);
		}

		// Add the fixed table to the scroll pane

		this.fixed.setPreferredScrollableViewportSize(this.fixed.getPreferredSize());
		scrollPane.setRowHeaderView(this.fixed);
		scrollPane.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, this.fixed.getTableHeader());

		// Synchronize scrolling of the row header with the main table

		scrollPane.getRowHeader()
			.addChangeListener(this);
	}

	/*
	 * Return the table being used in the row header
	 */
	public JTable getFixedTable() {

		return this.fixed;
	}

	//
	// Implement the ChangeListener
	//
	@Override
	public void stateChanged(ChangeEvent e) {
		// Sync the scroll pane scrollbar with the row header

		JViewport viewport = (JViewport) e.getSource();
		this.scrollPane.getVerticalScrollBar()
			.setValue(viewport.getViewPosition().y);
	}

	//
	// Implement the PropertyChangeListener
	//
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		// Keep the fixed table in sync with the main table

		if ("selectionModel".equals(e.getPropertyName()))
			this.fixed.setSelectionModel(this.main.getSelectionModel());

		if ("model".equals(e.getPropertyName()))
			this.fixed.setModel(this.main.getModel());
	}
}
