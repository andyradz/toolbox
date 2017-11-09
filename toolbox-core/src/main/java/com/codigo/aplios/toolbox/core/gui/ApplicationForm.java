package com.codigo.aplios.toolbox.core.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
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
 * Klasa sie chomik
 *
 * @author andrzej.radziszewski
 *
 *         Code file : ApplicationForm.java Create date: 04.05.2017
 */
public class ApplicationForm extends JFrame {

	private JPanel contentPane;

	private JTextField txtFilePath;

	private JTextField txtMD2HashCode;

	private JTextField txtSHA256HashCode;

	private JTextField txtMD5HashCode;

	private JTextField txtSHA384HashCode;

	private JTextField txtSHA1HashCode;

	private JTextField txtSHA512HashCode;

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
		this.setMinimumSize(new Dimension(800, 700));
		this.setSize(new Dimension(800, 680));
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
		gbl_panel_3.rowHeights = new int[] { 25, 25, 0, 28, 25, 0 };
		gbl_panel_3.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
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

		this.txtFilePath = new JTextField();
		this.txtFilePath.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		this.txtFilePath.setMinimumSize(new Dimension(100, 20));
		GridBagConstraints gbc_txtFilePath = new GridBagConstraints();
		gbc_txtFilePath.insets = new Insets(0, 0, 5, 5);
		gbc_txtFilePath.fill = GridBagConstraints.BOTH;
		gbc_txtFilePath.gridx = 0;
		gbc_txtFilePath.gridy = 1;
		panel_3.add(this.txtFilePath, gbc_txtFilePath);
		this.txtFilePath.setColumns(10);

		JButton btnStart = new JButton("Wybierz...");
		btnStart.setFont(new Font("Segoe UI", Font.BOLD, 12));
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.fill = GridBagConstraints.BOTH;
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 1;
		gbc_btnStart.gridy = 1;
		panel_3.add(btnStart, gbc_btnStart);

		JButton btnNewButton_1 = new JButton("Hash");
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		panel_3.add(btnNewButton_1, gbc_btnNewButton_1);

		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.gridwidth = 3;
		gbc_panel_9.insets = new Insets(0, 0, 5, 0);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 2;
		panel_3.add(panel_9, gbc_panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_9.rowHeights = new int[] { 25, 25, 25, 0 };
		gbl_panel_9.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_9.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_9.setLayout(gbl_panel_9);

		JLabel lblciekaPliku = new JLabel("Ścieżka  :\r\n");
		lblciekaPliku.setForeground(new Color(0, 0, 128));
		lblciekaPliku.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_lblciekaPliku = new GridBagConstraints();
		gbc_lblciekaPliku.anchor = GridBagConstraints.WEST;
		gbc_lblciekaPliku.insets = new Insets(0, 0, 5, 5);
		gbc_lblciekaPliku.gridx = 0;
		gbc_lblciekaPliku.gridy = 0;
		panel_9.add(lblciekaPliku, gbc_lblciekaPliku);

		JLabel lblCplikizmiennebackuptsl = new JLabel("C:\\Pliki\\zmienne\\");
		lblCplikizmiennebackuptsl.setForeground(new Color(0, 0, 255));
		lblCplikizmiennebackuptsl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCplikizmiennebackuptsl = new GridBagConstraints();
		gbc_lblCplikizmiennebackuptsl.insets = new Insets(0, 0, 5, 0);
		gbc_lblCplikizmiennebackuptsl.anchor = GridBagConstraints.WEST;
		gbc_lblCplikizmiennebackuptsl.gridx = 1;
		gbc_lblCplikizmiennebackuptsl.gridy = 0;
		panel_9.add(lblCplikizmiennebackuptsl, gbc_lblCplikizmiennebackuptsl);

		JLabel lblNazwaPliku = new JLabel("Nazwa   :\r\n");
		lblNazwaPliku.setForeground(new Color(0, 0, 128));
		lblNazwaPliku.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNazwaPliku = new GridBagConstraints();
		gbc_lblNazwaPliku.anchor = GridBagConstraints.WEST;
		gbc_lblNazwaPliku.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwaPliku.gridx = 0;
		gbc_lblNazwaPliku.gridy = 1;
		panel_9.add(lblNazwaPliku, gbc_lblNazwaPliku);

		JLabel lblBackuptsl = new JLabel("backup.tsl");
		lblBackuptsl.setForeground(Color.BLUE);
		lblBackuptsl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_lblBackuptsl = new GridBagConstraints();
		gbc_lblBackuptsl.fill = GridBagConstraints.BOTH;
		gbc_lblBackuptsl.insets = new Insets(0, 0, 5, 0);
		gbc_lblBackuptsl.gridx = 1;
		gbc_lblBackuptsl.gridy = 1;
		panel_9.add(lblBackuptsl, gbc_lblBackuptsl);

		JLabel lblRozmiar = new JLabel("Rozmiar :\r\n");
		lblRozmiar.setForeground(new Color(0, 0, 128));
		lblRozmiar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_lblRozmiar = new GridBagConstraints();
		gbc_lblRozmiar.insets = new Insets(0, 0, 0, 5);
		gbc_lblRozmiar.gridx = 0;
		gbc_lblRozmiar.gridy = 2;
		panel_9.add(lblRozmiar, gbc_lblRozmiar);

		JLabel lblGb = new JLabel("1,2 Gb");
		lblGb.setForeground(Color.BLUE);
		lblGb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_lblGb = new GridBagConstraints();
		gbc_lblGb.anchor = GridBagConstraints.WEST;
		gbc_lblGb.fill = GridBagConstraints.VERTICAL;
		gbc_lblGb.gridx = 1;
		gbc_lblGb.gridy = 2;
		panel_9.add(lblGb, gbc_lblGb);

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
		gbc_progressBar.gridy = 3;
		panel_3.add(progressBar, gbc_progressBar);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EmptyBorder(5, 0, 0, 0));
		panel_8.setOpaque(false);
		panel_8.setRequestFocusEnabled(false);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.gridwidth = 3;
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 4;
		panel_3.add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[] { 288, 290, 0 };
		gbl_panel_8.rowHeights = new int[] { 25, 25, 25, 25, 25, 25, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_8.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_8.rowWeights =
				new double[]
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_8.setLayout(gbl_panel_8);

		JCheckBox chckbxSumaKontrolnaMd_1 = new JCheckBox("Suma kontrolna MD2:");
		chckbxSumaKontrolnaMd_1.setOpaque(false);
		chckbxSumaKontrolnaMd_1.setForeground(new Color(0, 0, 128));
		chckbxSumaKontrolnaMd_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_chckbxSumaKontrolnaMd_1 = new GridBagConstraints();
		gbc_chckbxSumaKontrolnaMd_1.fill = GridBagConstraints.VERTICAL;
		gbc_chckbxSumaKontrolnaMd_1.anchor = GridBagConstraints.WEST;
		gbc_chckbxSumaKontrolnaMd_1.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxSumaKontrolnaMd_1.gridx = 0;
		gbc_chckbxSumaKontrolnaMd_1.gridy = 0;
		panel_8.add(chckbxSumaKontrolnaMd_1, gbc_chckbxSumaKontrolnaMd_1);

		this.txtMD2HashCode = new JTextField();
		this.txtMD2HashCode.setForeground(new Color(220, 20, 60));
		this.txtMD2HashCode.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtMD2HashCode.setText("0000000000000000000000000000000000000");
		this.txtMD2HashCode.setFocusTraversalKeysEnabled(false);
		this.txtMD2HashCode.setFocusable(false);
		this.txtMD2HashCode.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.txtMD2HashCode.setRequestFocusEnabled(false);
		this.txtMD2HashCode.setVerifyInputWhenFocusTarget(false);
		GridBagConstraints gbc_txtMD2HashCode = new GridBagConstraints();
		gbc_txtMD2HashCode.gridwidth = 2;
		gbc_txtMD2HashCode.insets = new Insets(0, 0, 5, 0);
		gbc_txtMD2HashCode.fill = GridBagConstraints.BOTH;
		gbc_txtMD2HashCode.gridx = 0;
		gbc_txtMD2HashCode.gridy = 1;
		panel_8.add(this.txtMD2HashCode, gbc_txtMD2HashCode);
		this.txtMD2HashCode.setColumns(10);

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

		this.txtMD5HashCode = new JTextField();
		this.txtMD5HashCode.setForeground(new Color(220, 20, 60));
		this.txtMD5HashCode.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtMD5HashCode.setText("0000000000000000000000000000000000000");
		this.txtMD5HashCode.setVerifyInputWhenFocusTarget(false);
		this.txtMD5HashCode.setRequestFocusEnabled(false);
		this.txtMD5HashCode.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.txtMD5HashCode.setFocusable(false);
		this.txtMD5HashCode.setFocusTraversalKeysEnabled(false);
		this.txtMD5HashCode.setColumns(10);
		GridBagConstraints gbc_txtMD5HashCode = new GridBagConstraints();
		gbc_txtMD5HashCode.gridwidth = 2;
		gbc_txtMD5HashCode.insets = new Insets(0, 0, 5, 0);
		gbc_txtMD5HashCode.fill = GridBagConstraints.BOTH;
		gbc_txtMD5HashCode.gridx = 0;
		gbc_txtMD5HashCode.gridy = 3;
		panel_8.add(this.txtMD5HashCode, gbc_txtMD5HashCode);

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

		this.txtSHA1HashCode = new JTextField();
		this.txtSHA1HashCode.setForeground(new Color(220, 20, 60));
		this.txtSHA1HashCode.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtSHA1HashCode.setText("0000000000000000000000000000000000000");
		this.txtSHA1HashCode.setVerifyInputWhenFocusTarget(false);
		this.txtSHA1HashCode.setRequestFocusEnabled(false);
		this.txtSHA1HashCode.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.txtSHA1HashCode.setFocusable(false);
		this.txtSHA1HashCode.setFocusTraversalKeysEnabled(false);
		this.txtSHA1HashCode.setColumns(10);
		GridBagConstraints gbc_txtSHA1HashCode = new GridBagConstraints();
		gbc_txtSHA1HashCode.gridwidth = 2;
		gbc_txtSHA1HashCode.insets = new Insets(0, 0, 5, 0);
		gbc_txtSHA1HashCode.fill = GridBagConstraints.BOTH;
		gbc_txtSHA1HashCode.gridx = 0;
		gbc_txtSHA1HashCode.gridy = 5;
		panel_8.add(this.txtSHA1HashCode, gbc_txtSHA1HashCode);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Suma kontrolna SHA256:");
		chckbxNewCheckBox.setForeground(new Color(0, 0, 128));
		chckbxNewCheckBox.setOpaque(false);
		chckbxNewCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.BOTH;
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 6;
		panel_8.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

		this.txtSHA256HashCode = new JTextField();
		this.txtSHA256HashCode.setForeground(new Color(220, 20, 60));
		this.txtSHA256HashCode.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtSHA256HashCode.setText("0000000000000000000000000000000000000");
		this.txtSHA256HashCode.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.txtSHA256HashCode.setFocusTraversalKeysEnabled(false);
		this.txtSHA256HashCode.setFocusable(false);
		this.txtSHA256HashCode.setRequestFocusEnabled(false);
		this.txtSHA256HashCode.setVerifyInputWhenFocusTarget(false);
		GridBagConstraints gbc_txtSHA256HashCode = new GridBagConstraints();
		gbc_txtSHA256HashCode.gridwidth = 2;
		gbc_txtSHA256HashCode.insets = new Insets(0, 0, 5, 5);
		gbc_txtSHA256HashCode.fill = GridBagConstraints.BOTH;
		gbc_txtSHA256HashCode.gridx = 0;
		gbc_txtSHA256HashCode.gridy = 7;
		panel_8.add(this.txtSHA256HashCode, gbc_txtSHA256HashCode);
		this.txtSHA256HashCode.setColumns(10);

		JCheckBox chckbxSumaKontrolnaSh = new JCheckBox("Suma kontrolna SH384:");
		chckbxSumaKontrolnaSh.setForeground(new Color(0, 0, 128));
		chckbxSumaKontrolnaSh.setOpaque(false);
		chckbxSumaKontrolnaSh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_chckbxSumaKontrolnaSh = new GridBagConstraints();
		gbc_chckbxSumaKontrolnaSh.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxSumaKontrolnaSh.anchor = GridBagConstraints.WEST;
		gbc_chckbxSumaKontrolnaSh.gridx = 0;
		gbc_chckbxSumaKontrolnaSh.gridy = 8;
		panel_8.add(chckbxSumaKontrolnaSh, gbc_chckbxSumaKontrolnaSh);

		this.txtSHA384HashCode = new JTextField();
		this.txtSHA384HashCode.setForeground(new Color(220, 20, 60));
		this.txtSHA384HashCode.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtSHA384HashCode.setText("0000000000000000000000000000000000000");
		this.txtSHA384HashCode.setVerifyInputWhenFocusTarget(false);
		this.txtSHA384HashCode.setRequestFocusEnabled(false);
		this.txtSHA384HashCode.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.txtSHA384HashCode.setFocusable(false);
		this.txtSHA384HashCode.setFocusTraversalKeysEnabled(false);
		this.txtSHA384HashCode.setColumns(10);
		GridBagConstraints gbc_txtSHA384HashCode = new GridBagConstraints();
		gbc_txtSHA384HashCode.gridwidth = 2;
		gbc_txtSHA384HashCode.insets = new Insets(0, 0, 5, 5);
		gbc_txtSHA384HashCode.fill = GridBagConstraints.BOTH;
		gbc_txtSHA384HashCode.gridx = 0;
		gbc_txtSHA384HashCode.gridy = 9;
		panel_8.add(this.txtSHA384HashCode, gbc_txtSHA384HashCode);

		JCheckBox chckbxSumaKontrolnaSha_1 = new JCheckBox("Suma kontrolna SHA512:");
		chckbxSumaKontrolnaSha_1.setForeground(new Color(0, 0, 128));
		chckbxSumaKontrolnaSha_1.setOpaque(false);
		chckbxSumaKontrolnaSha_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		GridBagConstraints gbc_chckbxSumaKontrolnaSha_1 = new GridBagConstraints();
		gbc_chckbxSumaKontrolnaSha_1.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxSumaKontrolnaSha_1.anchor = GridBagConstraints.WEST;
		gbc_chckbxSumaKontrolnaSha_1.gridx = 0;
		gbc_chckbxSumaKontrolnaSha_1.gridy = 10;
		panel_8.add(chckbxSumaKontrolnaSha_1, gbc_chckbxSumaKontrolnaSha_1);

		this.txtSHA512HashCode = new JTextField();
		this.txtSHA512HashCode.setForeground(new Color(220, 20, 60));
		this.txtSHA512HashCode.setHorizontalAlignment(SwingConstants.LEFT);
		this.txtSHA512HashCode.setText("0000000000000000000000000000000000000");
		this.txtSHA512HashCode.setVerifyInputWhenFocusTarget(false);
		this.txtSHA512HashCode.setRequestFocusEnabled(false);
		this.txtSHA512HashCode.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.txtSHA512HashCode.setFocusable(false);
		this.txtSHA512HashCode.setFocusTraversalKeysEnabled(false);
		this.txtSHA512HashCode.setColumns(10);
		GridBagConstraints gbc_txtSHA512HashCode = new GridBagConstraints();
		gbc_txtSHA512HashCode.gridwidth = 2;
		gbc_txtSHA512HashCode.insets = new Insets(0, 0, 0, 5);
		gbc_txtSHA512HashCode.fill = GridBagConstraints.BOTH;
		gbc_txtSHA512HashCode.gridx = 0;
		gbc_txtSHA512HashCode.gridy = 11;
		panel_8.add(this.txtSHA512HashCode, gbc_txtSHA512HashCode);
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
		panel_7.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.addTab("Sekwencja Hash", null, panel_7, null);
		//
		// btnNewButton_1.addActionListener(new ActionListener() {
		//
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// final ChecksumWorker md5HashWorker =
		// new ChecksumWorker(Checksum.HashDigit.MD2,
		// Paths.get(txtFilePath.getText()));
		// md5HashWorker.execute();
		// md5HashWorker.addPropertyChangeListener(new
		// PropertyChangeLlcheckistener() {
		//
		// @Override
		// public void propertyChange(PropertyChangeEvent evt) {
		// final SwingWorker.StateValue state =
		// SwingWorker.StateValue.class.cast(evt.getNewValue());
		// if (0 == SwingWorker.StateValue.DONE.compareTo(state))
		// try {
		// txtMD2HashCode.setText(md5HashWorker.get());
		// }
		// catch (InterruptedException | ExecutionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// });
		// }
		// });

		// btnNewButton_1.addActionListener((action) -> {
		// final ChecksumWorker md5HashWorker = new ChecksumWorker(Checksum.HashDigit.MD2,
		// Paths.get(txtFilePath.getText()));
		// md5HashWorker.execute();
		// md5HashWorker.addPropertyChangeListener((PropertyChangeEvent evt) -> {
		// final SwingWorker.StateValue state1 = SwingWorker.StateValue.class.cast(evt.getNewValue());
		// if (0 == SwingWorker.StateValue.DONE.compareTo(state1))
		// try {
		// txtMD2HashCode.setText(md5HashWorker.get());
		// } catch (InterruptedException | ExecutionException e) {
		// // TODO Auto-generated catch block
		// }
		// });
		// });

		// btnNewButton_1.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// final ChecksumWorker sha1HashWorker =
		// new ChecksumWorker(Checksum.HashDigit.SHA1,
		// Paths.get(txtFilePath.getText()));
		// sha1HashWorker.execute();
		//
		// sha1HashWorker.addPropertyChangeListener(new PropertyChangeListener()
		// {
		//
		// @Override
		// public void propertyChange(PropertyChangeEvent evt) {
		// final SwingWorker.StateValue state =
		// SwingWorker.StateValue.class.cast(evt.getNewValue());
		// if (0 == SwingWorker.StateValue.DONE.compareTo(state))
		// try {
		// txtSHA1HashCode.setText(sha1HashWorker.get());
		// }
		// catch (InterruptedException | ExecutionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// });
		// }
		// });
		// btnNewButton_1.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// final ChecksumWorker sha1HashWorker =
		// new ChecksumWorker(Checksum.HashDigit.SHA256,
		// Paths.get(txtFilePath.getText()));
		// sha1HashWorker.execute();
		//
		// sha1HashWorker.addPropertyChangeListener(new PropertyChangeListener()
		// {
		//
		// @Override
		// public void propertyChange(PropertyChangeEvent evt) {
		// final SwingWorker.StateValue state =
		// SwingWorker.StateValue.class.cast(evt.getNewValue());
		// if (0 == SwingWorker.StateValue.DONE.compareTo(state))
		// try {
		// txtSHA256HashCode.setText(sha1HashWorker.get());
		// }
		// catch (InterruptedException | ExecutionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// });
		// }
		// });
		// btnNewButton_1.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// final ChecksumWorker sha1HashWorker =
		// new ChecksumWorker(Checksum.HashDigit.SHA384,
		// Paths.get(txtFilePath.getText()));
		// sha1HashWorker.execute();
		//
		// sha1HashWorker.addPropertyChangeListener(new PropertyChangeListener()
		// {
		//
		// @Override
		// public void propertyChange(PropertyChangeEvent evt) {
		// final SwingWorker.StateValue state =
		// SwingWorker.StateValue.class.cast(evt.getNewValue());
		// if (0 == SwingWorker.StateValue.DONE.compareTo(state))
		// try {
		// txtSHA384HashCode.setText(sha1HashWorker.get());
		// }
		// catch (InterruptedException | ExecutionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// });
		// }
		// });
		// btnNewButton_1.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// final ChecksumWorker sha1HashWorker =
		// new ChecksumWorker(Checksum.HashDigit.SHA512,
		// Paths.get(txtFilePath.getText()));
		// sha1HashWorker.execute();
		// sha1HashWorker.addPropertyChangeListener(new PropertyChangeListener()
		// {
		//
		// @Override
		// public void propertyChange(PropertyChangeEvent evt) {
		// final SwingWorker.StateValue state =
		// SwingWorker.StateValue.class.cast(evt.getNewValue());
		// if (0 == SwingWorker.StateValue.DONE.compareTo(state))
		// try {
		// txtSHA512HashCode.setText(sha1HashWorker.get());
		// }
		// catch (InterruptedException | ExecutionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// });
		// }
		// });
		btnStart.addActionListener(e -> {
			final FileDialog fileDialog = new FileDialog(this, "Otrzórz", FileDialog.LOAD);
			fileDialog.setVisible(true);
			this.txtFilePath.setText("");
			this.txtMD2HashCode.setText("");
			this.txtMD5HashCode.setText("");
			this.txtSHA1HashCode.setText("");
			this.txtSHA256HashCode.setText("");
			this.txtSHA384HashCode.setText("");
			this.txtSHA512HashCode.setText("");
			this.txtFilePath.setText(fileDialog.getDirectory() + fileDialog.getFile());

		});

	}

}
