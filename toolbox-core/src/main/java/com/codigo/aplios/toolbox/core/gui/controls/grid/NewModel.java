package com.codigo.aplios.toolbox.core.gui.controls.grid;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class NewModel {
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create an Action doing what you want
		Action action = new AbstractAction("F10 Quit") {
			{
				this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
			}

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("F10 was clicked " + e.paramString());
			}
		};
		int zmiena = 1 + 1 + (1 * 4);

		JButton btn = new JButton(action);
		btn.setSize(100, 100);
		btn.getActionMap()
			.put("quit", action);
		btn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put((KeyStroke) action.getValue(Action.ACCELERATOR_KEY), "quit");

		frame.getRootPane()
			.add(btn);
		frame.setVisible(true);
	}
}
