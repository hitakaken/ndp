package com.novbank.ndp.kernel.impl.util;

import org.apache.commons.io.input.CountingInputStream;

import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * An InputStream wrapper that calculates the size and digest
 * while reading from the stream.
 *
 * Created by hp on 2015/5/18.
 */
public class FixityInputStream extends CountingInputStream {
    /**
     * Creates a <code>FilterInputStream</code> by assigning the
     * argument <code>in</code> to the field <code>this.in</code>
     * so as to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     * @param digest the given digest
     */
    public FixityInputStream(final InputStream in, final MessageDigest digest) {
        super(new DigestInputStream(in, digest));
    }

    /**
     * Retrieve the calculated digest for the input stream
     * @return digest for this input stream
     */
    public MessageDigest getMessageDigest() {
        return ((DigestInputStream) in).getMessageDigest();
    }
}
