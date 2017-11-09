
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * @version 1.0 05/31/99
 */
public class HideColumnTableExample extends JFrame {

	public HideColumnTableExample() {

		super("HideColumnTable Example");

		JTable table = new JTable(5, 7);
		ColumnButtonScrollPane pane = new ColumnButtonScrollPane(table);
		this.getContentPane()
			.add(pane);
	}

	public static void main(String[] args) {

		HideColumnTableExample frame = new HideColumnTableExample();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}
		});
		frame.setSize(400, 100);
		frame.setVisible(true);
	}
}

class ColumnButtonScrollPane extends JScrollPane {
	Component columnButton;

	public ColumnButtonScrollPane(JTable table) {

		super(table);
		TableColumnModel cm = table.getColumnModel();
		LimitedTableHeader header = new LimitedTableHeader(cm);
		table.setTableHeader(header);
		this.columnButton = this.createUpperCorner(header);
		this.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER, this.columnButton);
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ColumnButtonScrollPaneLayout layout = new ColumnButtonScrollPaneLayout();
		this.setLayout(layout);
		layout.syncWithScrollPane(this);
	}

	protected Component createUpperCorner(JTableHeader header) {

		ColumnButton corner = new ColumnButton(header);
		return corner;
	}

	public class LimitedTableHeader extends JTableHeader {
		public LimitedTableHeader(TableColumnModel cm) {

			super(cm);
		}

		// actually, this is a not complete way. but easy one.
		// you can see last column painted wider, short time :)
		// If you don't like this kind cheap fake,
		// you have to overwrite the paint method in UI class.
		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			ColumnButtonScrollPane.this.columnButton.repaint();
		}
	}

	public class ColumnButton extends JPanel {
		JTable table;

		TableColumnModel cm;

		JButton revealButton;

		JButton hideButton;

		Stack stack;

		public ColumnButton(JTableHeader header) {

			this.setLayout(new GridLayout(1, 2));
			this.setBorder(new LinesBorder(SystemColor.controlShadow, new Insets(0, 1, 0, 0)));

			this.stack = new Stack();
			this.table = header.getTable();
			this.cm = this.table.getColumnModel();

			this.revealButton = this.createButton(header, SwingConstants.WEST);
			this.hideButton = this.createButton(header, SwingConstants.EAST);
			this.add(this.revealButton);
			this.add(this.hideButton);

			this.revealButton.addActionListener(e -> {

				TableColumn column = (TableColumn) ColumnButton.this.stack.pop();
				ColumnButton.this.cm.addColumn(column);
				if (ColumnButton.this.stack.empty())
					ColumnButton.this.revealButton.setEnabled(false);
				ColumnButton.this.hideButton.setEnabled(true);
				ColumnButton.this.table.sizeColumnsToFit(-1);
			});
			this.hideButton.addActionListener(e -> {

				int n = ColumnButton.this.cm.getColumnCount();
				TableColumn column = ColumnButton.this.cm.getColumn(n - 1);
				ColumnButton.this.stack.push(column);
				ColumnButton.this.cm.removeColumn(column);
				if (n < 3)
					ColumnButton.this.hideButton.setEnabled(false);
				ColumnButton.this.revealButton.setEnabled(true);
				ColumnButton.this.table.sizeColumnsToFit(-1);
			});

			if (1 < this.cm.getColumnCount())
				this.hideButton.setEnabled(true);
			else
				this.hideButton.setEnabled(false);
			this.revealButton.setEnabled(false);
		}

		protected JButton createButton(JTableHeader header, int direction) {

			// int iconHeight = header.getPreferredSize().height - 6;
			int iconHeight = 8;
			JButton button = new JButton();
			button.setIcon(new ArrowIcon(iconHeight, direction, true));
			button.setDisabledIcon(new ArrowIcon(iconHeight, direction, false));
			button.setRequestFocusEnabled(false);
			button.setForeground(header.getForeground());
			button.setBackground(header.getBackground());
			button.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			return button;
		}
	}
}

class ColumnButtonScrollPaneLayout extends ScrollPaneLayout {

	public ColumnButtonScrollPaneLayout() {

		super.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}

	@Override
	public void setVerticalScrollBarPolicy(int x) {

		// VERTICAL_SCROLLBAR_ALWAYS
		super.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}

	@Override
	public void layoutContainer(Container parent) {

		super.layoutContainer(parent);

		if ((this.colHead == null) || (!this.colHead.isVisible()) || (this.upperRight == null) || (this.vsb == null))
			return;

		Rectangle vsbR = new Rectangle(0, 0, 0, 0);
		vsbR = this.vsb.getBounds(vsbR);

		Rectangle colHeadR = new Rectangle(0, 0, 0, 0);
		colHeadR = this.colHead.getBounds(colHeadR);
		colHeadR.width -= vsbR.width;
		this.colHead.getBounds(colHeadR);

		Rectangle upperRightR = this.upperRight.getBounds();
		upperRightR.x -= vsbR.width;
		upperRightR.width += vsbR.width + 1;
		this.upperRight.setBounds(upperRightR);
	}
}

class LinesBorder extends AbstractBorder implements SwingConstants {
	protected int northThickness;

	protected int southThickness;

	protected int eastThickness;

	protected int westThickness;

	protected Color northColor;

	protected Color southColor;

	protected Color eastColor;

	protected Color westColor;

	public LinesBorder(Color color) {

		this(color, 1);
	}

	public LinesBorder(Color color, int thickness) {

		this.setColor(color);
		this.setThickness(thickness);
	}

	public LinesBorder(Color color, Insets insets) {

		this.setColor(color);
		this.setThickness(insets);
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

		Color oldColor = g.getColor();

		g.setColor(this.northColor);
		for (int i = 0; i < this.northThickness; i++)
			g.drawLine(x, y + i, (x + width) - 1, y + i);
		g.setColor(this.southColor);
		for (int i = 0; i < this.southThickness; i++)
			g.drawLine(x, (y + height) - i - 1, (x + width) - 1, (y + height) - i - 1);
		g.setColor(this.eastColor);
		for (int i = 0; i < this.westThickness; i++)
			g.drawLine(x + i, y, x + i, (y + height) - 1);
		g.setColor(this.westColor);
		for (int i = 0; i < this.eastThickness; i++)
			g.drawLine((x + width) - i - 1, y, (x + width) - i - 1, (y + height) - 1);

		g.setColor(oldColor);
	}

	@Override
	public Insets getBorderInsets(Component c) {

		return new Insets(this.northThickness, this.westThickness, this.southThickness, this.eastThickness);
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {

		return new Insets(this.northThickness, this.westThickness, this.southThickness, this.eastThickness);
	}

	@Override
	public boolean isBorderOpaque() {

		return true;
	}

	public void setColor(Color c) {

		this.northColor = c;
		this.southColor = c;
		this.eastColor = c;
		this.westColor = c;
	}

	public void setColor(Color c, int direction) {

		switch (direction) {
		case NORTH:
			this.northColor = c;
			break;
		case SOUTH:
			this.southColor = c;
			break;
		case EAST:
			this.eastColor = c;
			break;
		case WEST:
			this.westColor = c;
			break;
		default:
		}
	}

	public void setThickness(int n) {

		this.northThickness = n;
		this.southThickness = n;
		this.eastThickness = n;
		this.westThickness = n;
	}

	public void setThickness(Insets insets) {

		this.northThickness = insets.top;
		this.southThickness = insets.bottom;
		this.eastThickness = insets.right;
		this.westThickness = insets.left;
	}

	public void setThickness(int n, int direction) {

		switch (direction) {
		case NORTH:
			this.northThickness = n;
			break;
		case SOUTH:
			this.southThickness = n;
			break;
		case EAST:
			this.eastThickness = n;
			break;
		case WEST:
			this.westThickness = n;
			break;
		default:
		}
	}

	public void append(LinesBorder b, boolean isReplace) {

		if (isReplace) {
			this.northThickness = b.northThickness;
			this.southThickness = b.southThickness;
			this.eastThickness = b.eastThickness;
			this.westThickness = b.westThickness;
		} else {
			this.northThickness = Math.max(this.northThickness, b.northThickness);
			this.southThickness = Math.max(this.southThickness, b.southThickness);
			this.eastThickness = Math.max(this.eastThickness, b.eastThickness);
			this.westThickness = Math.max(this.westThickness, b.westThickness);
		}
	}

	public void append(Insets insets, boolean isReplace) {

		if (isReplace) {
			this.northThickness = insets.top;
			this.southThickness = insets.bottom;
			this.eastThickness = insets.right;
			this.westThickness = insets.left;
		} else {
			this.northThickness = Math.max(this.northThickness, insets.top);
			this.southThickness = Math.max(this.southThickness, insets.bottom);
			this.eastThickness = Math.max(this.eastThickness, insets.right);
			this.westThickness = Math.max(this.westThickness, insets.left);
		}
	}

}

class ArrowIcon implements Icon, SwingConstants {
	private static final int DEFAULT_SIZE = 11;

	// private static final int DEFAULT_SIZE = 5;

	private int size;

	private int iconSize;

	private int direction;

	private boolean isEnabled;

	private BasicArrowButton iconRenderer;

	public ArrowIcon(int direction, boolean isPressedView) {

		this(ArrowIcon.DEFAULT_SIZE, direction, isPressedView);
	}

	public ArrowIcon(int iconSize, int direction, boolean isEnabled) {

		this.size = iconSize / 2;
		this.iconSize = iconSize;
		this.direction = direction;
		this.isEnabled = isEnabled;
		this.iconRenderer = new BasicArrowButton(direction);
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {

		this.iconRenderer.paintTriangle(g, x, y, this.size, this.direction, this.isEnabled);
	}

	@Override
	public int getIconWidth() {

		// int retCode;
		switch (this.direction) {
		case NORTH:
		case SOUTH:
			return this.iconSize;
		case EAST:
		case WEST:
			return this.size;
		}
		return this.iconSize;
	}

	@Override
	public int getIconHeight() {

		switch (this.direction) {
		case NORTH:
		case SOUTH:
			return this.size;
		case EAST:
		case WEST:
			return this.iconSize;
		}
		return this.size;
	}
}
