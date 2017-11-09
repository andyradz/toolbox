package com.codigo.aplios.toolbox.core.metric;

public class mainer {

	public static void main(String[] args) {

		// Lang pl = Lang.PL;
		// Lang en = Lang.EN;
	}

}

// enum Lang {
// PL(new Locale("pl_PL")),
// EN(new Locale("en_EN"));
//
// private MoneyNames moneyName;
//
// private Properties moneyNames;
//
// private Locale locale;
//
// private Lang(Locale locale) {
// try {
// this.locale = locale;
// String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
// String moneyConfigPath = rootPath + "names_" + this.locale.toString() + ".xml";
// moneyNames = new Properties();
// moneyNames.loadFromXML(new FileInputStream(moneyConfigPath));
// } catch (FileNotFoundException ex) {
// Logger.getLogger(Lang.class.getName()).log(Level.SEVERE, null, ex);
// } catch (IOException ex) {
// Logger.getLogger(Lang.class.getName()).log(Level.SEVERE, null, ex);
// }
// }
//
// }
//
/// **
// * Typ wyliczeniowy dostarcza nazwy dla poszczególnych elementów typu monetarnego, który jest zamieniany na postać
// * słowną.
// *
// * @author andrzej.radziszewski
// * @version 1.0.0.0
// * @since 2017
// */
// enum MoneyNames {
// // <editor-fold defaultstate="collapsed" desc=" ENUM VALUES ">
// ONES {
// @Override
// public String getName(int number) {
// return iconProps.getProperty(MoneyNames.onesNameIndex + "." + number);
// }
//
// },
// TENS {
// @Override
// public String getName(int number) {
// return iconProps.getProperty(MoneyNames.tensNameIndex + "." + number);
// }
//
// },
// HUNDRED {
// @Override
// public String getName(int number) {
// return iconProps.getProperty(MoneyNames.hundNameIndex + "." + number);
// }
//
// },
// TEENS {
// @Override
// public String getName(int number) {
// return iconProps.getProperty(MoneyNames.teensNameIndex + "." + number);
// }
//
// },
// MINUS {
// @Override
// public String getName(int number) {
// return iconProps.getProperty(MoneyNames.minusNameIndex);
// }
//
// },
// PLUS {
// @Override
// public String getName(int number) {
// return iconProps.getProperty(MoneyNames.plusNameIndex);
// }
//
// };
// // </editor-fold>
//
// // <editor-fold defaultstate="collapsed" desc=" PRIVATE SECTION ">
// private final static String onesNameIndex = "1";
//
// private final static String teensNameIndex = "2";
//
// private final static String tensNameIndex = "3";
//
// private final static String hundNameIndex = "4";
//
// private final static String plusNameIndex = "+";
//
// private final static String minusNameIndex = "-";
//
// private static Properties iconProps;
//
// private static Locale locale;
//
// static {
// try {
// locale = Locale.getDefault();
// String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
// String iconConfigPath = rootPath + "names_" + locale.toString() + ".xml";
// iconProps = new Properties();
// iconProps.loadFromXML(new FileInputStream(iconConfigPath));
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
// // </editor-fold>
//
// // <editor-fold defaultstate="collapsed" desc=" PUBLIC SECTION ">
// public abstract String getName(int number);
// // </editor-fold>

// }
