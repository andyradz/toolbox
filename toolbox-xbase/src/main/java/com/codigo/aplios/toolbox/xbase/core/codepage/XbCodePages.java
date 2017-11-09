package com.codigo.aplios.toolbox.xbase.core.codepage;

import java.lang.annotation.Annotation;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Typ wyliczeniowy reprezentuje mapowanie strony kodowej zbioru XBase na rodzaj
 * kodowania Charset dostępnego w JRE. Typ
 * posiada mechanizm wyszukiwania dla strony kodowej odpowiedniego rodzaju
 * kodowania znaków. Typ posiada mechanizm
 * wyszukiwania dla rodzaju kodowania znaków odpowiedniej strony kodowej. W
 * przypadku braku strony kodowej lub rodzaju
 * kodowania znaków wybierany jest domyślna wartość.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.1
 * @category enum
 * @since 2017
 */
@DefaultEnumItem(value = XbCodePages.ASCII)
public enum XbCodePages {

    /**
     * Domyślne kodowanie
     */
    ASCII(Charset.forName("ASCII"), 0x00),
    /*
     * U.S. MS-DOS
     */
    IBM437(Charset.forName("IBM437"), 0x01),
    /**
     * International MS-DOS
     */
    IBM850(Charset.forName("IBM850"), 0x02),
    /**
     * Windows ANSI
     *
     * ESRI shape files use code 0x57 to indicate that data is written in ANSI
     * (whatever that means).
     * http://www.esricanada.com/english/support/faqs/arcview/avfaq21.asp
     */
    WIN1252(Charset.forName("WINDOWS-1252"), 0x03, 0x57, 0x59),
    /**
     * Standard Macintosh
     */
    MACROMAN(Charset.forName("MACROMAN"), 0x04),
    /**
     * Eastern European MS-DOS
     */
    IBM852(
            Charset.forName("IBM852"), 0x64),
    /**
     * Russian MS-DOS
     */
    IBM866(Charset.forName("IBM866"), 0x65),
    /**
     * Nordic MS-DOS
     */
    IBM865(Charset.forName("IBM865"), 0x66),
    /**
     * Icelandic MS-DOS
     */
    IBM861(Charset.forName("IBM861"), 0x67),
    /**
     * Kamenicky (Czech) MS-DOS
     */
    KAMENICKY(Charset.forName("KAMENICKY"), 0x68),
    /**
     * Mazovia (Polish) MS-DOS
     */
    MAZOVIA(
            Charset.forName("MAZOVIA"), 0x69),
    /**
     * Greek MS-DOS (437G)
     */
    XIBM737(
            Charset.forName("X-IBM737"), 0x6A),
    /**
     * Turkish MS-DOS
     */
    IBM857(
            Charset.forName("IBM857"), 0x6B),
    /**
     * Chinese (Hong Kong SAR, Taiwan) Windows
     */
    WIN950(
            Charset.forName("WINDOWS-950"), 0x78),
    /**
     * Korean Windows
     */
    WIN949(
            Charset.forName("WINDOWS-949"), 0x79),
    /**
     * Chinese (PRC, Singapore) Windows
     */
    WINGBK(
            Charset.forName("GBK"), 0x7A),
    /**
     * Japanese Windows
     */
    WIN932(
            Charset.forName("WINDOWS-932"), 0x7B),
    /**
     * Thai Windows
     */
    WIN874(
            Charset.forName("WINDOWS-874"), 0x7C),
    /**
     * Hebrew Windows
     */
    WIN1255(
            Charset.forName("WINDOWS-1255"), 0x7D),
    /**
     * Arabic Windows
     */
    WIN1256(
            Charset.forName("WINDOWS-1256"), 0x7E),
    /**
     * Russian Macintosh
     */
    XMACCYRILLIC(
            Charset.forName("X-MACCYRILLIC"), 0x96),
    /**
     * Macintosh EE
     */
    XMACCENTRALEUROPE(
            Charset.forName("X-MACCENTRALEUROPE"), 0x97),
    /**
     * Greek Macintosh
     */
    XMACGREEK(
            Charset.forName("X-MACGREEK"), 0x98),
    /**
     * Eastern European Windows
     */
    WIN1250(
            Charset.forName("WINDOWS-1250"), 0xC8),
    /**
     * Russian Windows
     */
    WIN1251(
            Charset.forName("WINDOWS-1251"), 0xC9),
    /**
     * Turkish Windows
     */
    WIN1254(
            Charset.forName("WINDOWS-1254"), 0xCA),
    /**
     * Greek Windows
     */
    WIN1253(
            Charset.forName("WINDOWS-1253"), 0xCB);

    /**
     * Atrybut określa numery stron kodowych występujące dla standardu XBase
     */
    private final int[] codepages;

    /**
     * Atrybut określa rodzaj kodowania znaków
     */
    private final Charset charset;

    /**
     * Podstawowy konstruktor obiektu klasy <code></code>
     *
     * @param charset   Rodzaj kodowania znaków
     * @param codepages Numery stron kodowych dla kodowania znaków
     */
    XbCodePages(final Charset charset, final int... codepages) {

        this.codepages = codepages;
        this.charset = charset;
    }

    /**
     * Metoda sprawdza i wyszukuje czy obsługuje daną stronę kodową. W przypadku
     * braku obsługi ustawianie jest domyślne
     * kodowanie ASCII.
     *
     * @param codepage Numer strony kodowej
     * @return Rodzaj kodowania znaków
     */
    public static Charset ofCodepage(final int codepage) {

        if (codepage < 0)
            throw new IllegalArgumentException("Wartość codepage nie może być ujemna!");

        final Supplier<XbCodePages> operator = () ->
        {
            final Annotation annotation = XbCodePages.class.
                    getAnnotation(DefaultEnumItem.class);

            return (annotation instanceof DefaultEnumItem) ? DefaultEnumItem.class.
                    cast(annotation).value()
                    : XbCodePages.ASCII;
        };

        return Stream.of(XbCodePages.values())
                .filter(item ->
                        Long.valueOf(1L).equals(Arrays.stream(item.codepages).
                                filter(e ->
                                        Integer.valueOf(codepage).equals(e)).count()))
                .findAny().orElse(operator.get()).charset;

    }

    /**
     * Metoda sprawdza i wyszukuje czy obsługuje wskazany rodzaj kodowania
     * znaków. W przypadku braku obsługi ustawiana jest domyślna strona kodowa o numerze 0.
     *
     * @param charset Rodzaj kodowania znaków
     * @return Numser strony kodowej
     */
    public static int ofCharset(final Charset charset) {

        return Stream.of(XbCodePages.values()).filter(item ->
                item.charset.equals(charset))
                .findAny()
                .orElse(ASCII).codepages[0];
    }

}
