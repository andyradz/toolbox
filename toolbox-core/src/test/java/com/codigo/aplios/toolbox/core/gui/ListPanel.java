package com.codigo.aplios.toolbox.core.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class ListPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	public ListPanel() {

		this.setLayout(new GridLayout(0, 2, 10, 10));
		DefaultListModel model = new DefaultListModel();
		model.addElement(ListPanel.createButtons("one"));
		model.addElement(ListPanel.createButtons("two"));
		model.addElement(ListPanel.createButtons("three"));
		model.addElement(ListPanel.createButtons("four"));
		model.addElement(ListPanel.createButtons("five"));
		model.addElement(ListPanel.createButtons("six"));
		model.addElement(ListPanel.createButtons("seven"));
		model.addElement(ListPanel.createButtons("eight"));
		model.addElement(ListPanel.createButtons("nine"));
		model.addElement(ListPanel.createButtons("ten"));
		model.addElement(ListPanel.createButtons("eleven"));
		model.addElement(ListPanel.createButtons("twelwe"));
		JList list = new JList(model);
		list.setCellRenderer(new PanelRenderer());
		JScrollPane scroll1 = new JScrollPane(list);
		final JScrollBar scrollBar = scroll1.getVerticalScrollBar();
		scrollBar
			.addAdjustmentListener(e -> System.out.println("JScrollBar's current value = " + scrollBar.getValue()));
		this.add(scroll1);
		JScrollPane scroll2 = new JScrollPane(ListPanel.createPanel());
		this.add(scroll2);
		final JScrollBar scrollBar1 = scroll2.getVerticalScrollBar();
		scrollBar1
			.addAdjustmentListener(e -> System.out.println("JScrollBar's current value = " + scrollBar1.getValue()));

	}

	public static JPanel createPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1, 1, 1));
		panel.add(ListPanel.createButtons("one"));
		panel.add(ListPanel.createButtons("two"));
		panel.add(ListPanel.createButtons("three"));
		panel.add(ListPanel.createButtons("four"));
		panel.add(ListPanel.createButtons("five"));
		panel.add(ListPanel.createButtons("six"));
		panel.add(ListPanel.createButtons("seven"));
		panel.add(ListPanel.createButtons("eight"));
		panel.add(ListPanel.createButtons("nine"));
		panel.add(ListPanel.createButtons("ten"));
		panel.add(ListPanel.createButtons("eleven"));
		panel.add(ListPanel.createButtons("twelwe"));
		return panel;
	}

	public static JButton createButtons(String text) {

		JButton button = new JButton(text);
		return button;
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			ListPanel frame = new ListPanel();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// frame.pack();
			frame.setSize(270, 200);
			frame.setVisible(true);
		});
	}

	class PanelRenderer implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			JButton renderer = (JButton) value;
			renderer.setBackground(isSelected ? Color.red
					: list.getBackground());
			return renderer;
		}
	}
}