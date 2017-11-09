package com.codigo.aplios.toolbox.xbase.core.encoding;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.codigo.aplios.toolbox.core.helpers.EnumHelper;

/**
 * Klasa realizuje mechanizm udostępniania nowych mechanizmów kodowania znaków.
 *
 * Nowe standardy mozna wykorzystać w środoisku jre w standardowy sposób wskazywania kodowania znaków z Charset.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category charset
 */
public final class XbCharsetProvider extends CharsetProvider {

	/**
	 * Typ wyliczeniowy do określenia standarów kodowania znaków dla danych zapisanych w formacie XBase
	 *
	 */
	private static enum XbCharsets {

		/**
		 * ISO 8859-2 lub bardziej formalnie ISO/IEC 8859-2, również znane jako ISO Latin-2, bądź "środkowo–" i
		 * "wschodnioeuropejskie", jest drugą częścią standardu kodowania znaków zdefiniowanego przez organizację ISO.
		 * Składa się ze 191 znaków łacińskiego pisma, z czego każdy jest zapisywany przy pomocy ośmiu bitów. Na jego
		 * podstawie została utworzona Polska Norma (PN-T-42118:1993) opisująca kodowanie polskich liter
		 * diakrytyzowanych w kodach 8 bitowych.
		 */
		LATIN2("LATIN2", "CP-852", "852"),

		/**
		 * Sposób kodowania koduje wyłącznie cyfry.
		 */
		ROT5("ROT5"),

		/**
		 * ROT13 – prosty szyfr przesuwający, którego działanie polega na zamianie każdego znaku alfabetu łacińskiego na
		 * znak występujący 13 pozycji po nim, przy czym wielkość liter nie ma przy przekształcaniu znaczenia. ROT13
		 * jest przykładem szyfru Cezara, opracowanego w Starożytnym Rzymie.
		 */
		ROT13("ROT13"),

		/**
		 * ROT47 – kodowanie przesuwające, zamieniające każdy znak ASCII z przedziału 33-126 na znak znajdujący się 47
		 * pozycji dalej, ale nie dalej niż do 126 pozycji. Podobnie jak ROT13 jest on samoodwracalny.
		 */
		ROT47("ROT47"),

		/**
		 * Mazovia – 8-bitowe kodowanie znaków przeznaczone do stosowania w systemach operacyjnych MS-DOS i
		 * kompatybilnych. Powstało na potrzeby projektowanego polskiego komputera klasy IBM PC o nazwie handlowej
		 * Mazovia. Z inicjatywy Andrzeja Gecowa otrzymało ono od 1.IX.1992 status Normy Zakładowej ZN-92 Przetwarzanie
		 * informacji. Zestaw znaków graficznych w jednobajtowym kodzie 8-bitowym — tzw. kod MAZOVIA w Spółce Akcyjnej
		 * „Mikrokomputery”[1].
		 */
		MAZOVIA("MAZOVIA", "CP-896", "CP-896", "CP-620", "CP-620", "CP790", "CP-790"),

		/**
		 * kodowanie 8-bitowe znaków przeznaczone do stosowania w systemach operacyjnych MS-DOS i kompatybilnych.
		 * Zostało stworzone przez braci Kamenickich z Czechosłowacji, by umożliwić stosowanie czeskich i słowackich
		 * liter na komputerach osobistych. Mimo wejścia na rynek systemu operacyjnego MS-DOS w wersji 5.0,
		 * posiadającego natywną obsługę języków Europy Środkowej (w postaci strony kodowej CP852), a następnie Windows
		 * 3.x (używającego strony kodowej CP1250), kodowanie Kamenický dalej było powszechnie stosowane. Dopiero od
		 * czasu rozpowszechnienia się systemów operacyjnych Windows 95 i Windows NT 4.0, wykorzystujących kodowanie
		 * Windows-1250 i (w różnym stopniu) unikod, znaczenie tego kodowania zaczęło bardzo szybko maleć, choć wciąż
		 * można spotkać aplikacje wykorzystujące to kodowanie.
		 */
		KAMENICKY("KAMENICKY", "437", "895");

		/**
		 * Atrybut reprezentuje aliasy nazw dla standardu kodowania znaków
		 */
		private final String[] charsetAliases;

		/**
		 * Podstawowy konstruktor obiektu klasy <code>XbCharsets</code>
		 *
		 * @param charsetName
		 *        Nazwa standardu kodowania znaków
		 * @param checksetAliases
		 *        Aliasy nazw standardu kodowania znaków
		 */
		XbCharsets(final String charsetName, final String... checksetAliases) {

			this.charsetAliases = checksetAliases;
		}
	}

	/**
	 * Atrybut reprezentuje kolekcję rodzajów kodowań znaków
	 */
	private static List<Charset> charsets;

	/*
	 * (non-Javadoc)
	 *
	 * @see java.nio.charset.spi.CharsetProvider#charsetForName(java.lang.String)
	 */
	@Override
	public Charset charsetForName(final String charsetName) {

		if (EnumHelper.getName(XbCharsets.class, charsetName.trim())
			.equalsIgnoreCase(XbCharsets.ROT5.name()))
			return new Rot13Charset(XbCharsets.ROT5.name(),
				null);

		if (EnumHelper.getName(XbCharsets.class, charsetName.trim())
			.equalsIgnoreCase(XbCharsets.ROT13.name()))
			return new Rot13Charset(XbCharsets.ROT13.name(),
				null);

		if (EnumHelper.getName(XbCharsets.class, charsetName.trim())
			.equalsIgnoreCase(XbCharsets.ROT47.name()))
			return new Rot13Charset(XbCharsets.ROT47.name(),
				null);

		if (EnumHelper.getName(XbCharsets.class, charsetName.trim())
			.equalsIgnoreCase(XbCharsets.LATIN2.name()))
			return new Rot13Charset(XbCharsets.LATIN2.name(),
				null);

		if (Stream.of(XbCharsets.MAZOVIA.charsetAliases)
			.anyMatch(item -> item.equalsIgnoreCase(charsetName.trim())))
			return new MazoviaCharset(XbCharsets.MAZOVIA.name(),
				XbCharsets.MAZOVIA.charsetAliases);

		if (EnumHelper.getName(XbCharsets.class, charsetName.trim())
			.equalsIgnoreCase(XbCharsets.MAZOVIA.name()))
			return new MazoviaCharset(XbCharsets.MAZOVIA.name(),
				null);

		if (EnumHelper.getName(XbCharsets.class, charsetName.trim())
			.equalsIgnoreCase(XbCharsets.KAMENICKY.name()))
			return new KamenickyCharset(XbCharsets.KAMENICKY.name(),
				XbCharsets.KAMENICKY.charsetAliases);

		if (EnumHelper.getName(XbCharsets.class, charsetName.trim())
			.equalsIgnoreCase(XbCharsets.KAMENICKY.name()))
			return new MazoviaCharset(XbCharsets.KAMENICKY.name(),
				null);

		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.nio.charset.spi.CharsetProvider#charsets()
	 */
	@Override
	public Iterator<Charset> charsets() {

		if ((XbCharsetProvider.charsets == null) || XbCharsetProvider.charsets.isEmpty()) {
			XbCharsetProvider.charsets = new ArrayList<>();
			XbCharsetProvider.charsets.add(new Rot5Charset(XbCharsets.ROT5.name(),
				null));
			XbCharsetProvider.charsets.add(new Rot13Charset(XbCharsets.ROT13.name(),
				null));
			XbCharsetProvider.charsets.add(new Rot47Charset(XbCharsets.ROT47.name(),
				null));
			XbCharsetProvider.charsets.add(new IBMLatinCharset(XbCharsets.LATIN2.name(),
				null));
			XbCharsetProvider.charsets.add(new MazoviaCharset(XbCharsets.MAZOVIA.name(),
				XbCharsets.MAZOVIA.charsetAliases));
			XbCharsetProvider.charsets.add(new KamenickyCharset(XbCharsets.KAMENICKY.name(),
				XbCharsets.KAMENICKY.charsetAliases));

		}
		return XbCharsetProvider.charsets.iterator();
	}
}