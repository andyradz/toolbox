package com.codigo.aplios.toolbox.core.gui;

import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class InvokeExample extends javax.swing.JFrame {

	private javax.swing.JButton badWayOneButton;
	private javax.swing.JButton badWayTwoButton;
	private javax.swing.JButton goodNewWayButton;
	private javax.swing.JButton goodOldWayButton;
	private javax.swing.JLabel statusLabel;

	public InvokeExample() {

		this.initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		java.awt.GridBagConstraints gridBagConstraints;

		this.goodOldWayButton = new javax.swing.JButton();
		this.goodNewWayButton = new javax.swing.JButton();
		this.badWayOneButton = new javax.swing.JButton();
		this.badWayTwoButton = new javax.swing.JButton();
		this.statusLabel = new javax.swing.JLabel();

		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.getContentPane()
			.setLayout(new java.awt.GridBagLayout());

		this.goodOldWayButton.setText("Dobrze (po staremu)");
		this.goodOldWayButton.addActionListener(evt -> InvokeExample.this.goodOldWayButtonActionPerformed(evt));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.25;
		gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
		this.getContentPane()
			.add(this.goodOldWayButton, gridBagConstraints);

		this.goodNewWayButton.setText("Dobrze (po nowemu)");
		this.goodNewWayButton.addActionListener(evt -> InvokeExample.this.goodNewWayButtonActionPerformed(evt));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.25;
		gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
		this.getContentPane()
			.add(this.goodNewWayButton, gridBagConstraints);

		this.badWayOneButton.setText("Pierwszy zły");
		this.badWayOneButton.addActionListener(evt -> InvokeExample.this.badWayOneButtonActionPerformed(evt));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.25;
		gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
		this.getContentPane()
			.add(this.badWayOneButton, gridBagConstraints);

		this.badWayTwoButton.setText("Drugi zły");
		this.badWayTwoButton.addActionListener(evt -> InvokeExample.this.badWayTwoButtonActionPerformed(evt));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.25;
		gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
		this.getContentPane()
			.add(this.badWayTwoButton, gridBagConstraints);

		this.statusLabel.setText("Gotowe");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
		this.getContentPane()
			.add(this.statusLabel, gridBagConstraints);

		this.pack();
	}

	private void goodOldWayButtonActionPerformed(java.awt.event.ActionEvent evt) {

		this.statusLabel.setText("Proszę czekać...");
		this.setButtonsEnabled(false);

		// Wykonujemy coś, co zajmuje dużo czasu, więc odpalamy to w
		// wątku i aktualizujemy stan, gdy zadanie zostane wykonane.
		Thread worker = new Thread() {

			@Override
			public void run() {

				// Coś co zajmuje dużo czasu ... w prawdziwym życiu, może to
				// być na przykład pobieranie danych z bazy danych,
				// wywołanie zdalnej metody, itp.
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ex) {
				}

				// Aktualizujemy status zadania wykorzystując invokeLater().
				SwingUtilities.invokeLater(() -> {

					InvokeExample.this.statusLabel.setText("Gotowe");
					InvokeExample.this.setButtonsEnabled(true);
				});
			}
		};

		worker.start(); // Uruchamiamy wątek, aby nie blokować EDT.
	}

	private void goodNewWayButtonActionPerformed(java.awt.event.ActionEvent evt) {

		this.setButtonsEnabled(false);

		// Wykonujemy to samo zadanie, tym razem korzystając z klasy
		// SwingWorker'a.
		SwingWorker<?, ?> worker = new SwingWorker() {

			@Override
			protected void process(List chunks) {

				// Aktualizujemy status zadania wykorzystując fakt, że metoda
				// process() wykonywana jest w EDT.
				InvokeExample.this.statusLabel.setText(chunks.get(0)
					.toString());
			}

			@Override
			protected Object doInBackground() throws Exception {

				try {
					this.publish("Proszę czekać...");

					Thread.sleep(5000);
				} catch (InterruptedException ex) {
				}

				return null;
			}

			@Override
			protected void done() {

				// Aktualizujemy status zadania wykorzystując fakt, że metoda
				// done() wykonywana jest w ETD.
				InvokeExample.this.statusLabel.setText("Gotowe");
				InvokeExample.this.setButtonsEnabled(true);
			}
		};
		worker.execute(); // Uruchamiamy SwingWorker'a, aby nie blokować EDT.
	}

	private void badWayOneButtonActionPerformed(java.awt.event.ActionEvent evt) {

		this.statusLabel.setText("Proszę czekać...");
		this.setButtonsEnabled(false);

		// Wykonujemy to samo zadanie, tym razem jednak bez wykorzystania
		// dodatkowego wątku.
		try {
			Thread.sleep(5000); // Głodzimy wątek EDT
		} catch (InterruptedException ex) {
		}

		// Aktualizujemy status zadania.
		this.statusLabel.setText("Gotowe");
		this.setButtonsEnabled(true);
	}

	private void badWayTwoButtonActionPerformed(java.awt.event.ActionEvent evt) {

		this.statusLabel.setText("Proszę czekać... ");
		this.setButtonsEnabled(false);

		// Przykład niewłaściwego wykorzystania invokeLater().
		// Runnable nie powinien głodzić wątku EDT.
		SwingUtilities.invokeLater(() -> {

			try {
				// Dispatch thread is starving!
				Thread.sleep(5000);
			} catch (InterruptedException ex) {
			}

			// Aktualizujemy status zadania.
			InvokeExample.this.statusLabel.setText("Gotowe");
			InvokeExample.this.setButtonsEnabled(true);
		});
	}

	// Pozwala włączać i wyłaczać przyciski.
	private void setButtonsEnabled(boolean b) {

		this.goodOldWayButton.setEnabled(b);
		this.goodNewWayButton.setEnabled(b);
		this.badWayOneButton.setEnabled(b);
		this.badWayTwoButton.setEnabled(b);
	}

	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(() -> new InvokeExample().setVisible(true));
	}
}
