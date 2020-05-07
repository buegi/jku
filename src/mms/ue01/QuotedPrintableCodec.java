package mms.ue01;

public class QuotedPrintableCodec {

    public static final char DEFAULT_QUOTE_CHAR = '=';

    private char quoteChar;

    public QuotedPrintableCodec() {
        this(DEFAULT_QUOTE_CHAR);
    }

    public QuotedPrintableCodec(char quoteChar) {
        this.quoteChar = quoteChar;
    }

    public String encode(String plain) {
        StringBuffer quoted = new StringBuffer();
        /* @TODO Place your implementation here */
        for (int i = 0; i < plain.length(); i++) {
            int ascii = plain.charAt(i);
            if (ascii != 9 && ascii != DEFAULT_QUOTE_CHAR && (ascii < 32 || ascii > 126)) {
                quoted.append(DEFAULT_QUOTE_CHAR + Integer.toHexString(ascii).toUpperCase());
            } else {
                quoted.append(plain.charAt(i));
            }
        }
        return quoted.toString();
    }
    /* @TODO Place your implementation here */

    public String decode(String quoted) {
        StringBuffer plain = new StringBuffer();
        /* @TODO Place your implementation here */
        for (int i = 0; i < quoted.length(); i++) {
            if (quoted.charAt(i) == DEFAULT_QUOTE_CHAR) {
                StringBuffer hex = new StringBuffer();
                hex.append(quoted.charAt(i + 1));
                hex.append(quoted.charAt(i + 2));
                plain.append((char) Integer.parseInt(hex.toString(), 16));
                i += 2;
            } else {
                plain.append(quoted.charAt(i));
            }
        }
        return plain.toString();
    }
    /* @TODO Place your implementation here */
}