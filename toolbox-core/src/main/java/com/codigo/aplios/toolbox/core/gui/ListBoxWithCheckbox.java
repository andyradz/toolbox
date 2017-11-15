package com.codigo.aplios.toolbox.core.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class ListBoxWithCheckbox {

    public static void main(String[] args) {

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane()
                .add(new CheckCombo().getContent());
        f.setSize(300, 160);
        f.setLocation(200, 200);
        f.setVisible(true);
    }

}

class CheckCombo implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox)e.getSource();
        CheckComboStore store = (CheckComboStore)cb.getSelectedItem();
        CheckComboRenderer ccr = (CheckComboRenderer)cb.getRenderer();
        ccr.checkBox.setSelected((store.state = !store.state));
    }

    public JPanel getContent() {

        String[] ids = {"north", "west", "south", "east"};
        Boolean[] values = {Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE};
        CheckComboStore[] stores = new CheckComboStore[ids.length];
        for (int j = 0; j < ids.length; j++)
            stores[j] = new CheckComboStore(ids[j], values[j]);
        JComboBox combo = new JComboBox(stores);
        combo.setRenderer(new CheckComboRenderer());
        combo.addActionListener(this);
        JPanel panel = new JPanel();
        panel.add(combo);
        return panel;
    }

}

class CheckComboRenderer implements ListCellRenderer {

    JCheckBox checkBox;

    public CheckComboRenderer() {

        this.checkBox = new JCheckBox();
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {

        CheckComboStore store = (CheckComboStore)value;
        this.checkBox.setText(store.id);
        this.checkBox.setSelected(store.state.booleanValue());
        this.checkBox.setBackground(isSelected ? Color.red
                : Color.white);
        this.checkBox.setForeground(isSelected ? Color.white
                : Color.black);
        return this.checkBox;
    }

}

class CheckComboStore {

    String id;

    Boolean state;

    public CheckComboStore(String id, Boolean state) {

        this.id = id;
        this.state = state;
    }

}
