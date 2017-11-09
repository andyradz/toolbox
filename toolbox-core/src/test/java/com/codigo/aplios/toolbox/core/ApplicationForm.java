package com.codigo.aplios.toolbox.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 * @author andrzej.radziszewski
 *
 *         Code file : ApplicationForm.java Create date: 04.05.2017
 */
public class ApplicationForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(() -> {

			try {
				ApplicationForm.createUI();

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static void createUI() {

		ApplicationForm frame = new ApplicationForm();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ApplicationForm() {

		this.setTitle("Checksum Tool v4.1");
		this.setMinimumSize(new Dimension(800, 600));
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.setResizable(false);
		this.contentPane = new JPanel();
		this.contentPane.setMinimumSize(new Dimension(800, 600));
		this.contentPane.setBorder(null);
		this.setContentPane(this.contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		this.contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		this.contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 150, 0 };
		gbl_panel.rowHeights = new int[] { 70, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 0, 1, 0, new Color(192, 192, 192)));
		panel_1.setBackground(SystemColor.menu);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 141, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 16, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblUstawieniaPodstawowerwerwerwerwere = new JLabel("Ustawienia podstawowe:");
		lblUstawieniaPodstawowerwerwerwerwere.setHorizontalAlignment(SwingConstants.CENTER);
		lblUstawieniaPodstawowerwerwerwerwere.setForeground(Color.DARK_GRAY);
		lblUstawieniaPodstawowerwerwerwerwere.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblUstawieniaPodstawowerwerwerwerwere.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblUstawieniaPodstawowerwerwerwerwere.setBackground(SystemColor.control);
		GridBagConstraints gbc_lblUstawieniaPodstawowerwerwerwerwere = new GridBagConstraints();
		gbc_lblUstawieniaPodstawowerwerwerwerwere.fill = GridBagConstraints.BOTH;
		gbc_lblUstawieniaPodstawowerwerwerwerwere.gridx = 0;
		gbc_lblUstawieniaPodstawowerwerwerwerwere.gridy = 0;
		panel_1.add(lblUstawieniaPodstawowerwerwerwerwere, gbc_lblUstawieniaPodstawowerwerwerwerwere);

		JLabel lblWybierzPodstawoweOpcje = new JLabel("Wybierz podstawowe opcje dotyczące działania JExplorer");
		lblWybierzPodstawoweOpcje.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWybierzPodstawoweOpcje.setForeground(Color.BLACK);
		lblWybierzPodstawoweOpcje.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblWybierzPodstawoweOpcje.setBorder(new EmptyBorder(0, 5, 0, 0));
		lblWybierzPodstawoweOpcje.setBackground(SystemColor.menu);
		GridBagConstraints gbc_lblWybierzPodstawoweOpcje = new GridBagConstraints();
		gbc_lblWybierzPodstawoweOpcje.gridx = 1;
		gbc_lblWybierzPodstawoweOpcje.gridy = 0;
		panel_1.add(lblWybierzPodstawoweOpcje, gbc_lblWybierzPodstawoweOpcje);

		JPanel panel_2 = new JPanel();
		panel_2.setRequestFocusEnabled(false);
		panel_2.setBorder(new EmptyBorder(10, 16, 16, 16));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setVerifyInputWhenFocusTarget(false);
		tabbedPane.setRequestFocusEnabled(false);
		tabbedPane.setFocusTraversalKeysEnabled(false);
		tabbedPane.setFocusable(false);
		tabbedPane.setBorder(null);
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		panel_2.add(tabbedPane, gbc_tabbedPane);

		JPanel panel_3 = new JPanel();
		panel_3.setVerifyInputWhenFocusTarget(false);
		panel_3.setOpaque(false);
		panel_3.setRequestFocusEnabled(false);
		panel_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_3.setBorder(new EmptyBorder(15, 10, 10, 10));
		tabbedPane.addTab("Sumy kontrolne pliku", null, panel_3, null);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 328, 140, 140, 0 };
		gbl_panel_3.rowHeights = new int[] { 25, 25, 28, 25, 0 };
		gbl_panel_3.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		JLabel lblNewLabel = new JLabel("Wybierz plik, aby wyliczyć sumy kontrolne");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_3.add(lblNewLabel, gbc_lblNewLabel);

		this.textField = new JTextField();
		this.textField.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panel_3.add(this.textField, gbc_textField);
		this.textField.setColumns(10);

		JButton btnNewButton = new JButton("Wybierz...");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		panel_3.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Hash");
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		panel_3.add(btnNewButton_1, gbc_btnNewButton_1);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setStringPainted(true);
		progressBar.setBorder(new EmptyBorder(0, 0, 0, 0));
		progressBar.setOpaque(true);
		progressBar.setBackground(Color.BLACK);
		progressBar.setValue(50);
		progressBar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		progressBar.setString("50%");
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar.gridwidth = 3;
		gbc_progressBar.fill = GridBagConstraints.BOTH;
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 2;
		panel_3.add(progressBar, gbc_progressBar);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(5, 0, 0, 0));
		panel_8.setOpaque(false);
		panel_8.setRequestFocusEnabled(false);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.gridwidth = 3;
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 3;
		panel_3.add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[] { 288, 290, 0 };
		gbl_panel_8.rowHeights = new int[] { 25, 25, 25, 25, 25, 25, 0 };
		gbl_panel_8.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_8.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_8.setLayout(gbl_panel_8);

		JLabel lblNewLabel_1 = new JLabel("Rozmiar pliku:\r\n");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_8.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Suma kontrolna SHA256:");
		chckbxNewCheckBox.setForeground(new Color(0, 0, 128));
		chckbxNewCheckBox.setOpaque(false);
		chckbxNewCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.BOTH;
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 0;
		panel_8.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

		this.textField_1 = new JTextField();
		this.textField_1.setForeground(new Color(220, 20, 60));
		this.textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		this.textField_1.setText("0000000000000000000000000000000000000");
		this.textField_1.setFocusTraversalKeysEnabled(false);
		this.textField_1.setFocusable(false);
		this.textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.textField_1.setRequestFocusEnabled(false);
		this.textField_1.setVerifyInputWhenFocusTarget(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 1;
		panel_8.add(this.textField_1, gbc_textField_1);
		this.textField_1.setColumns(10);

		this.textField_2 = new JTextField();
		this.textField_2.setForeground(new Color(220, 20, 60));
		this.textField_2.setHorizontalAlignment(SwingConstants.LEFT);
		this.textField_2.setText("0000000000000000000000000000000000000");
		this.textField_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.textField_2.setFocusTraversalKeysEnabled(false);
		this.textField_2.setFocusable(false);
		this.textField_2.setRequestFocusEnabled(false);
		this.textField_2.setVerifyInputWhenFocusTarget(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 1;
		panel_8.add(this.textField_2, gbc_textField_2);
		this.textField_2.setColumns(10);

		JCheckBox chckbxSumaKontrolnaMd = new JCheckBox("Suma kontrolna MD5:");
		chckbxSumaKontrolnaMd.setForeground(new Color(0, 0, 128));
		chckbxSumaKontrolnaMd.setOpaque(false);
		chckbxSumaKontrolnaMd.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_chckbxSumaKontrolnaMd = new GridBagConstraints();
		gbc_chckbxSumaKontrolnaMd.anchor = GridBagConstraints.WEST;
		gbc_chckbxSumaKontrolnaMd.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxSumaKontrolnaMd.gridx = 0;
		gbc_chckbxSumaKontrolnaMd.gridy = 2;
		panel_8.add(chckbxSumaKontrolnaMd, gbc_chckbxSumaKontrolnaMd);

		JCheckBox chckbxSumaKontrolnaSh = new JCheckBox("Suma kontrolna SH384:");
		chckbxSumaKontrolnaSh.setForeground(new Color(0, 0, 128));
		chckbxSumaKontrolnaSh.setOpaque(false);
		chckbxSumaKontrolnaSh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_chckbxSumaKontrolnaSh = new GridBagConstraints();
		gbc_chckbxSumaKontrolnaSh.anchor = GridBagConstraints.WEST;
		gbc_chckbxSumaKontrolnaSh.gridx = 1;
		gbc_chckbxSumaKontrolnaSh.gridy = 2;
		panel_8.add(chckbxSumaKontrolnaSh, gbc_chckbxSumaKontrolnaSh);

		this.textField_3 = new JTextField();
		this.textField_3.setForeground(new Color(220, 20, 60));
		this.textField_3.setHorizontalAlignment(SwingConstants.LEFT);
		this.textField_3.setText("0000000000000000000000000000000000000");
		this.textField_3.setVerifyInputWhenFocusTarget(false);
		this.textField_3.setRequestFocusEnabled(false);
		this.textField_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.textField_3.setFocusable(false);
		this.textField_3.setFocusTraversalKeysEnabled(false);
		this.textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.BOTH;
		gbc_textField_3.gridx = 0;
		gbc_textField_3.gridy = 3;
		panel_8.add(this.textField_3, gbc_textField_3);

		this.textField_4 = new JTextField();
		this.textField_4.setForeground(new Color(220, 20, 60));
		this.textField_4.setHorizontalAlignment(SwingConstants.LEFT);
		this.textField_4.setText("0000000000000000000000000000000000000");
		this.textField_4.setVerifyInputWhenFocusTarget(false);
		this.textField_4.setRequestFocusEnabled(false);
		this.textField_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.textField_4.setFocusable(false);
		this.textField_4.setFocusTraversalKeysEnabled(false);
		this.textField_4.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.BOTH;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 3;
		panel_8.add(this.textField_4, gbc_textField_4);

		JCheckBox chckbxSumaKontrolnaSha = new JCheckBox("Suma kontrolna SHA1:");
		chckbxSumaKontrolnaSha.setForeground(new Color(0, 0, 128));
		chckbxSumaKontrolnaSha.setOpaque(false);
		chckbxSumaKontrolnaSha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_chckbxSumaKontrolnaSha = new GridBagConstraints();
		gbc_chckbxSumaKontrolnaSha.anchor = GridBagConstraints.WEST;
		gbc_chckbxSumaKontrolnaSha.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxSumaKontrolnaSha.gridx = 0;
		gbc_chckbxSumaKontrolnaSha.gridy = 4;
		panel_8.add(chckbxSumaKontrolnaSha, gbc_chckbxSumaKontrolnaSha);

		JCheckBox chckbxSumaKontrolnaSha_1 = new JCheckBox("Suma kontrolna SHA512:");
		chckbxSumaKontrolnaSha_1.setForeground(new Color(0, 0, 128));
		chckbxSumaKontrolnaSha_1.setOpaque(false);
		chckbxSumaKontrolnaSha_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_chckbxSumaKontrolnaSha_1 = new GridBagConstraints();
		gbc_chckbxSumaKontrolnaSha_1.anchor = GridBagConstraints.WEST;
		gbc_chckbxSumaKontrolnaSha_1.gridx = 1;
		gbc_chckbxSumaKontrolnaSha_1.gridy = 4;
		panel_8.add(chckbxSumaKontrolnaSha_1, gbc_chckbxSumaKontrolnaSha_1);

		this.textField_5 = new JTextField();
		this.textField_5.setForeground(new Color(220, 20, 60));
		this.textField_5.setHorizontalAlignment(SwingConstants.LEFT);
		this.textField_5.setText("0000000000000000000000000000000000000");
		this.textField_5.setVerifyInputWhenFocusTarget(false);
		this.textField_5.setRequestFocusEnabled(false);
		this.textField_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.textField_5.setFocusable(false);
		this.textField_5.setFocusTraversalKeysEnabled(false);
		this.textField_5.setColumns(10);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 0, 5);
		gbc_textField_5.fill = GridBagConstraints.BOTH;
		gbc_textField_5.gridx = 0;
		gbc_textField_5.gridy = 5;
		panel_8.add(this.textField_5, gbc_textField_5);

		this.textField_6 = new JTextField();
		this.textField_6.setForeground(new Color(220, 20, 60));
		this.textField_6.setHorizontalAlignment(SwingConstants.LEFT);
		this.textField_6.setText("0000000000000000000000000000000000000");
		this.textField_6.setVerifyInputWhenFocusTarget(false);
		this.textField_6.setRequestFocusEnabled(false);
		this.textField_6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.textField_6.setFocusable(false);
		this.textField_6.setFocusTraversalKeysEnabled(false);
		this.textField_6.setColumns(10);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.fill = GridBagConstraints.BOTH;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 5;
		panel_8.add(this.textField_6, gbc_textField_6);
		tabbedPane.setEnabledAt(0, true);

		JPanel panel_4 = new JPanel();
		panel_4.setVerifyInputWhenFocusTarget(false);
		panel_4.setRequestFocusEnabled(false);
		panel_4.setOpaque(false);
		panel_4.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.addTab("Zweryfikuj sumy kontrolne pliku", null, panel_4, null);

		JPanel panel_5 = new JPanel();
		panel_5.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_5.setFocusable(false);
		panel_5.setFocusTraversalKeysEnabled(false);
		panel_5.setRequestFocusEnabled(false);
		panel_5.setOpaque(false);
		panel_5.setVerifyInputWhenFocusTarget(false);
		panel_5.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.addTab("Porównaj pliki", null, panel_5, null);

		JPanel panel_6 = new JPanel();
		panel_6.setVerifyInputWhenFocusTarget(false);
		panel_6.setOpaque(false);
		panel_6.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_6.setFocusable(false);
		panel_6.setFocusTraversalKeysEnabled(false);
		panel_6.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.addTab("Przeskanuj folder", null, panel_6, null);

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_7.setRequestFocusEnabled(false);
		panel_7.setVerifyInputWhenFocusTarget(false);
		panel_7.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_7.setFocusable(false);
		panel_7.setFocusTraversalKeysEnabled(false);
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		panel_7.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.addTab("Sekwencja Hash", null, panel_7, null);

	}
}
