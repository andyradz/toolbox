/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.io;

import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author andrzej.radziszewski
 */
public class ImageTableCellRender extends DefaultTableCellRenderer {

	// public ImageTableCellRender() {
	//
	// // super();
	// }
	//
	// /*
	// * @see TableCellRenderer#getTableCellRendererComponent(JTable, Object, boolean, boolean, int, int)
	// */
	// @Override
	// public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	// int row, int column) {
	//
	// sun.awt.shell.ShellFolder sf = null;
	// Image image = null;
	//
	// JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	// File ff = new File(table.getModel().getValueAt(row, column + 1).toString());
	// if (ff.isFile() || ff.isDirectory())
	// FilePermission perm = new FilePermission(table.getModel().getValueAt(row, column + 1).toString(), "read");
	// //AccessController.checkPermission(perm);
	// // File ff = new File("c:/windows");
	// Icon icon = null;
	// try {
	//
	// sf = sun.awt.shell.ShellFolder.getShellFolder(ff);
	//
	// icon = new ImageIcon(sf.getIcon(false));
	// image = ((ImageIcon) icon).getImage();
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// }
	//
	// // setText("<dir> " + String.valueOf(sf.getTotalSpace()));
	// this.setIcon(icon);
	// // setSize(table.getColumnModel().getColumn(column).getWidth() - 150,
	// // getPreferredSize().height);
	//
	// // if (isSelected) {
	// // setBackground(table.getSelectionBackground());
	// // setForeground(table.getSelectionForeground());
	// // } else {
	// // setBackground(table.getBackground());
	// // setForeground(table.getForeground());
	// // }
	// return this;
	// }

}
