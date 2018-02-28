package com.bryan.common.coder;

import java.io.*;

/**
 * 编码类
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/28
 */
public abstract class CharacterEncoder {

    public CharacterEncoder() {
    }

    protected abstract int bytesPerAtom();

    protected abstract int bytesPerLine();

    protected void encodeBufferPrefix(OutputStream outputstream)
            throws IOException {
        pStream = new PrintStream(outputstream);
    }

    protected void encodeBufferSuffix(OutputStream outputstream)
            throws IOException {
    }

    protected void encodeLinePrefix(OutputStream outputstream, int i)
            throws IOException {
    }

    protected void encodeLineSuffix(OutputStream outputstream)
            throws IOException {
        //pStream.println();
    }

    protected abstract void encodeAtom(OutputStream outputstream, byte abyte0[], int i, int j)
            throws IOException;

    protected int readFully(InputStream inputstream, byte abyte0[])
            throws IOException {
        for (int i = 0; i < abyte0.length; i++) {
            int j = inputstream.read();
            if (j == -1)
                return i;
            abyte0[i] = (byte) j;
        }

        return abyte0.length;
    }

    public void encode(InputStream inputstream, OutputStream outputstream)
            throws IOException {
        byte abyte0[] = new byte[bytesPerLine()];
        encodeBufferPrefix(outputstream);
        do {
            int j = readFully(inputstream, abyte0);
            if (j == 0)
                break;
            encodeLinePrefix(outputstream, j);
            for (int i = 0; i < j; i += bytesPerAtom())
                if (i + bytesPerAtom() <= j)
                    encodeAtom(outputstream, abyte0, i, bytesPerAtom());
                else
                    encodeAtom(outputstream, abyte0, i, j - i);

            if (j < bytesPerLine())
                break;
            encodeLineSuffix(outputstream);
        } while (true);
        encodeBufferSuffix(outputstream);
    }

    public void encode(byte abyte0[], OutputStream outputstream)
            throws IOException {
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        encode(bytearrayinputstream, outputstream);
    }

    public String encode(byte abyte0[]) {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        String s;
        try {
            encode(bytearrayinputstream, bytearrayoutputstream);
            s = bytearrayoutputstream.toString("8859_1");
        } catch (Exception exception) {
            throw new Error("ChracterEncoder::encodeBuffer internal error");
        }
        return s;
    }

    public void encodeBuffer(InputStream inputstream, OutputStream outputstream)
            throws IOException {
        byte abyte0[] = new byte[bytesPerLine()];
        encodeBufferPrefix(outputstream);
        int j;
        do {
            j = readFully(inputstream, abyte0);
            if (j == 0)
                break;
            encodeLinePrefix(outputstream, j);
            for (int i = 0; i < j; i += bytesPerAtom())
                if (i + bytesPerAtom() <= j)
                    encodeAtom(outputstream, abyte0, i, bytesPerAtom());
                else
                    encodeAtom(outputstream, abyte0, i, j - i);

            encodeLineSuffix(outputstream);
        } while (j >= bytesPerLine());
        encodeBufferSuffix(outputstream);
    }

    public void encodeBuffer(byte abyte0[], OutputStream outputstream)
            throws IOException {
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        encodeBuffer(bytearrayinputstream, outputstream);
    }

    public String encodeBuffer(byte abyte0[]) {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        try {
            encodeBuffer(bytearrayinputstream, bytearrayoutputstream);
        } catch (Exception exception) {
            throw new Error("ChracterEncoder::encodeBuffer internal error");
        }
        return bytearrayoutputstream.toString();
    }

    private static int mCurrentLineLength = 0;

    public static String encode_qp(byte[] content) {
        if (content == null) {
            return null;
        }
        StringBuilder out = new StringBuilder();

        mCurrentLineLength = 0;
        int requiredLength;
        for (byte c : content) {
            if ((c >= 33) && (c <= 126) && (c != 61)) {
                requiredLength = 1;
                checkLineLength(requiredLength, out);
                out.append((char) c);
            } else {
                requiredLength = 3;
                checkLineLength(requiredLength, out);
                out.append('=');
                out.append(String.format("%02X", new Object[]{c}));
            }
        }
        return out.toString();
    }

    private static void checkLineLength(int required, StringBuilder out) {
        if (required + mCurrentLineLength > 998) {
            out.append("=/r/n");
            mCurrentLineLength = required;
        } else {
            mCurrentLineLength += required;
        }
    }

    protected PrintStream pStream;
}
