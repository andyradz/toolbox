package com.codigo.aplios.toolbox.core.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class VolumeComboBox extends JComboBox {
	/**
	 *
	 */
	private static final long serialVersionUID = 2647657444948506068L;
	private final DefaultComboBoxModel model;

	public VolumeComboBox() {

		this.model = new DefaultComboBoxModel();
		this.setModel(this.model);
		this.setRenderer(new VolumeItemRenderer());
		this.setEditor(new VolumeItemEditor());
	}

	/**
	 * Add an array items to this combo box. Each item is an array of two String elements: - first element is country
	 * name. - second element is path of an image file for country flag.
	 *
	 * @param items
	 */
	public void addItems(String[][] items) {

		for (String[] anItem : items)
			this.model.addElement(anItem);
	}
}

class VolumeItemEditor extends BasicComboBoxEditor {
	private JPanel panel = new JPanel();
	private JLabel labelItem = new JLabel();
	private String selectedValue;

	public VolumeItemEditor() {

		this.panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.FIRST_LINE_START;// GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(0, 0, 0, 0);

		this.labelItem.setOpaque(false);
		this.labelItem.setHorizontalAlignment(SwingConstants.LEFT);
		this.labelItem.setForeground(Color.WHITE);
		this.labelItem.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		this.panel.add(this.labelItem, constraints);
		this.panel.setBackground(Color.BLUE);
	}

	@Override
	public Component getEditorComponent() {

		return this.panel;
	}

	@Override
	public Object getItem() {

		return this.selectedValue;
	}

	@Override
	public void setItem(Object item) {

		if (item == null)
			return;
		String[] countryItem = (String[]) item;
		this.selectedValue = countryItem[0];
		this.labelItem.setText(this.selectedValue);
		this.labelItem.setIcon(new ImageIcon(countryItem[1]));
	}
}

class VolumeItemRenderer extends JPanel implements ListCellRenderer {
	private JLabel labelItem = new JLabel();

	public VolumeItemRenderer() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 100.0;
		constraints.insets = new Insets(0, 0, 0, 0);

		this.labelItem.setOpaque(true);
		this.labelItem.setHorizontalAlignment(SwingConstants.LEFT);

		this.add(this.labelItem, constraints);
		this.setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		String[] countryItem = (String[]) value;

		// set country name
		this.labelItem.setText(countryItem[0]);

		// set country flag
		this.labelItem.setIcon(new ImageIcon(countryItem[1]));

		if (isSelected) {
			this.labelItem.setBackground(Color.LIGHT_GRAY);
			this.labelItem.setForeground(Color.YELLOW);
		} else {
			this.labelItem.setForeground(Color.BLACK);
			this.labelItem.setBackground(Color.LIGHT_GRAY);
		}

		return this;
	}

}
