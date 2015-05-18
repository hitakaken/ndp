package com.novbank.ndp.kernel.util;

import com.novbank.ndp.kernel.exception.RepositoryRuntimeException;
import org.apache.commons.codec.binary.Hex;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hp on 2015/5/18.
 */
public class SecureHash {
    public static byte[] getHash(SecureHash.Algorithm algorithm, byte[] content) throws NoSuchAlgorithmException {
        checkNotNull(algorithm, "algorithm");
        return getHash(algorithm.digestName(), content);
    }

    public static byte[] getHash(SecureHash.Algorithm algorithm, File file) throws NoSuchAlgorithmException, IOException {
        checkNotNull(algorithm, "algorithm");
        return getHash(algorithm.digestName(), file);
    }

    public static byte[] getHash(SecureHash.Algorithm algorithm, InputStream stream) throws NoSuchAlgorithmException, IOException {
        checkNotNull(algorithm, "algorithm");
        return getHash(algorithm.digestName(), stream);
    }

    public static byte[] getHash(String digestName, byte[] content) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(digestName);

        assert digest != null;

        return digest.digest(content);
    }

    public static byte[] getHash(String digestName, File file) throws NoSuchAlgorithmException, IOException {
        checkNotNull(file, "file");
        MessageDigest digest = MessageDigest.getInstance(digestName);

        assert digest != null;

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        boolean error = false;

        try {
            short e = 1024;
            byte[] buffer = new byte[e];

            for(int n = in.read(buffer, 0, e); n != -1; n = in.read(buffer, 0, e)) {
                digest.update(buffer, 0, n);
            }
        } catch (IOException var15) {
            error = true;
            throw var15;
        } finally {
            try {
                in.close();
            } catch (IOException var14) {
                if(!error) {
                    throw var14;
                }
            }

        }

        return digest.digest();
    }

    public static byte[] getHash(String digestName, InputStream stream) throws NoSuchAlgorithmException, IOException {
        checkNotNull(stream, "stream");
        MessageDigest digest = MessageDigest.getInstance(digestName);

        assert digest != null;

        short bufSize = 1024;
        byte[] buffer = new byte[bufSize];

        for(int n = stream.read(buffer, 0, bufSize); n != -1; n = stream.read(buffer, 0, bufSize)) {
            digest.update(buffer, 0, n);
        }

        return digest.digest();
    }

    public static SecureHash.HashingInputStream createHashingStream(SecureHash.Algorithm algorithm, InputStream inputStream) throws NoSuchAlgorithmException {
        return createHashingStream(algorithm.digestName(), inputStream);
    }

    public static SecureHash.HashingInputStream createHashingStream(String digestName, InputStream inputStream) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(digestName);
        return new SecureHash.HashingInputStream(digest, inputStream);
    }

    public static SecureHash.HashingReader createHashingReader(SecureHash.Algorithm algorithm, Reader reader, Charset charset) throws NoSuchAlgorithmException {
        return createHashingReader(algorithm.digestName(), reader, charset);
    }

    public static SecureHash.HashingReader createHashingReader(String digestName, Reader reader, Charset charset) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(digestName);
        return new SecureHash.HashingReader(digest, reader, charset);
    }

    public static String asHexString(byte[] hash) {
        return hash != null? Hex.encodeHexString(hash):null;
    }

    public static String sha1(String string) {
        try {
            byte[] e = getHash(SecureHash.Algorithm.SHA_1, string.getBytes());
            return asHexString(e);
        } catch (NoSuchAlgorithmException var2) {
            throw new RepositoryRuntimeException(var2);
        }
    }

    private SecureHash() {
    }

    public static class HashingReader extends Reader {
        private final MessageDigest digest;
        private final Reader stream;
        private byte[] hash;
        private final CharsetEncoder encoder;

        protected HashingReader(MessageDigest digest, Reader input, Charset charset) {
            this.digest = digest;
            this.stream = input;
            this.encoder = charset.newEncoder();
        }

        public int read() throws IOException {
            int result = this.stream.read();
            if(result != -1) {
                this.digest.update((byte)result);
            }

            return result;
        }

        public int read(char[] b, int off, int len) throws IOException {
            int n = this.stream.read(b, off, len);
            if(n != -1) {
                byte[] bytes = this.encoder.encode(CharBuffer.wrap(b)).array();
                this.digest.update(bytes, off, n);
            }

            return n;
        }

        public int read(char[] b) throws IOException {
            int n = this.stream.read(b);
            if(n != -1) {
                byte[] bytes = this.encoder.encode(CharBuffer.wrap(b)).array();
                this.digest.update(bytes, 0, n);
            }

            return n;
        }

        public void close() throws IOException {
            this.stream.close();
            if(this.hash == null) {
                this.hash = this.digest.digest();
            }

        }

        public byte[] getHash() {
            return this.hash;
        }

        public String getHashAsHexString() {
            return SecureHash.asHexString(this.hash);
        }
    }

    public static class HashingInputStream extends InputStream {
        private final MessageDigest digest;
        private final InputStream stream;
        private byte[] hash;

        protected HashingInputStream(MessageDigest digest, InputStream input) {
            this.digest = digest;
            this.stream = input;
        }

        public int read() throws IOException {
            int result = this.stream.read();
            if(result != -1) {
                this.digest.update((byte)result);
            }

            return result;
        }

        public int read(byte[] b, int off, int len) throws IOException {
            int n = this.stream.read(b, off, len);
            if(n != -1) {
                this.digest.update(b, off, n);
            }

            return n;
        }

        public int read(byte[] b) throws IOException {
            int n = this.stream.read(b);
            if(n != -1) {
                this.digest.update(b, 0, n);
            }

            return n;
        }

        public void close() throws IOException {
            this.stream.close();
            if(this.hash == null) {
                this.hash = this.digest.digest();
            }

        }

        public byte[] getHash() {
            return this.hash;
        }

        public String getHashAsHexString() {
            return SecureHash.asHexString(this.hash);
        }
    }

    public enum Algorithm {
        MD2("MD2", 128, "The MD2 message digest algorithm as defined in RFC 1319"),
        MD5("MD5", 128, "The MD5 message digest algorithm as defined in RFC 1321"),
        SHA_1("SHA-1", 160, "The Secure Hash Algorithm, as defined in Secure Hash Standard, NIST FIPS 180-1"),
        SHA_256("SHA-256", 256, "New hash algorithms for which the draft Federal Information Processing Standard 180-2, Secure Hash Standard (SHS) is now available.  SHA-256 is a 256-bit hash function intended to provide 128 bits of security against collision attacks."),
        SHA_384("SHA-384", 384, "New hash algorithms for which the draft Federal Information Processing Standard 180-2, Secure Hash Standard (SHS) is now available.  A 384-bit hash may be obtained by truncating the SHA-512 output."),
        SHA_512("SHA-512", 512, "New hash algorithms for which the draft Federal Information Processing Standard 180-2, Secure Hash Standard (SHS) is now available.  SHA-512 is a 512-bit hash function intended to provide 256 bits of security.");

        private final String name;
        private final String description;
        private final int numberOfBits;
        private final int numberOfBytes;
        private final int numberOfHexChars;

        private Algorithm(String name, int numberOfBits, String description) {
            assert numberOfBits % 8 == 0;

            this.name = name;
            this.description = description;
            this.numberOfBits = numberOfBits;
            this.numberOfBytes = this.numberOfBits / 8;
            this.numberOfHexChars = this.numberOfBits / 4;
        }

        public String digestName() {
            return this.name;
        }

        public String description() {
            return this.description;
        }

        public int getHexadecimalStringLength() {
            return this.numberOfHexChars;
        }

        public boolean isHexadecimal(String string) {
            return string.length() == this.getHexadecimalStringLength() && HexUtils.isHexString(string);
        }

        public int getNumberOfBytes() {
            return this.numberOfBytes;
        }

        public int getNumberOfBits() {
            return this.numberOfBits;
        }

        public String toString() {
            return this.digestName();
        }
    }
}
