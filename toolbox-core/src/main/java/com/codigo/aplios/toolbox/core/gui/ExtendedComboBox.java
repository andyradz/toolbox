package com.codigo.aplios.toolbox.core.gui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;

public class ExtendedComboBox extends JComboBox {

	public ExtendedComboBox() {

		this.setModel(new ExtendedComboBoxModel());
		this.setRenderer(new ExtendedListCellRenderer());
	}

	public void addDelimiter(String text) {

		this.addItem(new Delimiter(text));
	}

	private static class ExtendedComboBoxModel extends DefaultComboBoxModel {
		@Override
		public void setSelectedItem(Object anObject) {

			if (!(anObject instanceof Delimiter))
				super.setSelectedItem(anObject);
			else {
				int index = this.getIndexOf(anObject);
				if (index < this.getSize())
					this.setSelectedItem(this.getElementAt(index + 1));
			}
		}

	}

	private static class ExtendedListCellRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			if (!(value instanceof Delimiter))
				return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			else {
				JLabel label = new JLabel(value.toString());
				Font f = label.getFont();
				label.setFont(f.deriveFont(f.getStyle() | Font.BOLD | Font.ITALIC));
				return label;
			}
		}
	}

	private static class Delimiter {
		private String text;

		private Delimiter(String text) {

			this.text = text;
		}

		@Override
		public String toString() {

			return this.text.toString();
		}
	}
}
