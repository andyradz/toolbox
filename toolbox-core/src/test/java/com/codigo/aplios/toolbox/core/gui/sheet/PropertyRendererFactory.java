package com.codigo.aplios.toolbox.core.gui.sheet;

import javax.swing.table.TableCellRenderer;

/**
 * Factory for Property renderers.<br>
 */
public interface PropertyRendererFactory {

	TableCellRenderer createTableCellRenderer(Property property);

	TableCellRenderer createTableCellRenderer(Class<?> type);

}
