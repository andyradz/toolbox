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
 * Klasa realizuje mechanizm kodowania i dekodowania znaków zgodnie ze
 * specyfikacją algorytmu ROT47
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @category charset
 */
public class Rot47Charset extends Charset {

    /**
     * Nazwa podstawowego algorytmu kodowania znaków
     */
    private static final String BASE_CHARSET_NAME = "utf-8";

    /**
     * Podstawowy konstruktor obiektu klasy<code>Rot47Charset</code>
     *
     * @param canonical
     * @param aliases
     */
    public Rot47Charset(final String canonical, final String[] aliases) {

        super(canonical, aliases);
        this.baseCharset = Charset.forName(Rot47Charset.BASE_CHARSET_NAME);
    }

    @Override
    public boolean contains(final Charset cs) {

        return cs.equals(this);
    }

    @Override
    public CharsetDecoder newDecoder() {

        return new Rot47CharsetDecoder(this, this.baseCharset.newDecoder());
    }

    @Override
    public CharsetEncoder newEncoder() {

        return new Rot47CharsetEncoder(this, this.baseCharset.newEncoder());
    }

    private final Charset baseCharset;

    private void rot47(final CharBuffer cb) {

        for (int pos = cb.position(); pos < cb.limit(); pos++) {
            char c = cb.get(pos);

            if (((byte)c >= 33) && ((byte)c <= 126)) {
                c = (char)(33 + ((c + 14) % 94));
                cb.put(pos, c);
            }
        }
    }

    private class Rot47CharsetDecoder extends CharsetDecoder {

        private final CharsetDecoder baseDecoder;

        public Rot47CharsetDecoder(final Charset cs, final CharsetDecoder baseDecoder) {

            super(cs, baseDecoder.averageCharsPerByte(), baseDecoder.
                    maxCharsPerByte());
            this.baseDecoder = baseDecoder;
        }

        @Override
        protected CoderResult decodeLoop(final ByteBuffer in, final CharBuffer out) {

            this.baseDecoder.reset();
            final CoderResult result = this.baseDecoder.decode(in, out, true);
            Rot47Charset.this.rot47(out);
            return result;
        }

    }

    private class Rot47CharsetEncoder extends CharsetEncoder {

        private final CharsetEncoder baseEncoder;

        public Rot47CharsetEncoder(final Charset cs, final CharsetEncoder baseEncoder) {

            super(cs, baseEncoder.averageBytesPerChar(), baseEncoder.
                    maxBytesPerChar());
            this.baseEncoder = baseEncoder;
        }

        @Override
        protected CoderResult encodeLoop(final CharBuffer in, final ByteBuffer out) {

            final CharBuffer buffer = CharBuffer.allocate(in.remaining());
            while (in.hasRemaining()) {
                buffer.put(in.get());
            }
            buffer.rewind();
            Rot47Charset.this.rot47(buffer);
            this.baseEncoder.reset();
            final CoderResult result = this.baseEncoder.
                    encode(buffer, out, true);
            in.position(in.position() - buffer.remaining());
            return result;
        }

    }
}
