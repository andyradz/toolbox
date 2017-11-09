package com.codigo.aplios.toolbox.xbase.core.encoding;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/**
 * @author andrzej.radziszewski
 *
 */
public class KamenickyCharset extends Charset {

    public static void main(final String[] args) {

    }

    public static final char[][] CHARS_UNICODE_SORT = {
        {'\u010C', 128},
        {'\u00FC', 129},
        {'\u00E9', 130},
        {'\u010F', 131},
        {'\u00E4', 132},
        {'\u010E', 133},
        {'\u0164', 134},
        {'\u010D', 135},
        {'\u011B', 136},
        {'\u011A', 137},
        {'\u0139', 138},
        {'\u00CD', 139},
        {'\u013E', 140},
        {'\u013A', 141},
        {'\u00C4', 142},
        {'\u00C1', 143},
        {'\u00C9', 144},
        {'\u017E', 145},
        {'\u017D', 146},
        {'\u00F4', 147},
        {'\u00F6', 148},
        {'\u00D3', 149},
        {'\u016F', 150},
        {'\u00DA', 151},
        {'\u00FD', 152},
        {'\u00D6', 153},
        {'\u00DC', 154},
        {'\u0160', 155},
        {'\u013D', 156},
        {'\u00DD', 157},
        {'\u0158', 158},
        {'\u0165', 159},
        {'\u00E1', 160},
        {'\u00ED', 161},
        {'\u00F3', 162},
        {'\u00FA', 163},
        {'\u0148', 164},
        {'\u0147', 165},
        {'\u016E', 166},
        {'\u00D4', 167},
        {'\u0161', 168},
        {'\u0159', 169},
        {'\u0155', 170},
        {'\u0154', 171},
        {'\u00BC', 172},
        {'\u00A1', 173},
        {'\u00AB', 174},
        {'\u00BB', 175},
        {'\u2591', 176},
        {'\u2592', 177},
        {'\u2593', 178},
        {'\u2502', 179},
        {'\u2524', 180},
        {'\u2561', 181},
        {'\u2562', 182},
        {'\u2556', 183},
        {'\u2555', 184},
        {'\u2563', 185},
        {'\u2551', 186},
        {'\u2557', 187},
        {'\u255D', 188},
        {'\u255C', 189},
        {'\u255B', 190},
        {'\u2510', 191},
        {'\u2514', 192},
        {'\u2534', 193},
        {'\u252C', 194},
        {'\u251C', 195},
        {'\u2500', 196},
        {'\u253C', 197},
        {'\u255E', 198},
        {'\u255F', 199},
        {'\u255A', 200},
        {'\u2554', 201},
        {'\u2569', 202},
        {'\u2566', 203},
        {'\u2560', 204},
        {'\u2550', 205},
        {'\u256C', 206},
        {'\u2567', 207},
        {'\u2568', 208},
        {'\u2564', 209},
        {'\u2565', 210},
        {'\u2559', 211},
        {'\u2558', 212},
        {'\u2552', 213},
        {'\u2553', 214},
        {'\u256B', 215},
        {'\u256A', 216},
        {'\u2518', 217},
        {'\u250C', 218},
        {'\u2588', 219},
        {'\u2584', 220},
        {'\u258C', 221},
        {'\u2590', 222},
        {'\u2580', 223},
        {'\u03B1', 224},
        {'\u00DF', 225},
        {'\u0393', 226},
        {'\u03C0', 227},
        {'\u03A3', 228},
        {'\u03C3', 229},
        {'\u00B5', 230},
        {'\u03C4', 231},
        {'\u03A6', 232},
        {'\u0398', 233},
        {'\u03A9', 234},
        {'\u03B4', 235},
        {'\u221E', 236},
        {'\u03C6', 237},
        {'\u03B5', 238},
        {'\u2229', 239},
        {'\u2261', 240},
        {'\u00B1', 241},
        {'\u2265', 242},
        {'\u2264', 243},
        {'\u2320', 244},
        {'\u2321', 245},
        {'\u00F7', 246},
        {'\u2248', 247},
        {'\u00B0', 248},
        {'\u2219', 249},
        {'\u00B7', 250},
        {'\u221A', 251},
        {'\u207F', 252},
        {'\u00B2', 253},
        {'\u25A0', 254},
        {'\u00A0', 255}};

    public KamenickyCharset(final String canonicalName, final String[] aliases) {

        super(canonicalName, aliases);
    }

    @Override
    public boolean contains(final Charset cs) {

        return cs.equals(this);
    }

    @Override
    public CharsetDecoder newDecoder() {

        return new KamenickyCharsetDecoder(this, 1, 1);
    }

    @Override
    public CharsetEncoder newEncoder() {

        return new KamenickyCharsetEncoder(this, 1, 1);
    }

    public class KamenickyCharsetEncoder extends CharsetEncoder {

        public KamenickyCharsetEncoder(final Charset cs, final float averageBytesPerChar, final float maxBytesPerChar, final byte[] replacement) {

            super(cs, averageBytesPerChar, maxBytesPerChar, replacement);
        }

        public KamenickyCharsetEncoder(final Charset cs, final float averageBytesPerChar, final float maxBytesPerChar) {

            super(cs, averageBytesPerChar, maxBytesPerChar);
        }

        @Override
        protected CoderResult encodeLoop(final CharBuffer in, final ByteBuffer out) {

            while (in.hasRemaining()) {
                char inputChar = in.get();

                for (final char[] element : KamenickyCharset.CHARS_UNICODE_SORT)
                    if (inputChar == element[0])
                        inputChar = element[1];

                out.put((byte)(inputChar & 0xFF));
            }
            return CoderResult.UNDERFLOW;
        }

    }

    /**
     *
     * @author andrzej.radziszewski
     *
     */
    public class KamenickyCharsetDecoder extends CharsetDecoder {

        public KamenickyCharsetDecoder(final Charset cs, final float averageCharsPerByte, final float maxCharsPerByte) {

            super(cs, averageCharsPerByte, maxCharsPerByte);
        }

        @Override
        protected CoderResult decodeLoop(final ByteBuffer in, final CharBuffer out) {

            while (in.hasRemaining()) {
                char inputChar = (char)(in.get() & 0x00FF);

                for (final char[] element : KamenickyCharset.CHARS_UNICODE_SORT)
                    if (inputChar == element[1])
                        inputChar = element[0];
                out.put(inputChar);
            }

            return CoderResult.UNDERFLOW;
        }

    }
}
