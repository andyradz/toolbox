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
 *
 * @author andrzej.radziszewski
 */
public class IBMLatinCharset extends Charset {

    private static char[][] convertChars = new char[][]{{'\u0104', 164}// Ą
    ,
     {'\u0105', 165}// ą
    ,
     {'\u0106', 143}// Ć
    ,
     {'\u0107', 134}// ć
    ,
     {'\u0118', 168}// Ę
    ,
     {'\u0119', 169}// ę
    ,
     {'\u0141', 157}// Ł
    ,
     {'\u0142', 136}// ł
    ,
     {'\u0143', 227}// Ń
    ,
     {'\u0144', 228}// ń
    ,
     {'\u00D3', 224}// Ó
    ,
     {'\u00F3', 162}// ó
    ,
     {'\u015A', 151}// Ś
    ,
     {'\u015B', 152}// ś
    ,
     {'\u0179', 141}// Ź
    ,
     {'\u017A', 171}// ź
    ,
     {'\u017B', 189}// Ż
    ,
     {'\u017C', 190}// ż
    };

    public IBMLatinCharset(String canonicalName, String[] aliases) {

        super(canonicalName, aliases);
    }

    @Override
    public boolean contains(Charset cs) {

        return cs.equals(this);
    }

    @Override
    public CharsetDecoder newDecoder() {

        return new PrivCharsetDecoder(this, 1, 1);
    }

    @Override
    public CharsetEncoder newEncoder() {

        return new PrivCharsetEncoder(this, 1, 1);
    }

    public class PrivCharsetEncoder extends CharsetEncoder {

        public PrivCharsetEncoder(Charset cs, float averageBytesPerChar, float maxBytesPerChar, byte[] replacement) {

            super(cs, averageBytesPerChar, maxBytesPerChar, replacement);
        }

        public PrivCharsetEncoder(Charset cs, float averageBytesPerChar, float maxBytesPerChar) {

            super(cs, averageBytesPerChar, maxBytesPerChar);
        }

        @Override
        protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {

            while (in.hasRemaining()) {
                char inputChar = in.get();

                for (char[] convertChar : IBMLatinCharset.convertChars) {
                    if (inputChar == convertChar[0]) {
                        inputChar = convertChar[1];
                    }
                }

                out.put((byte)(inputChar & 0xFF));
            }
            return CoderResult.UNDERFLOW;
        }

    }

    /**
     * @author andrzej.radziszewski
     *
     */
    public class PrivCharsetDecoder extends CharsetDecoder {

        public PrivCharsetDecoder(Charset cs, float averageCharsPerByte, float maxCharsPerByte) {

            super(cs, averageCharsPerByte, maxCharsPerByte);
        }

        @Override
        protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {

            while (in.hasRemaining()) {
                char inputChar = (char)(in.get() & 0x00FF);

                for (char[] convertChar : IBMLatinCharset.convertChars) {
                    if (inputChar == convertChar[1]) {
                        inputChar = convertChar[0];
                    }
                }
                out.put(inputChar);
            }

            return CoderResult.UNDERFLOW;
        }

    }
}
