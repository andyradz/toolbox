/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.xbase.core.encoding;

import java.nio.charset.Charset;
import java.util.stream.Stream;

// Mazowia charset
// https://github.com/VitaliyOliynyk/mazovia-charset/blob/master/src/main/java/eu/vitaliy/pl/charset/MazoviaCharset.java
/**
 *
 * @author andrzej.radziszewski
 */
public enum XbFileCharset {

    /**
     * U.S. MS-DOS
     */
    IBM437((byte)0x01, Charset.forName("ibm437")),
    /**
     * International MS-DOS
     */
    IBM850((byte)0x02, Charset.forName("ibm850")),
    /**
     * Windows ANSI
     */
    WIN1252((byte)0x03, Charset.forName("windows-1252")),
    /**
     * Standard Macintosh
     */
    MACROMAN((byte)0x04, Charset.forName("macroman")),
    /**
     * ESRI shape files use code 0x57 to indicate that // data is written in ANSI (whatever that means). //
     * http://www.esricanada.com/english/support/faqs/arcview/avfaq21.asp
     */
    WIN1252_1((byte)0x57, Charset.forName("windows-1252")),
    /**
     *
     */
    WIN1252_2((byte)0x59, Charset.forName("windows-1252")),
    /**
     * Eastern European MS-DOS
     */
    IBM852((byte)0x64, Charset.forName("ibm852")),
    /**
     * Russian MS-DOS
     */
    IBM866((byte)0x65, Charset.forName("ibm866")),
    /**
     * Nordic MS-DOS
     */
    IBM865((byte)0x66, Charset.forName("ibm865")),
    /**
     * Icelandic MS-DOS
     */
    IBM861((byte)0x67, Charset.forName("ibm865")),
    /**
     * Kamenicky (Czech) MS-DOS
     */
    // KAMENICKY((byte) 0x67, Charset.forName("895")),

    /**
     * Mazovia (Polish) MS-DOS
     */
    MAZOVIA((byte)0x69, Charset.forName("mazovia")),
    /**
     * Greek MS-DOS (437G)
     */
    IBM737((byte)0x6A, Charset.forName("x-ibm737")),
    /**
     * Turkish MS-DOS
     */
    IBM857((byte)0x6B, Charset.forName("x-ibm737")),
    /**
     * Chinese (Hong Kong SAR, Taiwan) Windows
     */
    WIN950((byte)0x78, Charset.forName("windows-950")),
    /**
     * Korean Windows
     */
    WIN949((byte)0x79, Charset.forName("windows-949")),
    /**
     * Chinese (PRC, Singapore) Windows
     */
    WINGBK((byte)0x79, Charset.forName("GBK")),
    /**
     * Japanese Windows
     */
    WIN932((byte)0x7B, Charset.forName("windows-932")),
    /**
     * Thai Windows
     */
    WIN874((byte)0x7C, Charset.forName("windows-874")),
    /**
     * Hebrew Windows
     */
    WIN1255((byte)0x7D, Charset.forName("windows-1255")),
    /**
     * Arabic Windows
     */
    WIN1256((byte)0x7E, Charset.forName("windows-1256")),
    /**
     * Russian Macintosh
     */
    MACCYRILLIC((byte)0x96, Charset.forName("x-maccyrillic")),
    /**
     * Macintosh EE
     */
    MACINTOSHEE((byte)0x97, Charset.forName("x-maccentraleurope")),
    /**
     * Greek Macintosh
     */
    MACGREEK((byte)0x98, Charset.forName("x-macgreek")),
    /**
     * Eastern European Windows
     */
    WIN1250((byte)0xC8, Charset.forName("windows-1250")),
    /**
     * Russian Windows
     */
    WIN1251((byte)0xC9, Charset.forName("windows-1251")),
    /**
     * Turkish Windows
     */
    WIN1254((byte)0xCA, Charset.forName("windows-1254")),
    /**
     * Greek Windows
     */
    WIN1253((byte)0xCB, Charset.forName("windows-1253"));

    XbFileCharset(final byte version, final Charset charset) {

        this.code = version;
        this.charset = charset;
    }

    /**
     * Gets Java charset from DBF code
     *
     * @param b the code stored in DBF file
     * @return Java charset, null if unknown.
     */
    public static Charset getCharsetByByte(final byte b) {

        return Stream.of(XbFileCharset.values())
                .filter(item -> item.code == b)
                .map(i -> i.charset)
                .findFirst()
                .orElse(Charset.defaultCharset());
    }

    /**
     * gets the DBF code for a given Java charset
     *
     * @param charset the Java charset
     * @return the DBF code, 0 if unknown
     */
    public static byte getCodeForCharset(final Charset charset) {

        return Stream.of(XbFileCharset.values())
                .filter(item -> item.charset.equals(charset))
                .map(i -> i.code)
                .findFirst()
                .orElse((byte)0);
    }

    private byte code;

    // --------------------------------------------------------------------------
    private Charset charset;

}
