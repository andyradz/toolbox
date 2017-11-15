/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.core.io;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.ActionMapUIResource;

/**
 *
 * @author andrzej.radziszewski
 */
public class FileOS {

    private JFrame frmToolbox;

    /**
     * Launch the application.
     *
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws IllegalAccessException {

        // FileTableDemo.scrollToVisible(table, 0, 0);
        // frame.setSize(1024, 860);
        // frame.setLocationRelativeTo(null);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setVisible(true);
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
                FileOS window = new FileOS();
                // window.frmToolbox.pack();
                window.frmToolbox.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     *
     * @throws IllegalAccessException
     */
    public FileOS() throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        this.initialize();

        FileSystemView fsv = FileSystemView.getFileSystemView();

        File[] drives = File.listRoots();
        if ((drives != null) && (drives.length > 0))
            // this.comboBox_1.removeAll();
            for (File aDrive : drives) {
                // this.comboBox_1.addItem(aDrive.getName());
                // this.comboBox.addItem(aDrive.getName());
                // System.out.println("Drive Letter: " + aDrive);
                // System.out.println("\tType: " + fsv.getSystemTypeDescription(aDrive));
                // System.out.println("\tTotal space: " + aDrive.getTotalSpace());
                // System.out.println("\tFree space: " + aDrive.getFreeSpace());
                // System.out.println();
            }

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
                if ("Windows".equals(info.getName())) {
                    // UIManager.setLookAndFeel(info.getClassName());
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    // FontUIResource font = new FontUIResource("Tahoma",
                    // Font.PLAIN, 12);
                    // UIManager.put("Table.font", font);

                    // UIManager.put("Table.foreground", Color.RED);
                    break;
                }
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {

        }

        // Figure out what directory to display
        File dir;
        // dir = new File(System.getProperty("user.home"));
        dir = new File("C:");

        // Create a TableModel object to represent the contents of the directory
        // this.model = new FileTableModel(dir);
        // Display it all in a scrolling window and make the window appear
        // JFrame frame = new JFrame("FileTableDemo");
        JForm form = new JForm();
        JFrame frame = form.makeFrame("FileTableDemo");
        frame.addWindowListener(new ActionExit(new JButton(), "1", "1"));

        // Create a JTable and tell it to display our model
        // FileTable table = new FileTable(model);
        // table.setModel(model);
        // table.setRowHeight(24);
        // table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        //
        // //createKeybindings(table);
        // table.setAutoCreateRowSorter(true);
        // table.getColumnModel().getColumn(0).setMaxWidth(20);
        // table.getColumnModel().getColumn(2).setWidth(10);
        // table.clearSelection();
        // table.setIntercellSpacing(new Dimension(0, 1));
        // table.setSelectionBackground(Color.BLACK);
        // table.setSelectionForeground(Color.WHITE);
        // frame.getContentPane().add(new JScrollPane(table));
        //
        // // table.setShowHorizontalLines(false);
        //
        // table.setShowVerticalLines(false);
        // table.setFocusable(true);
        // table.getColumnModel().getColumn(0).setCellRenderer(new ImageRender());
        // table.getColumnModel().getColumn(1).setCellRenderer(new FileRender());
        // table.requestFocus(true);
        // table.changeSelection(0, 0, false, false);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        this.frmToolbox = new JFrame();
        this.frmToolbox.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(FileOS.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Bold.png")));
        this.frmToolbox.setTitle("JExplorer ver. 1.0.0.0 beta");

        File dir1 = new File("C:");
        this.model1 = new FileTableModel(dir1);

        File dir = new File("C:");
        FileTableModel model = new FileTableModel(dir);
        this.frmToolbox.setBounds(100, 100, 1240, 689);
        this.frmToolbox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frmToolbox.setLocationRelativeTo(null);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        this.frmToolbox.getContentPane()
                .setLayout(gridBagLayout);

        JPanel pnlMain = new JPanel();
        GridBagConstraints gbc_pnlMain = new GridBagConstraints();
        gbc_pnlMain.fill = GridBagConstraints.BOTH;
        gbc_pnlMain.gridx = 0;
        gbc_pnlMain.gridy = 0;
        this.frmToolbox.getContentPane()
                .add(pnlMain, gbc_pnlMain);
        GridBagLayout gbl_pnlMain = new GridBagLayout();
        gbl_pnlMain.columnWidths = new int[]{0, 0};
        gbl_pnlMain.rowHeights = new int[]{30, 30, 30, 0, 30, 30, 25, 0};
        gbl_pnlMain.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_pnlMain.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        pnlMain.setLayout(gbl_pnlMain);

        JPanel pnlMainMenu = new JPanel();
        GridBagConstraints gbc_pnlMainMenu = new GridBagConstraints();
        gbc_pnlMainMenu.fill = GridBagConstraints.BOTH;
        gbc_pnlMainMenu.gridx = 0;
        gbc_pnlMainMenu.gridy = 0;
        pnlMain.add(pnlMainMenu, gbc_pnlMainMenu);
        GridBagLayout gbl_pnlMainMenu = new GridBagLayout();
        gbl_pnlMainMenu.columnWidths = new int[]{425, 133, 133, 97, 0};
        gbl_pnlMainMenu.rowHeights = new int[]{22, 0};
        gbl_pnlMainMenu.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_pnlMainMenu.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        pnlMainMenu.setLayout(gbl_pnlMainMenu);

        JPanel pnlSubmenu = new JPanel();
        GridBagConstraints gbc_pnlSubmenu = new GridBagConstraints();
        gbc_pnlSubmenu.fill = GridBagConstraints.BOTH;
        gbc_pnlSubmenu.gridx = 0;
        gbc_pnlSubmenu.gridy = 1;
        pnlMain.add(pnlSubmenu, gbc_pnlSubmenu);

        JPanel pnlDrives = new JPanel();
        GridBagConstraints gbc_pnlDrives = new GridBagConstraints();
        gbc_pnlDrives.fill = GridBagConstraints.BOTH;
        gbc_pnlDrives.gridx = 0;
        gbc_pnlDrives.gridy = 2;
        pnlMain.add(pnlDrives, gbc_pnlDrives);
        pnlDrives.setLayout(new GridLayout(1, 0, 0, 0));

        JPanel pnlContext = new JPanel();
        GridBagConstraints gbc_pnlContext = new GridBagConstraints();
        gbc_pnlContext.fill = GridBagConstraints.BOTH;
        gbc_pnlContext.gridx = 0;
        gbc_pnlContext.gridy = 3;
        pnlMain.add(pnlContext, gbc_pnlContext);
        GridBagLayout gbl_pnlContext = new GridBagLayout();
        gbl_pnlContext.columnWidths = new int[]{10, 0};
        gbl_pnlContext.rowHeights = new int[]{10, 0};
        gbl_pnlContext.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_pnlContext.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        pnlContext.setLayout(gbl_pnlContext);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        pnlContext.add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setFocusTraversalKeysEnabled(false);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);
        splitPane.setContinuousLayout(true);
        splitPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        splitPane.setFocusable(false);
        GridBagConstraints gbc_splitPane = new GridBagConstraints();
        gbc_splitPane.fill = GridBagConstraints.BOTH;
        gbc_splitPane.gridx = 0;
        gbc_splitPane.gridy = 0;
        splitPane.setDividerLocation(0.9);
        panel.add(splitPane, gbc_splitPane);

        JPanel pnlConsole = new JPanel();
        GridBagConstraints gbc_pnlConsole = new GridBagConstraints();
        gbc_pnlConsole.fill = GridBagConstraints.BOTH;
        gbc_pnlConsole.gridx = 0;
        gbc_pnlConsole.gridy = 4;
        pnlMain.add(pnlConsole, gbc_pnlConsole);
        pnlConsole.setLayout(new GridLayout(1, 0, 0, 0));

        JPanel pnlCommand = new JPanel();
        GridBagConstraints gbc_pnlCommand = new GridBagConstraints();
        gbc_pnlCommand.fill = GridBagConstraints.BOTH;
        gbc_pnlCommand.gridx = 0;
        gbc_pnlCommand.gridy = 5;
        pnlMain.add(pnlCommand, gbc_pnlCommand);
        pnlCommand.setLayout(new GridLayout(0, 10, 0, 0));

        JButton btnPreview = new JButton("  F3 Podgląd  ");
        btnPreview.setFocusPainted(!false);
        btnPreview
                .setIcon(new ImageIcon(FileOS.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
        btnPreview.setForeground(new Color(0, 0, 128));
        btnPreview.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnPreview.setBorder(null);
        btnPreview.setAction(new ActionPreview(btnPreview, "F3 Podgląd", "Zamykam aplikacje"));
        pnlCommand.add(btnPreview);

        JButton btnNewButton_1 = new JButton("   F4 Edycja   ");
        btnNewButton_1
                .setIcon(new ImageIcon(FileOS.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.setForeground(new Color(0, 0, 128));
        btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNewButton_1.setBorder(null);
        pnlCommand.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("   F5 Kopiuj   ");
        btnNewButton_2
                .setIcon(new ImageIcon(FileOS.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
        btnNewButton_2.setFocusPainted(false);
        btnNewButton_2.setForeground(new Color(0, 0, 128));
        btnNewButton_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNewButton_2.setBorder(null);
        pnlCommand.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("New button");
        btnNewButton_3
                .setIcon(new ImageIcon(FileOS.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
        btnNewButton_3.setFocusPainted(false);
        btnNewButton_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNewButton_3.setForeground(new Color(0, 0, 128));
        btnNewButton_3.setBorder(null);
        pnlCommand.add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("New button");
        btnNewButton_4
                .setIcon(new ImageIcon(FileOS.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
        btnNewButton_4.setFocusPainted(false);
        btnNewButton_4.setForeground(new Color(0, 0, 128));
        btnNewButton_4.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNewButton_4.setBorder(null);
        pnlCommand.add(btnNewButton_4);

        JButton btnNewButton_5 = new JButton("New button");
        btnNewButton_5
                .setIcon(new ImageIcon(FileOS.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
        btnNewButton_5.setFocusPainted(false);
        btnNewButton_5.setForeground(new Color(0, 0, 128));
        btnNewButton_5.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNewButton_5.setBorder(null);
        pnlCommand.add(btnNewButton_5);

        JButton btnNewButton_6 = new JButton("    Odznacz    ");
        btnNewButton_6
                .setIcon(new ImageIcon(FileOS.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
        btnNewButton_6.setFocusPainted(false);
        btnNewButton_6.setForeground(new Color(0, 0, 128));
        btnNewButton_6.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNewButton_6.setBorder(null);
        pnlCommand.add(btnNewButton_6);

        JButton btnNewButton_7 = new JButton("    Zaznacz    ");
        btnNewButton_7
                .setIcon(new ImageIcon(FileOS.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
        btnNewButton_7.setFocusPainted(false);
        btnNewButton_7.setForeground(new Color(0, 0, 128));
        btnNewButton_7.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNewButton_7.setBorder(null);
        pnlCommand.add(btnNewButton_7);

        JButton btnNewButton_8 = new JButton("    F8 Usuń    ");
        btnNewButton_8
                .setIcon(new ImageIcon(FileOS.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
        btnNewButton_8.setFocusPainted(false);
        btnNewButton_8.setForeground(new Color(0, 0, 128));
        btnNewButton_8.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNewButton_8.setBorder(null);
        pnlCommand.add(btnNewButton_8);

        JButton btnNewButton_9 = new JButton("Alt+F4 Zakończ");
        btnNewButton_9.setFocusPainted(false);
        btnNewButton_9.setMultiClickThreshhold(10L);
        btnNewButton_9.setForeground(new Color(0, 0, 128));
        btnNewButton_9.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNewButton_9.setBorder(null);
        btnNewButton_9.setAction(new ActionExit(btnNewButton_9, "Alt+F4 Zakończ", "Zamykam aplikacje"));
        pnlCommand.add(btnNewButton_9);

        JPanel pnlFooter = new JPanel();
        GridBagConstraints gbc_pnlFooter = new GridBagConstraints();
        gbc_pnlFooter.fill = GridBagConstraints.BOTH;
        gbc_pnlFooter.gridx = 0;
        gbc_pnlFooter.gridy = 6;
        pnlMain.add(pnlFooter, gbc_pnlFooter);
        GridBagLayout gbl_pnlFooter = new GridBagLayout();
        gbl_pnlFooter.columnWidths = new int[]{0, 0};
        gbl_pnlFooter.rowHeights = new int[]{0, 0};
        gbl_pnlFooter.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_pnlFooter.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        pnlFooter.setLayout(gbl_pnlFooter);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(SystemColor.inactiveCaption);
        GridBagConstraints gbc_toolBar = new GridBagConstraints();
        gbc_toolBar.fill = GridBagConstraints.BOTH;
        gbc_toolBar.gridx = 0;
        gbc_toolBar.gridy = 0;
        pnlFooter.add(toolBar, gbc_toolBar);

        JButton btnNewButton = new JButton("New button");
        toolBar.add(btnNewButton);

        JComboBox comboBox = new JComboBox();
        toolBar.add(comboBox);

        JMenuBar menuBar = new JMenuBar();
        this.frmToolbox.setJMenuBar(menuBar);

        JMenu mnPliki = new JMenu("Pliki");
        menuBar.add(mnPliki);

        JMenuItem mntmZmieAtrybuty = new JMenuItem("Zmień atrybuty...");
        mnPliki.add(mntmZmieAtrybuty);

        JMenuItem mntmSpakuj = new JMenuItem("Spakuj...");
        mntmSpakuj.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.ALT_MASK));
        mnPliki.add(mntmSpakuj);

        JMenuItem mntmRozpakuj = new JMenuItem("Rozpakuj...");
        mntmRozpakuj.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, InputEvent.ALT_MASK));
        mnPliki.add(mntmRozpakuj);

        JMenuItem mntmTestujArchiwuma = new JMenuItem("Testuj archiwum(a) ");
        mntmTestujArchiwuma
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
        mnPliki.add(mntmTestujArchiwuma);

        JMenuItem mntmPorwnajWgZawartoci = new JMenuItem("Porównaj wg. zawartości...");
        mnPliki.add(mntmPorwnajWgZawartoci);

        JMenuItem mntmSkojarzZ = new JMenuItem("Skojarz z...");
        mnPliki.add(mntmSkojarzZ);

        JMenuItem mntmWewntrzneSkojarzenia = new JMenuItem("Wewnętrzne skojarzenia...");
        mnPliki.add(mntmWewntrzneSkojarzenia);

        JMenuItem mntmWaciowoci = new JMenuItem("Właściwości");
        mntmWaciowoci.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_MASK));
        mnPliki.add(mntmWaciowoci);

        JMenuItem mntmObliczZajmowanPrzestrze = new JMenuItem("Oblicz zajmowaną przestrzeń");
        mnPliki.add(mntmObliczZajmowanPrzestrze);

        JMenuItem mntmNarzdzieWielokrotnejZmiany = new JMenuItem("Narzędzie wielokrotnej zmiany...");
        mntmNarzdzieWielokrotnejZmiany.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        mnPliki.add(mntmNarzdzieWielokrotnejZmiany);

        JMenuItem mntmEdytujKomentarze = new JMenuItem("Edytuj komentarze...");
        mntmEdytujKomentarze.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        mnPliki.add(mntmEdytujKomentarze);

        JMenu mnListPlikw = new JMenu("Drukuj");
        mnPliki.add(mnListPlikw);

        JMenuItem mntmListPlik = new JMenuItem("Listę plików...");
        mnListPlikw.add(mntmListPlik);

        JMenuItem mntmNewMenuItem = new JMenuItem("Listę plików z podkatalogami...");
        mnListPlikw.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Zawartość pliku pod kursorem");
        mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, InputEvent.CTRL_MASK));
        mnListPlikw.add(mntmNewMenuItem_1);

        JSeparator separator_1 = new JSeparator();
        mnPliki.add(separator_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Podziel plik...");
        mnPliki.add(mntmNewMenuItem_2);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Scal plik...");
        mnPliki.add(mntmNewMenuItem_3);

        JMenuItem mntmZakodujPliki = new JMenuItem("Zakoduj plik(i) (MIME, UUE, XXE)...");
        mnPliki.add(mntmZakodujPliki);

        JMenuItem mntmDekodujPliki = new JMenuItem("Dekoduj plik(i) (MIME, UUE, XXE)...");
        mnPliki.add(mntmDekodujPliki);

        JMenuItem mntmUtwrzPlikiSum = new JMenuItem("Utwórz plik(i) sum kontrolnych (CRC32, MD5, SHA1)...");
        mnPliki.add(mntmUtwrzPlikiSum);

        JMenuItem mntmWeryfukuj = new JMenuItem("Weryfikuj sumy kontrolne (z plików sum kontrolnych)");
        mnPliki.add(mntmWeryfukuj);

        JSeparator separator_2 = new JSeparator();
        mnPliki.add(separator_2);

        JMenuItem mntmZakocz = new JMenuItem("Zakończ");
        mntmZakocz.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        mnPliki.add(mntmZakocz);

        JMenu mnZaznacz = new JMenu("Zaznacz");
        menuBar.add(mnZaznacz);

        JMenu mnPolecenia = new JMenu("Polecenia");
        menuBar.add(mnPolecenia);

        JMenu mnSie = new JMenu("Sieć");
        menuBar.add(mnSie);

        JMenu mnWidok = new JMenu("Widok");
        menuBar.add(mnWidok);

        JMenuItem mntmKrtki = new JMenuItem("Krótki");
        mntmKrtki.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK));
        mnWidok.add(mntmKrtki);

        JMenuItem mntmPeny = new JMenuItem("Pełny");
        mntmPeny.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_MASK));
        mnWidok.add(mntmPeny);

        JMenuItem mntmKomentarze = new JMenuItem("Komentarze ");
        mntmKomentarze
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        mnWidok.add(mntmKomentarze);

        JMenu mnTrybWasnychKolumn = new JMenu("Tryb własnych kolumn");
        mnWidok.add(mnTrybWasnychKolumn);

        JMenuItem mntmNrWersji = new JMenuItem("1 nr. wersji...");
        mnTrybWasnychKolumn.add(mntmNrWersji);

        JMenuItem mntmKonfigurujWasneKolumny = new JMenuItem("Konfiguruj własne kolumny...");
        mnTrybWasnychKolumn.add(mntmKonfigurujWasneKolumny);

        JMenu mnTrybWasnychWidokw = new JMenu("Tryb własnych widoków");
        mnWidok.add(mnTrybWasnychWidokw);

        JMenuItem mntmDrzewo = new JMenuItem("Drzewo");
        mntmDrzewo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, InputEvent.CTRL_MASK));
        mnWidok.add(mntmDrzewo);

        JMenu mnOsobneDrzewo = new JMenu("Osobne drzewo");
        mnWidok.add(mnOsobneDrzewo);

        JMenuItem mntmWidokMinatur = new JMenuItem("Widok minatur");
        mntmWidokMinatur
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        mnWidok.add(mntmWidokMinatur);

        JMenuItem mntmSzybkiPodgld = new JMenuItem("Szybki podgląd");
        mntmSzybkiPodgld.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        mnWidok.add(mntmSzybkiPodgld);

        JMenuItem mntmPionoweUoenie = new JMenuItem("Pionowe ułożenie");
        mntmPionoweUoenie.addActionListener(e -> {
            splitPane
                    .setOrientation(splitPane.getOrientation() == JSplitPane.VERTICAL_SPLIT ? JSplitPane.HORIZONTAL_SPLIT
                            : JSplitPane.VERTICAL_SPLIT);
            splitPane.setDividerLocation(0.5);
        });
        mnWidok.add(mntmPionoweUoenie);

        JMenuItem mntmNowaZakadkaFolderu = new JMenuItem("Nowa zakładka folderu");
        mntmNowaZakadkaFolderu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
        mnWidok.add(mntmNowaZakadkaFolderu);

        JSeparator separator = new JSeparator();
        mnWidok.add(separator);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("Wszystkie pliki\r\n");
        mntmNewMenuItem_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, InputEvent.CTRL_MASK));
        mnWidok.add(mntmNewMenuItem_4);

        JMenuItem mntmProgramy = new JMenuItem("Programy");
        mntmProgramy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, InputEvent.CTRL_MASK));
        mnWidok.add(mntmProgramy);

        JMenuItem menuItem = new JMenuItem("*.*");
        mnWidok.add(menuItem);

        JMenuItem mntmUytkownika = new JMenuItem("Użytkownika...");
        mntmUytkownika.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, InputEvent.CTRL_MASK));
        mnWidok.add(mntmUytkownika);

        JMenuItem mntmTylkoZaznaczonePliki = new JMenuItem("Tylko zaznaczone pliki");
        mnWidok.add(mntmTylkoZaznaczonePliki);

        JSeparator separator_3 = new JSeparator();
        mnWidok.add(separator_3);

        JMenuItem mntmNazwa = new JMenuItem("Nazwa");
        mntmNazwa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.CTRL_MASK));
        mnWidok.add(mntmNazwa);

        JMenuItem mntmRozszerzenie = new JMenuItem("Rozszerzenie");
        mntmRozszerzenie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
        mnWidok.add(mntmRozszerzenie);

        JMenuItem mntmCzas = new JMenuItem("Czas");
        mntmCzas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.CTRL_MASK));
        mnWidok.add(mntmCzas);

        JMenuItem mntmWielko = new JMenuItem("Wielkość");
        mntmWielko.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, InputEvent.CTRL_MASK));
        mnWidok.add(mntmWielko);

        JMenuItem mntmNieposortowane = new JMenuItem("Nie posortowane");
        mntmNieposortowane.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, InputEvent.CTRL_MASK));
        mnWidok.add(mntmNieposortowane);

        JSeparator separator_4 = new JSeparator();
        mnWidok.add(separator_4);

        JMenuItem mntmOdwrotnyPorzdek = new JMenuItem("Odwrotny porządek");
        mnWidok.add(mntmOdwrotnyPorzdek);

        JSeparator separator_5 = new JSeparator();
        mnWidok.add(separator_5);

        JMenuItem mntmOdczytajPonownierdowy = new JMenuItem("Odczytaj ponownie źródłowy");
        mntmOdczytajPonownierdowy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        mnWidok.add(mntmOdczytajPonownierdowy);

        JMenu mnKonfiguracja = new JMenu("Konfiguracja");
        menuBar.add(mnKonfiguracja);

        JMenu mnStart = new JMenu("Start");
        menuBar.add(mnStart);

        JMenu mnPomoc = new JMenu("Pomoc");
        mnPomoc.setHorizontalAlignment(SwingConstants.RIGHT);
        menuBar.add(mnPomoc);
    }

    private FileTableModel model1;

}

class ActionExit extends AbstractAction implements WindowListener {

    JButton button;

    public ActionExit(JButton component, String text, String desc) {

        super(text, new ImageIcon(FileOS.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
        this.button = component;
        ActionMap actionMap = new ActionMapUIResource();
        actionMap.put("action_exit", this);

        this.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_Z));
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Z"));

        InputMap keyMap = new ComponentInputMap(component);
        keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.Event.ALT_MASK), "action_exit");
        SwingUtilities.replaceUIActionMap(component, actionMap);
        SwingUtilities.replaceUIInputMap(component, JComponent.WHEN_IN_FOCUSED_WINDOW, keyMap);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        this.button.requestFocus();
        System.exit(0);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
     */
    @Override
    public void windowOpened(WindowEvent e) {

        System.out.println("Zamykanie okna..");

    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
     */
    @Override
    public void windowClosing(WindowEvent e) {

        System.out.println("Zamykanie okna..");

    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
     */
    @Override
    public void windowClosed(WindowEvent e) {

        System.out.println("Zamykanie okna..");

    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
     */
    @Override
    public void windowIconified(WindowEvent e) {

        System.out.println("Zamykanie okna..");

    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
     */
    @Override
    public void windowDeiconified(WindowEvent e) {

        System.out.println("Zamykanie okna..");

    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
     */
    @Override
    public void windowActivated(WindowEvent e) {

        System.out.println("Zamykanie okna..");

    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
     */
    @Override
    public void windowDeactivated(WindowEvent e) {

        System.out.println("Zamykanie okna..");

    }

}

class ActionPreview extends AbstractAction {

    private JButton button;

    public ActionPreview(JButton component, String text, String desc) {

        super(text, new ImageIcon(FileOS.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
        this.button = component;

        ActionMap actionMap = new ActionMapUIResource();
        actionMap.put("action_preview", this);

        this.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_P));
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("P"));

        InputMap keyMap = new ComponentInputMap(component);
        keyMap.put(KeyStroke.getKeyStroke("F3"), "action_preview");
        SwingUtilities.replaceUIActionMap(component, actionMap);
        SwingUtilities.replaceUIInputMap(component, JComponent.WHEN_IN_FOCUSED_WINDOW, keyMap);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        this.button.requestFocus();
        // JOptionPane.showMessageDialog(frame, "BINGO, you SAW me.");
        System.out.println("Preview...");
    }

}
