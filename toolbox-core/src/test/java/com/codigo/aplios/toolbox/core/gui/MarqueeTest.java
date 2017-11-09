package com.codigo.aplios.toolbox.core.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/** @see http://stackoverflow.com/questions/3617326 */
public class MarqueeTest {

	private void display() {

		JFrame f = new JFrame("MarqueeTest");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String s = "Tomorrow, and tomorrow, and tomorrow, " + "creeps in this petty pace from day to day, "
				+ "to the last syllable of recorded time; ... " + "It is a tale told by an idiot, full of "
				+ "sound and fury signifying nothing.";
		MarqueePanel mp = new MarqueePanel(s, 32);
		f.add(mp);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		mp.start();
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> new MarqueeTest().display());
	}
}

/** Side-scroll n characters of s. */
class MarqueePanel extends JPanel implements ActionListener {

	private static final int RATE = 8;
	private final Timer timer = new Timer(1000 / MarqueePanel.RATE, this);
	private final JLabel label = new JLabel();
	private final String s;
	private final int n;
	private int index;

	public MarqueePanel(String s, int n) {

		if ((s == null) || (n < 1))
			throw new IllegalArgumentException("Null string or n < 1");
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++)
			sb.append(' ');
		this.s = sb + s + sb;
		this.n = n;
		this.label.setFont(new Font("Serif", Font.BOLD, 36));
		this.label.setText(sb.toString());
		this.label.setForeground(Color.RED);
		this.add(this.label);
	}

	public void start() {

		this.timer.start();
	}

	public void stop() {

		this.timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String str = "";
		this.index++;
		if (this.index > (this.s.length() - this.n))
			this.index = 0;
		str = this.s.substring(this.index, this.index + this.n);
		this.label.setText(this.s.substring(this.index, this.index + this.n));
	}
}