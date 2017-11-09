package com.codigo.aplios.toolbox.core.helpers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class FiveCharacterEditor extends DefaultCellEditor {
	FiveCharacterEditor() {

		super(new JTextField());
	}

	@Override
	public boolean stopCellEditing() {

		try {
			String editingValue = (String) this.getCellEditorValue();

			if (editingValue.length() != 5) {
				JTextField textField = (JTextField) this.getComponent();
				textField.setBorder(new LineBorder(Color.red));
				textField.selectAll();
				textField.requestFocusInWindow();

				JOptionPane.showMessageDialog(null,
						"Please enter string with 5 letters.",
						"Alert!",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (ClassCastException exception) {
			return false;
		}

		return super.stopCellEditing();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		Component c = super.getTableCellEditorComponent(table, value, isSelected, row, column);
		((JComponent) c).setBorder(new LineBorder(Color.black));

		return c;
	}

}