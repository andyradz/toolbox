package com.codigo.aplios.toolbox.core.helpers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class CustomRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 6703872492730589499L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if (row == 0)
			cellComponent.setBackground(Color.YELLOW);
		else if (row == 1)
			cellComponent.setBackground(Color.GRAY);
		else
			cellComponent.setBackground(Color.CYAN);
		return cellComponent;
	}
}