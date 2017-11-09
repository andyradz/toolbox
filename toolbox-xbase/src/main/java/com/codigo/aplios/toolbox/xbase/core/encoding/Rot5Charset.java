package com.codigo.aplios.toolbox.xbase.core.encoding;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/**
 * Klasa implementuje
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category charset
 */
public class Rot5Charset extends Charset {

    private class Rot5CharsetDecoder extends CharsetDecoder {

        private final CharsetDecoder baseDecoder;

        public Rot5CharsetDecoder(final Charset cs, final CharsetDecoder baseDecoder) {

            super(cs, baseDecoder.averageCharsPerByte(), baseDecoder.
                    maxCharsPerByte());
            this.baseDecoder = baseDecoder;
        }

        @Override
        protected CoderResult decodeLoop(final ByteBuffer in, final CharBuffer out) {

            this.baseDecoder.reset();
            final CoderResult result = this.baseDecoder.decode(in, out, true);
            Rot5Charset.this.rot5(out);
            return result;
        }

    }

    /**
     * @author andrzej.radziszewski
     *
     */
    private class Rot5CharsetEncoder extends CharsetEncoder {

        private final CharsetEncoder baseEncoder;

        public Rot5CharsetEncoder(final Charset cs, final CharsetEncoder baseEncoder) {

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
            Rot5Charset.this.rot5(buffer);
            this.baseEncoder.reset();
            final CoderResult result = this.baseEncoder.
                    encode(buffer, out, true);
            in.position(in.position() - buffer.remaining());
            return result;
        }

    }

    private static final String BASE_CHARSET_NAME = "UTF-8";

    private final Charset baseCharset;

    public Rot5Charset(final String canonical, final String[] aliases) {

        super(canonical, aliases);
        this.baseCharset = Charset.forName(Rot5Charset.BASE_CHARSET_NAME);
    }

    @Override
    public boolean contains(final Charset cs) {

        return cs.equals(this);
    }

    @Override
    public CharsetDecoder newDecoder() {

        return new Rot5CharsetDecoder(this, this.baseCharset.newDecoder());
    }

    @Override
    public CharsetEncoder newEncoder() {

        return new Rot5CharsetEncoder(this, this.baseCharset.newEncoder());
    }

    private void rot5(final CharBuffer cb) {

        for (int pos = cb.position(); pos < cb.limit(); pos++) {
            final char c = cb.get(pos);

            if ((c >= 48) && (c <= 57)) {
                if (c <= 52) {
                    cb.put(pos, (char)(c + 5));
                } else {
                    cb.put(pos, (char)(c - 5));
                }
            } else {
                cb.put(pos, c);
            }
        }
    }

}
