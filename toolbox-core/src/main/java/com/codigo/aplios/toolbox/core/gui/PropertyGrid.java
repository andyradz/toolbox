package com.codigo.aplios.toolbox.core.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.EventObject;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.codigo.aplios.toolbox.core.gui.gridview.JSortTable;

public class PropertyGrid extends JTable implements ActionListener {
	final Timer timer = new Timer(10000, this);

	public PropertyGrid(JFrame parent) {

		this.labelRenderer = new LabelCellRenderer();
		this.labelEditor = new LabelCellEditor();

		this.fields = new LinkedHashMap<>();
		this.curRow = 0;

		this.eventListener = null;

		this.parent = parent;
		this.setModel(new PGModel());
		this.setUI(new PGUI());

		this.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.timer.start();
	}

	public void clear() {

		this.removeAll();
		this.clearSelection();

		this.fields.clear();
		this.curRow = 0;
	}

	public void setEventListener(EventListener listener) {

		this.eventListener = listener;
	}

	public void addCategory(String name, String caption) {

		if (this.fields.containsKey(name))
			return;

		Field field = new Field();
		field.name = name;

		field.row = this.curRow++;
		field.type = "category";
		field.choices = null;
		field.value = null;

		field.label = new JLabel(caption);
		field.renderer = this.labelRenderer;
		field.editor = this.labelEditor;

		this.fields.put(name, field);

		field.label.setFont(field.label.getFont()
			.deriveFont(Font.BOLD));
		field.label.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void addField(String name, String caption, String type, java.util.List choices, Object val) {

		if (this.fields.containsKey(name)) {
			if (!val.equals(this.fields.get(name).value))
				this.fields.get(name).value = null;

			return;
		}

		Field field = new Field();
		field.name = name;

		field.row = this.curRow++;
		field.type = type;
		field.choices = choices;
		field.value = val;

		field.label = new JLabel(caption);
		field.renderer = null;

		switch (type) {
		case "text":
		case "int":
			field.editor = new TextCellEditor(field);
			break;

		case "float":
			field.renderer = new FloatCellRenderer();
			field.editor = new FloatCellEditor(field);
			break;

		case "list":
			field.editor = new ListCellEditor(field);
			break;

		case "bool":
			field.renderer = new BoolCellRenderer();
			field.editor = new BoolCellEditor(field);
			break;

		case "objname":
			field.editor = new ObjectCellEditor(field);
			break;
		}

		if (field.renderer == null)
			field.renderer = new GeneralCellRenderer();

		this.fields.put(name, field);
	}

	public void setFieldValue(String field, Object value) {

		if (!this.fields.containsKey(field))
			return;

		Field f = this.fields.get(field);
		if (f.value == null)
			return;
		f.value = value;
	}

	public void removeField(String field) {

		if (!this.fields.containsKey(field))
			return;

		Field f = this.fields.get(field);
		f.renderer = null;
		f.editor = null;
		this.fields.remove(field);
	}

	@Override
	public Rectangle getCellRect(int row, int col, boolean includeSpacing) {

		Rectangle rect = super.getCellRect(row, col, includeSpacing);
		try {
			Field field = (Field) this.fields.values()
				.toArray()[row];

			if (field.type.equals("category"))
				if (col == 0)
					rect.width = this.getBounds().width;
				else
					rect.width = 0;
		} catch (ArrayIndexOutOfBoundsException ex) {
		}

		return rect;
	}

	@Override
	public TableCellRenderer getCellRenderer(int row, int col) {

		Field field = (Field) this.fields.values()
			.toArray()[row];

		if (col == 0)
			return this.labelRenderer;
		if ((col == 1) && (field.renderer != null))
			return field.renderer;

		return super.getCellRenderer(row, col);
	}

	@Override
	public TableCellEditor getCellEditor(int row, int col) {

		Field field = (Field) this.fields.values()
			.toArray()[row];

		if (col == 0)
			return this.labelEditor;
		if (col == 1)
			return field.editor;

		return super.getCellEditor(row, col);
	}

	public class PGModel extends AbstractTableModel {
		@Override
		public int getRowCount() {

			return PropertyGrid.this.fields.size();
		}

		@Override
		public int getColumnCount() {

			return 2;
		}

		@Override
		public Object getValueAt(int row, int col) {

			Field field = (Field) PropertyGrid.this.fields.values()
				.toArray()[row];

			if (col == 0)
				return field.label.getText();
			else if (!field.type.equals("category"))
				// if (field.value == null) return "<multiple>";
				return field.value;

			return null;
		}

		@Override
		public String getColumnName(int col) {

			if (col == 0)
				return "Property";
			return "Value";
		}

		@Override
		public boolean isCellEditable(int row, int col) {

			if (col == 0)
				return false;
			return true;
		}
	}

	// based off
	// http://code.google.com/p/spantable/source/browse/SpanTable/src/main/java/spantable/SpanTableUI.java
	public class PGUI extends BasicTableUI {
		@Override
		public void paint(Graphics g, JComponent c) {

			Rectangle r = g.getClipBounds();
			int firstRow = this.table.rowAtPoint(new Point(r.x, r.y));
			int lastRow = this.table.rowAtPoint(new Point(r.x, r.y + r.height));
			// -1 is a flag that the ending point is outside the table:
			if (lastRow < 0)
				lastRow = this.table.getRowCount() - 1;
			for (int row = firstRow; row <= lastRow; row++)
				this.paintRow(row, g);
		}

		private void paintRow(int row, Graphics g) {

			Rectangle clipRect = g.getClipBounds();
			for (int col = 0; col < this.table.getColumnCount(); col++) {
				Rectangle cellRect = this.table.getCellRect(row, col, true);
				if (cellRect.width == 0)
					continue;
				if (cellRect.intersects(clipRect))
					this.paintCell(row, col, g, cellRect);
			}
		}

		private void paintCell(int row, int column, Graphics g, Rectangle area) {

			int verticalMargin = this.table.getRowMargin();
			int horizontalMargin = this.table.getColumnModel()
				.getColumnMargin();

			Color c = g.getColor();
			g.setColor(this.table.getGridColor());
			// Acmlmboard border method
			g.drawLine((area.x + area.width) - 1, area.y, (area.x + area.width) - 1, (area.y + area.height) - 1);
			g.drawLine(area.x, (area.y + area.height) - 1, (area.x + area.width) - 1, (area.y + area.height) - 1);
			g.setColor(c);

			area.setBounds(area.x + (horizontalMargin / 2),
					area.y + (verticalMargin / 2),
					area.width - horizontalMargin,
					area.height - verticalMargin);

			if (this.table.isEditing() && (this.table.getEditingRow() == row)
					&& (this.table.getEditingColumn() == column)) {
				Component component = this.table.getEditorComponent();
				component.setBounds(area);
				component.validate();
			} else {
				TableCellRenderer renderer = this.table.getCellRenderer(row, column);
				Component component = this.table.prepareRenderer(renderer, row, column);
				if ((renderer != null) && (component != null)) {
					if (component.getParent() == null)
						this.rendererPane.add(component);
					this.rendererPane
						.paintComponent(g, component, this.table, area.x, area.y, area.width, area.height, true);
				}
			}
		}
	}

	public class LabelCellRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {

			Field field = (Field) PropertyGrid.this.fields.values()
				.toArray()[row];
			field.label.setBackground(Color.CYAN);
			if (col == 0)
				return field.label;
			return null;
		}
	}

	public class GeneralCellRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {

			if (value == null)
				value = "<multiple>";
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		}
	}

	public class FloatCellRenderer extends DefaultTableCellRenderer {
		JLabel label;

		public FloatCellRenderer() {

			this.label = new JLabel();
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {

			if (value == null) {
				this.label.setText("<multiple>");
				return this.label;
			}

			// make float rendering consistent with JSpinner's display
			DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
			df.applyPattern("#.###");
			String formattedval = df.format(value);
			this.label.setText(formattedval);
			// label.setHorizontalAlignment(SwingConstants.RIGHT);
			return this.label;
		}
	}

	public class BoolCellRenderer extends DefaultTableCellRenderer {
		JCheckBox cb;

		public BoolCellRenderer() {

			this.cb = new JCheckBox();
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {

			if (value == null) {
				this.cb.getModel()
					.setSelected(true);
				this.cb.getModel()
					.setArmed(true);
			} else
				this.cb.setSelected((boolean) value);
			return this.cb;
		}
	}

	public class LabelCellEditor implements TableCellEditor {
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {

			return null;
		}

		@Override
		public Object getCellEditorValue() {

			return null;
		}

		@Override
		public boolean isCellEditable(EventObject anEvent) {

			return false;
		}

		@Override
		public boolean shouldSelectCell(EventObject anEvent) {

			return false;
		}

		@Override
		public boolean stopCellEditing() {

			return true;
		}

		@Override
		public void cancelCellEditing() {

		}

		@Override
		public void addCellEditorListener(CellEditorListener l) {

		}

		@Override
		public void removeCellEditorListener(CellEditorListener l) {

		}
	}

	public class FloatCellEditor extends AbstractCellEditor implements TableCellEditor {
		JSpinner spinner;
		Field field;

		public FloatCellEditor(Field f) {

			this.field = f;

			this.spinner = new JSpinner();
			this.spinner.setModel(new SpinnerNumberModel(10.00f, -Float.MAX_VALUE, Float.MAX_VALUE, 1f));
			this.spinner.addChangeListener(evt -> {

				// guarantee the value we're giving out is a Float. herp derp
				Object val = FloatCellEditor.this.spinner.getValue();
				float fval = (val instanceof Double) ? (float) (double) val
						: (float) val;
				FloatCellEditor.this.field.value = fval;
				PropertyGrid.this.eventListener.propertyChanged(FloatCellEditor.this.field.name, fval);
			});
		}

		@Override
		public Object getCellEditorValue() {

			return this.spinner.getValue();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {

			this.spinner.setValue(value == null ? 0f
					: value);
			return this.spinner;
		}
	}

	public class TextCellEditor extends AbstractCellEditor implements TableCellEditor {
		JTextField textfield;
		Field field;
		boolean isInt;

		public TextCellEditor(Field f) {

			this.field = f;
			this.isInt = f.type.equals("int");

			this.textfield = new JTextField(f.value.toString());
			this.textfield.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent evt) {

				}

				@Override
				public void keyTyped(KeyEvent evt) {

				}

				@Override
				public void keyReleased(KeyEvent evt) {

					Object val = TextCellEditor.this.textfield.getText();
					try {
						if (TextCellEditor.this.isInt)
							val = Integer.parseInt((String) val);
						TextCellEditor.this.textfield.setForeground(Color.getColor("text"));

						TextCellEditor.this.field.value = val;
						PropertyGrid.this.eventListener.propertyChanged(TextCellEditor.this.field.name, val);
					} catch (NumberFormatException ex) {
						TextCellEditor.this.textfield.setForeground(new Color(0xFF4040));
					}
				}
			});
		}

		@Override
		public Object getCellEditorValue() {

			return this.textfield.getText();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {

			if (value == null)
				value = this.isInt ? "0"
						: "<multiple>";
			this.textfield.setText(value.toString());
			return this.textfield;
		}
	}

	public class ListCellEditor extends AbstractCellEditor implements TableCellEditor {
		JComboBox combo;
		Field field;

		public ListCellEditor(Field f) {

			this.field = f;

			this.combo = new JComboBox(f.choices.toArray());

			this.combo.addActionListener(evt -> {

				Object val = ListCellEditor.this.combo.getSelectedItem();

				if (!ListCellEditor.this.field.value.equals(val)) {
					ListCellEditor.this.field.value = val;
					PropertyGrid.this.eventListener.propertyChanged(ListCellEditor.this.field.name, val);
				}
			});
		}

		@Override
		public Object getCellEditorValue() {

			return this.combo.getSelectedItem();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {

			if (value == null)
				this.combo.setSelectedIndex(0);
			else
				this.combo.setSelectedItem(value);
			return this.combo;
		}
	}

	public class BoolCellEditor extends AbstractCellEditor implements TableCellEditor {
		JCheckBox checkbox;
		Field field;

		public BoolCellEditor(Field f) {

			this.field = f;

			this.checkbox = new JCheckBox();
			this.checkbox.addActionListener(evt -> {

				boolean val = BoolCellEditor.this.checkbox.isSelected();
				BoolCellEditor.this.field.label.setText(Boolean.toString(val));
				BoolCellEditor.this.field.value = val;
				PropertyGrid.this.eventListener.propertyChanged(BoolCellEditor.this.field.name, val);
			});
		}

		@Override
		public Object getCellEditorValue() {

			return this.checkbox.isSelected();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {

			this.checkbox.setSelected(value == null ? false
					: (boolean) value);
			return this.checkbox;
		}
	}

	public class ObjectCellEditor extends AbstractCellEditor implements TableCellEditor {
		JPanel container;
		JTextField textfield;
		JButton button;
		Field field;

		public ObjectCellEditor(Field f) {

			this.field = f;

			this.container = new JPanel();
			this.container.setLayout(new BorderLayout());

			this.textfield = new JTextField(f.value.toString());
			this.container.add(this.textfield, BorderLayout.CENTER);
			this.textfield.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent evt) {

				}

				@Override
				public void keyTyped(KeyEvent evt) {

				}

				@Override
				public void keyReleased(KeyEvent evt) {

					String val = ObjectCellEditor.this.textfield.getText();

					// textfield.setForeground(ObjectDB.objects.containsKey(val) ? Color.getColor(
					// "text") : new Color(0xFF4040));

					ObjectCellEditor.this.field.value = val;
					PropertyGrid.this.eventListener.propertyChanged(ObjectCellEditor.this.field.name, val);
				}
			});

			this.button = new JButton("...");
			this.container.add(this.button, BorderLayout.EAST);
			this.button.addActionListener(evt -> {
				// GalaxyEditorForm gform = (GalaxyEditorForm) parent;

				// ObjectSelectForm objsel = new ObjectSelectForm(gform, gform.zoneArcs.get(
				// gform.galaxyName).gameMask, textfield.getText());
				// objsel.setVisible(true);

				// String val = objsel.selectedObject;
				// textfield.setText(val);
				// textfield.setForeground(ObjectDB.objects.containsKey(val) ? Color.getColor(
				// "text") : new Color(0xFF4040));

				// field.value = val;
				// eventListener.propertyChanged(field.name, val);
			});

			int btnheight = this.button.getPreferredSize().height;
			this.button.setPreferredSize(new Dimension(btnheight, btnheight));

			// textfield.setForeground(ObjectDB.objects.containsKey((String) field.value) ? Color
			// .getColor("text") : new Color(0xFF4040));
		}

		@Override
		public Object getCellEditorValue() {

			return this.textfield.getText();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {

			this.textfield.setText(value == null ? "<multiple>"
					: value.toString());
			return this.container;
		}
	}

	public class Field {
		String name;

		String type;
		int row;
		java.util.List choices;
		Object value;

		JLabel label;
		TableCellRenderer renderer;
		TableCellEditor editor;
	}

	public interface EventListener {
		public void propertyChanged(String propname, Object value);
	}

	public LinkedHashMap<String, PropertyGrid.Field> fields;
	private int curRow;

	private EventListener eventListener;

	private JFrame parent;

	private LabelCellRenderer labelRenderer;
	private LabelCellEditor labelEditor;
	private static JComboBox WygladProgramujComboBox;
	private static JFrame frame = new JFrame("Prognostic :: Administracja");

	public static void main(String s[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(PropertyGrid.frame);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		// Add a window listner for close button
		PropertyGrid.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				JOptionPane.showMessageDialog(PropertyGrid.frame,
						e.toString(),
						"Czy chcesz zmknąc program?",
						JOptionPane.QUESTION_MESSAGE);
				System.exit(0);
			}
		});
		// This is an empty content area in the frame
		// frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(Filepath)));
		String appdata = System.getenv("APPDATA");
		// frame.setIconImage(Toolkit.getDefaultToolkit().getImage(frame.getClass().getResource("znaczek.ico")));
		ImageIcon imgicon = new ImageIcon("D://IIM.ico");
		PropertyGrid.frame.setIconImage(imgicon.getImage());

		DatePicker calcmb = new DatePicker(Calendar.getInstance(Locale.getDefault()));

		JLabel jlbempty = new JLabel("");
		PropertyGrid pg = new PropertyGrid(PropertyGrid.frame);
		pg.setRowHeight(21);
		pg.addCategory("Visibility", "Okna");
		pg.addField("F1", "Codigo", "bool", null, true);
		pg.addField("Float", "Wartość", "float", null, 1.01);
		pg.addField("Logic", "Logical", "list", Arrays.asList("False", "True"), "False");
		pg.addField("Const", "Stała", "list", Arrays.asList(2, 3, 4, 5, 6), 2);

		pg.addCategory("Visibility1", "Okna1");
		pg.addField("F11", "Codigo1", "bool", null, true);
		pg.addField("Float1", "Wartość1", "float", null, 1);
		pg.addField("Logic1", "Logical1", "list", Arrays.asList("False", "True"), "False");
		pg.addField("Const1", "Stała1", "list", Arrays.asList(2, 3, 4, 5, 6, 23, 23, 2322, 23), 2);

		jlbempty.setPreferredSize(new Dimension(175, 100));
		PropertyGrid.WygladProgramujComboBox = new JComboBox(new String[] { "1", "2", "3", "4" });

		PropertyGrid.WygladProgramujComboBox.addActionListener(PropertyGrid::WygladProgramu_jComboBoxActionPerformed);
		String[] columnNames = { "First Name", "Last Name", "Sport", "# of Years", "Vegetarian", "Percent" };

		Object[][] data =
				{
					{ "Kathy", "Smith", "Snowboarding", new Double(5.01), new Boolean(false), new Double(0.50), },
					{
						"Andrzej",
						"Radziszewski",
						"Snowboarding",
						new Double(-5.01),
						new Boolean(true),
						new Double(-0.50), },
					{
						"Andrzej",
						"Radziszewski",
						"Pudło",
						new Double(-225.02521),
						new Boolean(true),
						new Double(-0.50), },

					{ "Adriam", "Kalisamon", "<p>df</p>", new Double(13215.01), new Boolean(false), new Double(.50), },
					{ "John", "Doe", "Rowing", new Double(3.65), new Boolean(true), new Double(0.75), },
					{ "Sue", "Black", "Knitting", new Double(2.232), new Boolean(false), new Double(1), },
					{ "Jane", "White", "Speed reading", new Double(20.333), new Boolean(true), new Double(1), },
					{
						"Jane12",
						"White12",
						"Speed12 reading",
						new Double(210.3555),
						new Boolean(true),
						new Double(0.10), },
					{ "Jane", "White", "Speed reading", new Double(20.23), new Boolean(true), new Double(0.100), },
					{ "Jane", "White", "Speed reading", new Double(20.232), new Boolean(true), new Double(0.100), },
					{ "Jane3", "White3", "Speed3 reading", new Double(20.232), new Boolean(true), new Double(0.100), },
					{ "Jan4e", "Whit4e", "Speed4 reading", new Double(20.0), new Boolean(true), new Double(0.1), },
					{ "Jo6e", "Bro5wn", "Pool3", new Double(10.89), new Boolean(false), new Double(1) },
					{ "Kathy", "Smith", "Snowboarding", new Double(5.01), new Boolean(false), new Double(0.50), },
					{ "John", "Doe", "Rowing", new Double(3.65), new Boolean(true), new Double(0.75), },
					{ "Sue", "Black", "Knitting", new Double(2.232), new Boolean(false), new Double(1), },
					{ "Jane", "White", "Speed reading", new Double(20.333), new Boolean(true), new Double(1), },
					{
						"Jane12",
						"White12",
						"Speed12 reading",
						new Double(210.3555),
						new Boolean(true),
						new Double(0.10), },
					{ "Jane", "White", "Speed reading", new Double(20.23), new Boolean(true), new Double(0.100), },
					{ "Jane", "White", "Speed reading", new Double(20.232), new Boolean(true), new Double(0.100), },
					{ "Jane3", "White3", "Speed3 reading", new Double(20.232), new Boolean(true), new Double(0.100), },
					{
						"Jan412312e",
						"Whit4e23",
						"Speed4 rea213ding",
						new Double(20.0),
						new Boolean(true),
						new Double(0.1), },
					{ "Jo6e23", "Bro5wn1", "Pool3232", new Double(10.89), new Boolean(false), new Double(1) },
					{ "Kathy", "Smith", "Snowboarding", new Double(5.01), new Boolean(false), new Double(0.50), },
					{ "John", "Doe", "Rowing", new Double(3.65), new Boolean(true), new Double(0.75), },
					{ "Sue", "Black", "Knitting", new Double(2.232), new Boolean(false), new Double(1), },
					{ "Jane", "White", "Speed reading", new Double(20.333), new Boolean(true), new Double(1), },
					{
						"Jane12",
						"White12",
						"Speed12 reading",
						new Double(210.3555),
						new Boolean(true),
						new Double(0.10), },
					{ "Jane", "White", "Speed reading", new Double(20.23), new Boolean(true), new Double(0.100), },
					{ "Jane", "White", "Speed reading", new Double(20.232), new Boolean(true), new Double(0.100), },
					{ "Jane3", "White3", "Speed3 reading", new Double(20.232), new Boolean(true), new Double(0.100), },
					{ "Jan4e", "Whit4e", "Speed4 reading", new Double(20.0), new Boolean(true), new Double(0.1), },
					{ "Jo6e", "Bro5wn", "Pool3", new Double(12220.89), new Boolean(false), new Double(1) } };

		class CustomTableCellRender extends DefaultTableCellRenderer {

			Border padding = BorderFactory.createEmptyBorder(0, 2, 0, 2);

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), this.padding));

				return this;
			}
		}

		class PercentTableCellRenderer extends CustomTableCellRender {

			private final NumberFormat numFormat = NumberFormat.getPercentInstance(Locale.getDefault());

			@Override
			public final Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				final Component result =
						super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (value instanceof Number) {
					this.setHorizontalAlignment(SwingConstants.RIGHT);
					this.setText(this.numFormat.format(value));
				} else
					this.setText("");

				return result;
			}
		}

		class CurrencyTableCellRenderer extends CustomTableCellRender {

			private final NumberFormat FORMAT = NumberFormat.getCurrencyInstance();

			@Override
			public final Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				final Component result =
						super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (value instanceof Number) {
					this.setHorizontalAlignment(SwingConstants.RIGHT);
					this.setText(this.FORMAT.format(value));
				} else
					this.setText("");

				this.setBackground(Color.orange);
				if (10.0 >= ((Number) value).doubleValue())
					this.setForeground(Color.BLUE);
				else
					this.setForeground(Color.RED);
				this.setFont(this.getFont()
					.deriveFont(Font.PLAIN));
				return result;
			}
		}

		class BooleanTableCellRenderer extends CustomTableCellRender {

			@Override
			public final Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				final Component result =
						super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (value instanceof Boolean) {
					this.setHorizontalAlignment(SwingConstants.LEFT);
					this.setText((Boolean) value ? "Tak"
							: "Nie");
				} else
					this.setText("");

				this.setBackground(Color.LIGHT_GRAY);
				this.setForeground(Color.yellow);
				this.setFont(this.getFont()
					.deriveFont(Font.PLAIN));
				return result;
			}
		}

		class HeaderRenderer implements UIResource, TableCellRenderer {

			private TableCellRenderer original;

			public HeaderRenderer(TableCellRenderer original) {

				this.original = original;
			}

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component comp =
						this.original.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				comp.setFont(comp.getFont()
					.deriveFont(Font.BOLD));
				return comp;
			}

		}

		final JSortTable sorttable = new JSortTable(data, columnNames);

		sorttable.setRowHeight(21);

		sorttable.getColumnModel()
			.getColumn(0)
			.setCellRenderer(new CustomTableCellRender());

		sorttable.getColumnModel()
			.getColumn(1)
			.setCellRenderer(new CustomTableCellRender());

		sorttable.getColumnModel()
			.getColumn(2)
			.setCellRenderer(new CustomTableCellRender());

		sorttable.getColumnModel()
			.getColumn(3)
			.setCellRenderer(new CurrencyTableCellRenderer());

		sorttable.getColumnModel()
			.getColumn(4)
			.setCellRenderer(new BooleanTableCellRenderer());

		sorttable.getColumnModel()
			.getColumn(5)
			.setCellRenderer(new PercentTableCellRenderer());

		sorttable.setFont(new Font(sorttable.getFont()
			.getName(), Font.PLAIN, 11));

		PropertyGrid.frame.setTitle("DB Suite Studio Manager Free Edition v1.6.2.1");

		PropertyGrid.frame.getContentPane()
			.add(jlbempty, BorderLayout.NORTH);
		PropertyGrid.frame.getContentPane()
			.add(PropertyGrid.WygladProgramujComboBox, BorderLayout.BEFORE_LINE_BEGINS);
		PropertyGrid.frame.getContentPane()
			.add(new JScrollPane(sorttable), BorderLayout.CENTER);

		JButton button = new JButton("alert");
		button.setIconTextGap(10);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setBorder(BorderFactory.createEmptyBorder(4, 4, 2, 20));
		try {
			button.setIcon(new ImageIcon(ImageIO.read(Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("ic_add_alert_black_18dp.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		PropertyGrid.frame.getContentPane()
			.add(button, BorderLayout.SOUTH);

		// frame.pack();
		PropertyGrid.frame.setSize(800, 600);
		PropertyGrid.frame.setVisible(true);
	}

	private static void WygladProgramu_jComboBoxActionPerformed(ActionEvent evt) {

		// TODO add your handling code here:
		JComboBox control = (JComboBox) evt.getSource();

		/* Konfiguracja wyglądu program */
		try {
			PropertyGrid.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if (PropertyGrid.WygladProgramujComboBox.getSelectedIndex() == 0) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(control, e.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
				}
				SwingUtilities.updateComponentTreeUI(PropertyGrid.frame);
			}

			if (PropertyGrid.WygladProgramujComboBox.getSelectedIndex() == 1) {
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(control, e.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
				}
				SwingUtilities.updateComponentTreeUI(control);
			}

			if (PropertyGrid.WygladProgramujComboBox.getSelectedIndex() == 2) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(control, e.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
				}
				SwingUtilities.updateComponentTreeUI(control);
			}
			if (PropertyGrid.WygladProgramujComboBox.getSelectedIndex() == 3) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(control, e.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
				}
				SwingUtilities.updateComponentTreeUI(control);

			}
		} finally {
			control.setCursor(Cursor.getDefaultCursor());
		}
		System.gc();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		this.timer.stop();
		this.timer.setDelay(0);
		System.exit(0);
	}
}
