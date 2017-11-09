/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.toolbox.xbase.core.encoding;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/**
 * Klasa realizuje mechanizm kodowania znaków dla standardu Mazovia.
 * Przykład użycia: <code>Charset.forName("MAZOVIA")</code>
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 */
public final class MazoviaCharset extends Charset {

    /**
     * Mapa kodowania znaków dla standardu Mazovia
     */
    private final static char[][] CHARS_UNICODE_SORT = {{'\u0104', 143}// Ą
    ,
     {'\u0105', 134}// ą
    ,
     {'\u0106', 149}// Ć
    ,
     {'\u0107', 141}// ć
    ,
     {'\u0118', 144}// Ę
    ,
     {'\u0119', 145}// ę
    ,
     {'\u0141', 156}// Ł
    ,
     {'\u0142', 146}// ł
    ,
     {'\u0143', 165}// Ń
    ,
     {'\u0144', 164}// ń
    ,
     {'\u00D3', 163}// Ó
    ,
     {'\u00F3', 162}// ó
    ,
     {'\u015A', 152}// Ś
    ,
     {'\u015B', 158}// ś
    ,
     {'\u0179', 160}// Ź
    ,
     {'\u017A', 166}// ź
    ,
     {'\u017B', 161}// Ż
    ,
     {'\u017C', 167}// ż
    };

    /**
     * Podstawowy konstruktor obiektu klasy <code>MazoviaCharset</code>
     *
     * @param canonicalName Potoczna nazwa standadu kodowania znaków
     * @param aliases       Aliasy nazw dla standardu kodowania nazków Mazowia
     */
    public MazoviaCharset(final String canonicalName, final String[] aliases) {

        super(canonicalName, aliases);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.nio.charset.Charset#contains(java.nio.charset.Charset)
     */
    @Override
    public boolean contains(final Charset cs) {

        return cs.equals(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.nio.charset.Charset#newDecoder()
     */
    @Override
    public CharsetDecoder newDecoder() {

        return new MazoviaCharsetDecoder(this, 1, 1);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.nio.charset.Charset#newEncoder()
     */
    @Override
    public CharsetEncoder newEncoder() {

        return new MazoviaCharsetEncoder(this, 1, 1);
    }

    /**
     * Klasa realizuje mechanizm kodowania zanków dla standardu Mazovia
     *
     * @author andrzej.radziszewski
     * @version 1.0.0.0
     * @since 2017
     */
    private final class MazoviaCharsetEncoder extends CharsetEncoder {

        /**
         * Podstawowy konstruktor obiektu klasy <code>MazoviaCharsetEncoder</code>
         *
         * @param cs                  Bufor kodowanych znaków
         * @param averageBytesPerChar
         * @param maxBytesPerChar
         * @param replacement
         */
        public MazoviaCharsetEncoder(final Charset cs, final float averageBytesPerChar, final float maxBytesPerChar, final byte[] replacement) {

            super(cs, averageBytesPerChar, maxBytesPerChar, replacement);
        }

        /**
         * Podstawowy konstruktor obiektu klasy <code>MazoviaCharsetEncoder</code>
         *
         * @param cs                  Bufor kodowanych znaków
         * @param averageBytesPerChar
         * @param maxBytesPerChar
         */
        public MazoviaCharsetEncoder(final Charset cs, final float averageBytesPerChar, final float maxBytesPerChar) {

            super(cs, averageBytesPerChar, maxBytesPerChar);
        }

        /*
         * (non-Javadoc)
         *
         * @see java.nio.charset.CharsetEncoder#encodeLoop(java.nio.CharBuffer, java.nio.ByteBuffer)
         */
        @Override
        protected CoderResult encodeLoop(final CharBuffer in, final ByteBuffer out) {

            while (in.hasRemaining()) {
                char inputChar = in.get();

                for (final char[] CHARS_UNICODE_SORT1 : MazoviaCharset.CHARS_UNICODE_SORT) {
                    if (inputChar == CHARS_UNICODE_SORT1[0]) {
                        inputChar = CHARS_UNICODE_SORT1[1];
                    }
                }
                out.put((byte)(inputChar & 0xFF));
            }
            return CoderResult.UNDERFLOW;
        }

    }

    /**
     * Klasa realizuje mechanizm dekodowania znaków dla standardów Mazovia
     *
     * @author andrzej.radziszewski
     * @version 1.0.0.0
     * @since 2017
     */
    private final class MazoviaCharsetDecoder extends CharsetDecoder {

        /**
         * Podstawowy kostruktor obiektu klasy <code>MazoviaCharsetDecoder</code>
         *
         * @param cs
         * @param averageCharsPerByte
         * @param maxCharsPerByte
         */
        public MazoviaCharsetDecoder(final Charset cs, final float averageCharsPerByte, final float maxCharsPerByte) {

            super(cs, averageCharsPerByte, maxCharsPerByte);
        }

        /*
         * (non-Javadoc)
         *
         * @see java.nio.charset.CharsetDecoder#decodeLoop(java.nio.ByteBuffer, java.nio.CharBuffer)
         */
        @Override
        protected CoderResult decodeLoop(final ByteBuffer in, final CharBuffer out) {

            while (in.hasRemaining()) {
                char inputChar = (char)(in.get() & 0x00FF);

                for (final char[] CHARS_UNICODE_SORT1 : MazoviaCharset.CHARS_UNICODE_SORT) {
                    if (inputChar == CHARS_UNICODE_SORT1[1]) {
                        inputChar = CHARS_UNICODE_SORT1[0];
                    }
                }
                out.put(inputChar);
            }

            return CoderResult.UNDERFLOW;
        }

    }
}
