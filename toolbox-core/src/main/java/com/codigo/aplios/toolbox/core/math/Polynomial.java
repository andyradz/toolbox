package com.codigo.aplios.toolbox.core.math;

import java.util.HashSet;

// http://www.matematykam.pl/dzialania_na_wielomianach.html
// https://edux.pjwstk.edu.pl/mat/224/lec/main17.html
public class Polynomial {

	public static void main(String[] args) {

		// Wielomian w1 = new Wielomian(new double[] { 3, -4, 5, 0, 2 });
		// Wielomian w2 = new Wielomian(new double[] { 2, 3, -7, 4 });
		//
		// Wielomian w3 = new Wielomian(new double[] { 0, 1, 2, 23, 23 });

		// System.out.println(w1.wartosc(44));
		// System.out.println(w1);
		// System.out.println("+");
		// System.out.println(w2);
		// System.out.println("=");
		// System.out.println(w1.suma(w2));
		//
		// System.out.println(w3);
	}

	private HashSet<Long> factors;

	// TODO: współczynniki woelomiany zdefiniować jako osobne obiekty, które będą przrchowywały wartość

	// -----------------------------------------------------------------------------------------------------------------

	public Polynomial() {

		this.factors = new HashSet<>();
	}

	// -----------------------------------------------------------------------------------------------------------------

	public Polynomial add(Polynomial pol1, Polynomial pol2) {

		return null;
	}

	// -----------------------------------------------------------------------------------------------------------------

	public Polynomial diff(Polynomial pol1, Polynomial pol2) {

		return null;
	}

	// -----------------------------------------------------------------------------------------------------------------

	public Polynomial mul(Polynomial pol1, Polynomial pol2) {

		return null;
	}

	// -----------------------------------------------------------------------------------------------------------------

	public Polynomial div(Polynomial pol1, Polynomial pol2) {

		return null;
	}

	// -----------------------------------------------------------------------------------------------------------------

	public Polynomial assembly(Polynomial pol1, Polynomial pol2) {

		return null;
	}

	// -----------------------------------------------------------------------------------------------------------------
}

/// * Wielomian.java */
//
// class Wielomian {
// private double[] wsp;
//
// public Wielomian(double[] wsp) {
// this.wsp = wsp;
// }
//
// @Override
// public String toString() {
//
// String row = "";
//
// for (int i = 0; i < wsp.length; i++) {
// if (wsp[i] != 0) {
// row += wsp[i] < 0 ? row.equals("") ? "-" : " - " : row.equals("") ? "" : " + ";
// row += Math.abs(wsp[i]) + (i == 0 ? "" : i == 1 ? "x" : "x^" + i);
// }
// }
//
// return row + (row.equals("") ? "0" : "");
// }
//
// public double wartosc(double x) {
//
// double suma = wsp[wsp.length - 1];
//
// for (int i = wsp.length - 2; i >= 0; i--) {
// suma = wsp[i] + x * suma;
// }
//
// return suma;
// }
//
// public Wielomian suma(Wielomian w) {
//
// double[] w1 = wsp;
// double[] w2 = w.wsp;
//
// int max = Math.max(w1.length, w2.length);
//
// double[] suma = new double[max];
//
// for (int i = 0; i < max; i++) {
// double wsp1 = i < w1.length ? w1[i] : 0;
// double wsp2 = i < w2.length ? w2[i] : 0;
//
// suma[i] = wsp1 + wsp2;
// }
//
// return new Wielomian(suma);
// }
//
// public Wielomian iloczyn(Wielomian w) {
//
// int st = wsp.length + w.wsp.length - 1;
//
// double[] w1 = wsp;
// double[] w2 = w.wsp;
//
// double[] iloczyn = new double[st];
//
// for (int i = 0; i < w1.length; i++) {
// for (int j = 0; j < w2.length; j++) {
// iloczyn[i + j] = iloczyn[i + j] + w1[i] * w2[j];
// }
// }
//
// return new Wielomian(iloczyn);
// }
//
// public Wielomian[] iloraz(Wielomian w) throws Exception {
//
// double[] w1 = wsp;
// double[] w2 = w.wsp;
//
// int st = w1.length - w2.length + 1;
//
// if (st < 1)
// throw new Exception("dzielenie niewykonalne");
//
// double[] iloraz = new double[st];
// double[] reszta = w1.clone();
//
// for (int k = iloraz.length - 1, i = w1.length - 1; k >= 0; k--, i--) {
// iloraz[k] = reszta[i] / w2[w2.length - 1];
//
// for (int j = w2.length - 1; j >= 0; j--) {
// reszta[k + j] = reszta[k + j] - iloraz[k] * w2[j];
// }
// }
//
// return new Wielomian[] { new Wielomian(iloraz), new Wielomian(reszta) };
// }
// }
//
//
// if expectedSts == 'REGD';
// if not verifyTagValue ( XPath = 'InstrPrcgSts/Accptd/Rsn/RsnCd/Cd', val = expectedRsnCd );
// rsnCd = getTagValue ( XPath = 'RequestPayload/Document/DsclInstrStsAdvc/InstrPrcgSts/Accptd/Rsn/RsnCd/Cd',
// tagValueMaxLen = 4 );
// log('Dla $CARef znaleziono $stsMsgTp do $mbr $instrId gdzie rsnCd = $rsnCd a oczekiwano rsnCd = $expectedRsnCd');
// return false;
// endif;
// log('$CARef $stsMsgTp do $mbr $instrId status $expectedSts $expectedRsnCd zgodny z oczekiwaniem');
// endif;
//
//
// Opis algorytmu obliczania wielomianua metodą Hornera
// http://rnowak.c0.pl/index.php?kat=algorytmika&file=algorytm_hornera
//
/// **
// * www.algorytm.org
// * Tomasz Lubinski (c)2005
// * metoda polowienia - obliczanie zer funkcji nieliniowyh
// * na przykladzie wielomianow
// */
// public class Polowienie {
//
// private static int n,m;
// private static double a[];
//
//// algorytm Hornera do obliczania wartosci wielomianu
// private static double w(int k, double x) {
// if (k==n)
// return a[n];
// else
// return w(k+1,x)*x+a[k];
// }
//
// public static void main(String[] args) {
// int i;
// double p,k,s;
// s = 0;
//
// System.out.println("Metoda polowienia - obliczanie miejsc zerowych funkcji nie liniowych \nna przykladzie
/// wielomianow\n\nPodaj stopien wielomianu\n");
// n = Console.readInt("?");
// a = new double[n+1];
//
// System.out.println("Podaj teraz kolejne wspolczynniki wielomianu.\nZaczynij od tego z najwieksza potega.\n");
// for(i=n; i>=0; i--) {
// a[i] = Console.readDouble("a" + i);
// }
//
// System.out.println("Podaj poczatek przedzialu");
// p = Console.readInt("?");
// System.out.println("Podaj koniec przedzialu");
// k = Console.readInt("?");
// System.out.println("Podaj liczbe iteracji");
// m = Console.readInt("?");
//
// for(i=1; i<m; i++) {
// s=(p+k)/2;
// if ((w(0,p)*w(0,s)<0)&&(w(0,s)*w(0,k)>0)) {
// k=s;
// }
// else if ((w(0,p)*w(0,s)>0)&&(w(0,s)*w(0,k)<0)) {
// p=s;
// }
// else if (w(0,s)==0) {
// break;
// }
// else {
// System.out.println("Przedzial nie spelnia zalozen");
// return;
// }
// }
// if (w(0,s)==0) {
// System.out.println("Dokladny pierwiastek wynosi " + s);
// }
// else {
// System.out.println("Przyblizony pierwiastek wynosi " + s);
// }
//
// return;
// }
// }
//
// http://matematyka.pisz.pl/strona/106.html
// http://www.matmana6.pl/tablice_matematyczne/liceum/wielomiany/20-definicja_wielomianu_dzialania_na_wielomianach
