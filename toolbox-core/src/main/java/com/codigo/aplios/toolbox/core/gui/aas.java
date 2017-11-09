package com.codigo.aplios.toolbox.core.gui;

// class MyInt {
// private final Integer val;
//
// public MyInt(Integer val) {
// this.val = val;
// }
//
// public Integer value() {
//
// return this.val;
// }
// }
//
// class CustomRenderer extends DefaultTableCellRenderer {
// private static final long serialVersionUID = 6703872492730589499L;
//
// @Override
// public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
// int row, int column) {
//
// Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//
// if (row == 0) {
// cellComponent.setBackground(Color.YELLOW);
// } else if (row == 1) {
// cellComponent.setBackground(Color.GRAY);
// } else {
// cellComponent.setBackground(Color.CYAN);
// }
// return cellComponent;
// }
// }
//
// class FiveCharacterEditor extends DefaultCellEditor {
// FiveCharacterEditor() {
// super(new JTextField());
// }
//
// @Override
// public boolean stopCellEditing() {
//
// try {
// String editingValue = (String) getCellEditorValue();
//
// if (editingValue.length() != 5) {
// JTextField textField = (JTextField) getComponent();
// textField.setBorder(new LineBorder(Color.red));
// textField.selectAll();
// textField.requestFocusInWindow();
//
// JOptionPane.showMessageDialog(
// null, "Please enter string with 5 letters.", "Alert!", JOptionPane.ERROR_MESSAGE);
// return false;
// }
// } catch (ClassCastException exception) {
// return false;
// }
//
// return super.stopCellEditing();
// }
//
// @Override
// public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//
// Component c = super.getTableCellEditorComponent(table, value, isSelected, row, column);
// ((JComponent) c).setBorder(new LineBorder(Color.black));
//
// return c;
// }
//
// public static void createGui() {
//
// final JFrame frmMain = new JFrame("Konfigurator");
// String className = getLookAndFeelClassName("Windows");
// try {
// UIManager.setLookAndFeel(className);
// } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
// | UnsupportedLookAndFeelException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// final JTable frmGrid = new JTable(20, 10);
//
// // Ustawnienie właściwości grida
// // ..............................................................................................................
// // frmGrid.setRowMargin(1);
// frmGrid.getColumnModel().setColumnMargin(1);
// frmGrid.setIntercellSpacing(new Dimension(1, 1));
// TableCellEditor fce = new FiveCharacterEditor();
// frmGrid.setDefaultEditor(Object.class, fce);
// frmGrid.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer());
// frmGrid.getModel().addTableModelListener(new TableModelListener() {
// @Override
// public void tableChanged(final TableModelEvent e) {
//
// EventQueue.invokeLater(new Runnable() {
// @Override
// public void run() {
//
// frmGrid.setRowHeight(e.getFirstRow(), 30); // replace 15 with your
// // own height
// }
// });
// }
// });
//
// // frmGrid.setRowHeight(10, 40);
// JButton btnClear = new JButton("Zakończ");
// JButton btnRestore = new JButton("Przywróć");
//
// frmMain.setLayout(new FlowLayout());
// frmMain.add(new JButton("Koniec"));
// frmMain.add(new JButton("Początek"));
// frmMain.add(new JButton("Rozpocznij"));
// frmMain.add(btnClear);
// frmMain.add(btnRestore);
// frmMain.add(new JScrollPane(frmGrid));
// btnClear.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent e) {
//
// EveryNth.makeWindowScreenshot(frmMain);
// try {
// EveryNth.makeWindowsScreenshot(frmMain);
// } catch (AWTException | IOException e1) {
// // TODO Auto-generated catch block
// e1.printStackTrace();
// }
// // display/center the jdialog when the button is pressed
// // JDialog d = new JDialog(frmMain, "Hello", true);
// // d.setLocationRelativeTo(frmMain);
// // d.setVisible(true);
// DefaultTableModel model = (DefaultTableModel) frmGrid.getModel();
// model.setNumRows(1);
// model.fireTableDataChanged();
// }
// });
//
// btnRestore.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent e) {
//
// // display/center the jdialog when the button is pressed
// // JDialog d = new JDialog(frmMain, "Hello", true);
// // d.setLocationRelativeTo(frmMain);
// // d.setVisible(true);
// DefaultTableModel model = (DefaultTableModel) frmGrid.getModel();
// // model.setRowCount(100);
// model.setNumRows(100);
// model.fireTableDataChanged();
// }
// });
// frmMain.add(frmGrid);
//
// frmMain.setSize(800, 600);
// frmMain.setLocationRelativeTo(null);
// frmMain.setResizable(false);
// frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// frmMain.setVisible(true);
//
// }
//
// public static String getLookAndFeelClassName(String nameSnippet) {
//
// LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
// for (LookAndFeelInfo info : plafs) {
// if (info.getName().contains(nameSnippet)) {
// return info.getClassName();
// }
// }
// return null;
// }
//
// public static void main(String[] args)
// throws IllegalArgumentException, IllegalAccessException, InstantiationException {
//
// Arrays.stream(args).forEach(System.out::println);
//
// SwingUtilities.invokeLater(ArrayHelpers::createGui);
//
// Integer[] znaki = { 1, 2, 3, 4, 5, 6 };
//
// final MyInt[] znaczki = Arrays.stream(znaki)
// // .map(String::valueOf)
// .map(MyInt::new)
// .toArray(MyInt[]::new);
//
// final String[] znaczks = Arrays.stream(znaki).map(String::valueOf).toArray(String[]::new);
//
// Integer[][] dane = { { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 11, 12, 13, 14, 15, 16, 17, 18, 19 } };
//
// Arrays.stream(dane).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
// // Stream<Integer> stringStream = temp.flatMap(x -> Arrays.stream(x));
//
// // stringStream.collect(Collectors.toList())
// // .forEach(System.out::println);
//
// List<Integer> dsList = new ArrayList<Integer>() {
// {
// add(1);
// add(2);
// add(3);
// add(4);
// }
// };
//
// Integer[] arr = Stream.of(1, 2, 23, 12, 211, 232, 3).toArray(Integer[]::new);
//
// Integer[] arr1 = Stream.of(1000, 2000, 3000, 4000).toArray(Integer[]::new);
// System.out.println("Łączenie tablic");
// ArrayHelpers.join(arr, arr1).forEachRemaining(System.out::println);
//
// Number val = ArrayHelpers.min(arr);
// System.out.println("Wartość minimalna:" + val.doubleValue());
// val = ArrayHelpers.max(arr);
// System.out.println("Wartość maxsymalna:" + val.doubleValue());
// val = ArrayHelpers.count(arr);
// System.out.println("Ilość elementów:" + val.longValue());
//
// Integer[] numbers = { 0, 1, 2, 1, 3, 4, 4, 4, 4, 0, 23, 23, 11, 12, 22, -1, 0, 0, 0, 0, 33, 100, 293, -12 };
//
// System.out.println("Sortowanie elemntów tablicy");
// ArrayHelpers.sort(numbers, (x, y) -> x.compareTo(y)).forEachRemaining(System.out::println);
//
// System.out.println("Zduplikowane wartości");
// ArrayHelpers.duplicate(numbers).iterator().forEachRemaining(System.out::println);
//
// val = ArrayHelpers.avg(numbers);
// System.out.println("Wartość średnia:" + ceil(val.doubleValue()));
//
// System.out.println("Podzbiór elemntów tablicy");
// ArrayHelpers.subarr(numbers, 0L, 5L).forEachRemaining(System.out::println);
//
// }
//
// /**
// * Metoda realizuje wyznaczenie maksymalnej wartości elemntu z elementów tablicy przekazanej jako parametr
// * <code>array</code>.
// *
// * @param array
// * @return
// */
// private static final Number max(Number[] array) {
//
// if (Objects.isNull(array))
// throw new NullPointerException();
//
// return Arrays.stream(array).map(Number::doubleValue).max(Double::compare).get();
// }
//
// /**
// * Metoda realizuje wyznaczenie minimalnej wartości elemntu z elementów tablicy przekazanej jako parametr
// * <code>array</code>.
// *
// * @param array
// * @return
// */
// private static final Number min(Number[] array) {
//
// if (Objects.isNull(array))
// throw new NullPointerException();
//
// return Arrays.stream(array).map(Number::doubleValue).min(Double::compare).get();
// }
//
// /**
// * Metoda realizuje zliczenie ilości elementów tablicy przekazanej jako parametr <code>array</code>.
// *
// * @param array
// * @return
// */
// private static final <T> Number count(T[] array) {
//
// if (Objects.isNull(array))
// throw new NullPointerException();
//
// return Arrays.stream(array).count();
// }
//
// private static final <T> Iterator<T> subarr(T[] array, long skip, long limit) {
//
// if (Objects.isNull(array))
// throw new NullPointerException();
//
// return Arrays.stream(array).skip(skip).limit(limit).iterator();
// }
//
// /**
// * Metoda realizuje wyznaczenie zduplikowanych wartości z elementów tablicy przekazanych jako parametr
// * <code>array</code>.
// *
// * @param array
// * @return
// */
// private static final <T> Stream<T> duplicate(T[] array) {
//
// if (Objects.isNull(array))
// throw new NullPointerException();
//
// return Arrays.stream(array).filter(n -> Arrays.stream(array).filter(x -> x == n).count() > 1).distinct();
// }
//
// /**
// * Metoda realizuje wyznaczenie średniej wartości z elementów przekaznaych jako tablica elementów numerycznych.
// *
// * @param array
// * @return
// */
// private static final <T extends Number> double avg(T[] array) {
//
// if (Objects.isNull(array))
// throw new NullPointerException();
//
// return Arrays.stream(array).map(Number::doubleValue).mapToDouble(Double::doubleValue).average().getAsDouble();
// }
//
// private static final <T> Iterator<T> sort(T[] array, Comparator<T> comparatotr) {
//
// return Arrays.stream(array).sorted(comparatotr).iterator();
// }
//
// private static final <T> Iterator<T> join(T[] array1, T[] array2) {
//
// if (Objects.isNull(array1) || Objects.isNull(array1))
// throw new NullPointerException();
//
// return Stream.concat(Arrays.stream(array1), Arrays.stream(array2)).iterator();
// }
//
// private static final Function<Field, Long> functor = (item) -> {
// Enum<?> val = null;
// try {
// val = (Enum<?>) item.get(val);
// } catch (Exception e) {
// e.printStackTrace();
// }
// return (long) val.ordinal();
// };
//
// public static long getOrdinalSum(Class<? extends Enum<?>> instance)
// throws IllegalArgumentException, IllegalAccessException, InstantiationException {
//
// return Stream.of(instance.getDeclaredFields()).filter(item -> item.isEnumConstant()).map(functor).collect(
// Collectors.summingLong(item -> item));
// }
//
// public static double getOrdinalAvg(Class<? extends Enum<?>> instance) {
//
// return Stream.of(instance.getDeclaredFields()).filter(item -> item.isEnumConstant()).map(functor).collect(
// Collectors.averagingLong(item -> item));
// }
//
// public static long getOrdinalMul(Class<? extends Enum<?>> instance) {
//
// return Stream.of(instance.getDeclaredFields())
// .filter(item -> item.isEnumConstant())
// .map(functor)
// .reduce(
// (sum, p) -> sum = (sum == 0L ? 1L
// : p.longValue() * sum))
// .get();
// }
//
// public static long getLastOrdinal(Class<? extends Enum<?>> instance) {
//
// return Stream.of(instance.getDeclaredFields())
// .filter(item -> item.isEnumConstant())
// .map(functor)
// .reduce((a, b) -> b)
// .get();
// }
//
// public static String getLastElement(Class<? extends Enum<?>> instance) {
//
// return Stream.of(instance.getDeclaredFields())
// .filter(item -> item.isEnumConstant())
// .map(item -> item.getName())
// .reduce((a, b) -> b)
// .get();
// }
// }
//
// class EveryNth<C> {
//
// private final int nth;
// private final List<List<C>> lists = new ArrayList<>();
// private int next = 0;
//
// private EveryNth(int nth) {
// this.nth = nth;
// IntStream.range(0, nth).forEach(i -> lists.add(new ArrayList<>()));
// }
//
// private void accept(C item) {
//
// lists.get(next++ % nth).add(item);
// }
//
// private EveryNth<C> combine(EveryNth<C> other) {
//
// other.lists.forEach(l -> lists.get(next++ % nth).addAll(l));
// next += other.next;
// return this;
// }
//
// private List<C> getResult() {
//
// return lists.get(0);
// }
//
// public static <T> Collector<Integer, ?, List<Integer>> collector(int nth) {
//
// return Collector.of(() -> new EveryNth(nth), EveryNth::accept, EveryNth::combine, EveryNth::getResult);
// }
//
// /**
// * Procedura wykonuje zrzut zawartości ekranu i zapisuje go do pliku;
// *
// * @param winForm
// * Okno ekranu systemu operacyjnego.
// */
// public static final void makeWindowScreenshot(JFrame winForm) {
//
// // obszar roboczy okna ekranu
// // ..............................................................................................................
// final Rectangle winBound = winForm.getBounds();
//
// // buffor operacji graficznych
// // ..............................................................................................................
// final BufferedImage bufferedImage =
// new BufferedImage(winBound.width, winBound.height, BufferedImage.TYPE_INT_ARGB);
//
// winForm.paint(bufferedImage.getGraphics());
//
// try {
// // utworzenie pliku
// final File screenFile = File.createTempFile(winForm.getName(), ".png");
//
// // Use the ImageIO API to write the bufferedImage to a temporary file
// ImageIO.write(bufferedImage, "png", screenFile);
// screenFile.deleteOnExit();
// } catch (IOException ioe) {
// System.out.println(ioe);
// }
// }
//
// public static final void makeWindowsScreenshot(JFrame winForm) throws AWTException, IOException {
//
// GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
// GraphicsDevice[] screens = ge.getScreenDevices();
// Rectangle allScreenBounds = new Rectangle();
// for (GraphicsDevice screen : screens) {
// Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
// allScreenBounds.width += screenBounds.width;
// allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
// allScreenBounds.x = Math.min(allScreenBounds.x, screenBounds.x);
// allScreenBounds.y = Math.min(allScreenBounds.y, screenBounds.y);
// }
// Robot robot = new Robot();
// BufferedImage bufferedImage = robot.createScreenCapture(allScreenBounds);
// File file = new File("d:\\scr.png");
// if (!file.exists())
// file.createNewFile();
// FileOutputStream fos = new FileOutputStream(file);
// ImageIO.write(bufferedImage, "png", fos);
// }
// }
