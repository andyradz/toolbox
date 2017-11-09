package com.codigo.aplios.toolbox.xbase.core.encoding;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/**
 * Klasa implementuje kodowanie znaków w standardzie ROT13.
 * Przykładowe użycie: <code>Charset.forName("ROT13")</code>
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category charset
 */
public final class Rot13Charset extends Charset {

    /**
     * Domyślna nazwa standardu kodowania znaków.
     */
    private static final String BASE_CHARSET_NAME = "UTF-8";

    /**
     * Atrybut reprezentuje podstawowy rodzaj kodowania znaków
     */
    private final Charset baseCharset;

    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param canonical Potoczna nazwa standardu kodowania znaków
     * @param aliases   Aliasy nazw dla standaru kodowania znaków
     */
    public Rot13Charset(final String canonical, final String[] aliases) {

        super(canonical, aliases);
        this.baseCharset = Charset.forName(Rot13Charset.BASE_CHARSET_NAME);
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

        return new Rot13CharsetDecoder(this, this.baseCharset.newDecoder());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.nio.charset.Charset#newEncoder()
     */
    @Override
    public CharsetEncoder newEncoder() {

        return new Rot13CharsetEncoder(this, this.baseCharset.newEncoder());
    }

    /**
     * Metoda implementuje mechanizm kodowanie/dekodowania znaków dla standardu
     * ROT13
     *
     * @param cb Bufor prztetwarznych znaków
     */
    private void rot13(final CharBuffer cb) {

        for (int pos = cb.position(); pos < cb.limit(); pos++) {
            char c = cb.get(pos);
            char a = '\u0000';

            // Is it lower case alpha?
            if ((c >= 'a') && (c <= 'z')) {
                a = 'a';
            }

            // Is it upper case alpha?
            if ((c >= 'A') && (c <= 'Z')) {
                a = 'A';
            }

            // If either, roll it by 13
            if (a != '\u0000') {
                c = (char)((((c - a) + 13) % 26) + a);
                cb.put(pos, c);
            }
        }
    }

    /**
     * Klasa implementuje mechanizm dekodera dla standarku ROT13
     *
     * @author andrzej.radziszewski
     * @version 1.0.0.0
     * @since 2017
     */
    private class Rot13CharsetDecoder extends CharsetDecoder {

        /**
         * Atrybut reprezentuje podstawowy dekoder dla standardu R0T13
         */
        private final CharsetDecoder baseDecoder;

        /**
         * Podstawowy konstruktor obiektu klasy <code>Rot13CharsetDecoder</code>
         *
         * @param cs
         * @param baseDecoder
         */
        public Rot13CharsetDecoder(final Charset cs, final CharsetDecoder baseDecoder) {

            super(cs, baseDecoder.averageCharsPerByte(), baseDecoder.
                    maxCharsPerByte());
            this.baseDecoder = baseDecoder;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.nio.charset.CharsetDecoder#decodeLoop(java.nio.ByteBuffer,
         * java.nio.CharBuffer)
         */
        @Override
        protected CoderResult decodeLoop(final ByteBuffer in, final CharBuffer out) {

            this.baseDecoder.reset();
            final CoderResult result = this.baseDecoder.decode(in, out, true);
            Rot13Charset.this.rot13(out);
            return result;
        }

    }

    /**
     * Klasa implementuje mechanizm kodera dla standarku ROT13
     *
     * @author andrzej.radziszewski
     * @version 1.0.0.0
     * @since 2017
     */
    private final class Rot13CharsetEncoder extends CharsetEncoder {

        /**
         * Atrybut reprezentuje podstawowy koder standardu R)T13
         */
        private final CharsetEncoder baseEncoder;

        /**
         * Podstawowy konstruktor obiektu klasy <code>Rot13CharsetEncoder</code>
         *
         * @param cs          Nazwa roadzju kodowania znaków
         * @param baseEncoder Podstawowy koder dla standardu ROT13
         */
        public Rot13CharsetEncoder(final Charset cs, final CharsetEncoder baseEncoder) {

            super(cs, baseEncoder.averageBytesPerChar(), baseEncoder.
                    maxBytesPerChar());
            this.baseEncoder = baseEncoder;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.nio.charset.CharsetEncoder#encodeLoop(java.nio.CharBuffer,
         * java.nio.ByteBuffer)
         */
        @Override
        protected CoderResult encodeLoop(final CharBuffer in, final ByteBuffer out) {

            final CharBuffer buffer = CharBuffer.allocate(in.remaining());
            while (in.hasRemaining()) {
                buffer.put(in.get());
            }
            buffer.rewind();
            Rot13Charset.this.rot13(buffer);
            this.baseEncoder.reset();
            final CoderResult result = this.baseEncoder.
                    encode(buffer, out, true);
            in.position(in.position() - buffer.remaining());
            return result;
        }

    }
}
