/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.gui.controls.panel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author root
 */
public class TestSlidingPanel extends JPanel {

	public TestSlidingPanel() {

		SlidePaneFactory factory = SlidePaneFactory.getInstance();
		BookForm bookForm = new BookForm();
		factory.add(bookForm, "Slide1", new ImageIcon("images/title.png").getImage(), true);

		bookForm = new BookForm();
		factory.add(bookForm, "Slide2", new ImageIcon("images/title.png").getImage());

		bookForm = new BookForm();
		factory.add(bookForm, "Slide3", new ImageIcon("images/title.png").getImage(), false);

		this.add(factory);

	}

	public static void main(String s[]) {

		JFrame frame = new JFrame();
		frame.setSize(330, 400);

		// frame.setLayout(new FlowLayout());
		JScrollPane pane = new JScrollPane();
		pane.setViewportView(new TestSlidingPanel());
		frame.add(pane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
