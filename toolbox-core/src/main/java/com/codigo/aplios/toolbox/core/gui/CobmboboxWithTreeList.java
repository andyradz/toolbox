package com.codigo.aplios.toolbox.core.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.EventObject;

// w w w . jav a2s. co m
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

public class CobmboboxWithTreeList {
	public static void main(String[] args) {

		String[] m = { "A", "B", "C" };
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Node("Values"));
		root.add(new DefaultMutableTreeNode(new Node("Value 1", m)));
		root.add(new DefaultMutableTreeNode(new Node("Value 2", m)));
		DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(new Node("Value 3", m));
		root.add(leaf);
		leaf.add(new DefaultMutableTreeNode(new Node("Value 3A", m)));
		leaf.add(new DefaultMutableTreeNode(new Node("Value 3B", m)));

		JTree tree = new JTree(root);
		RendererDispatcher rendererDispatcher = new RendererDispatcher(new JComboBox<String>());
		RendererDispatcher editorDispatcher = new RendererDispatcher(new JComboBox<String>());
		tree.setCellRenderer(rendererDispatcher);
		tree.setCellEditor(editorDispatcher);
		tree.setEditable(true);

		JFrame f = new JFrame();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.getContentPane()
			.add(new JScrollPane(tree));
		f.setSize(320, 240);
		f.setVisible(true);
	}
}

class Node {
	String name;
	String[] Values;
	int myIndex;

	public Node(String name, String... Values) {

		this.name = name;
		this.Values = Values;
	}

	@Override
	public String toString() {

		return this.name;
	}

	public int getMyIndex() {

		return this.myIndex;
	}

	public void setIndex(int selectedValueIndex) {

		this.myIndex = selectedValueIndex;
	}

	public String[] getValues() {

		return this.Values;
	}
}

class RendererDispatcher extends DefaultCellEditor implements TreeCellRenderer {
	JPanel panel = new JPanel();
	JLabel ValueName = new JLabel();
	JComboBox<String> comboBox;
	Node node;

	public RendererDispatcher(JComboBox<String> comboBox) {

		super(comboBox);
		this.comboBox = comboBox;
		this.panel.setOpaque(false);
		this.panel.add(this.ValueName);
		this.panel.add(comboBox);
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {

		Node node = RendererDispatcher.extractNode(value);
		this.setContents(node);
		return this.panel;
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row) {

		Node node = RendererDispatcher.extractNode(value);
		this.setContents(node);
		this.node = node;
		return this.panel;
	}

	@Override
	public Object getCellEditorValue() {

		Object o = super.getCellEditorValue();
		DefaultComboBoxModel<String> m = (DefaultComboBoxModel<String>) this.comboBox.getModel();
		Node n = new Node(this.ValueName.getText(), this.node.getValues());
		n.setIndex(m.getIndexOf(o));
		return n;
	}

	@Override
	public boolean isCellEditable(final EventObject event) {

		Object source = event.getSource();
		if (!(source instanceof JTree) || !(event instanceof MouseEvent))
			return false;
		JTree tree = (JTree) source;
		MouseEvent mouseEvent = (MouseEvent) event;
		TreePath path = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
		if (path == null)
			return false;
		Object node = path.getLastPathComponent();
		if ((node == null) || !(node instanceof DefaultMutableTreeNode))
			return false;

		Rectangle r = tree.getPathBounds(path);
		if (r == null)
			return false;
		Dimension d = this.panel.getPreferredSize();
		r.setSize(new Dimension(d.width, r.height));
		if (r.contains(mouseEvent.getX(), mouseEvent.getY())) {
			Point pt = SwingUtilities.convertPoint(tree, mouseEvent.getPoint(), this.panel);
			Object o = SwingUtilities.getDeepestComponentAt(this.panel, pt.x, pt.y);
			if (o instanceof JComboBox)
				this.comboBox.showPopup();
			else if (o instanceof Component) {
				Object oo = SwingUtilities.getAncestorOfClass(JComboBox.class, (Component) o);
				if (oo instanceof JComboBox)
					this.comboBox.showPopup();
			}
			return true;
		}
		return this.delegate.isCellEditable(event);
	}

	private static Node extractNode(Object value) {

		if (value instanceof DefaultMutableTreeNode) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			Object userObject = node.getUserObject();
			if (userObject instanceof Node)
				return (Node) userObject;
		}
		return null;
	}

	private void setContents(Node node) {

		if (node == null)
			return;
		this.ValueName.setText(node.toString());
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.comboBox.getModel();
		model.removeAllElements();
		if (node.getValues().length > 0) {
			this.panel.add(this.comboBox);
			for (String s : node.getValues())
				model.addElement(s);
			this.comboBox.setSelectedIndex(node.getMyIndex());
		} else
			this.panel.remove(this.comboBox);
	}
}