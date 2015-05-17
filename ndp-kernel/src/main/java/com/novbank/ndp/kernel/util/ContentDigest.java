package com.novbank.ndp.kernel.util;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static com.google.common.base.Throwables.propagate;
import static java.util.Collections.singletonMap;

/**
 * Digest helpers to convert digests (checksums) into URI strings
 * (based loosely on Magnet URIs)
 *
 *  Created by CaoKe on 2015/5/16.
 */
public class ContentDigest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentDigest.class);

    public static final Map<String, String> algorithmToScheme = ImmutableMap
            .of("SHA-1", "urn:sha1", "SHA1", "urn:sha1","MD5","urn:md5");

    public static final Map<String, String> schemeToAlgorithm = ImmutableMap
            .of("urn:sha1", "SHA-1", "urn:md5", "MD5");

    public static final String DEFAULT_ALGORITHM = "SHA-1";

    private ContentDigest() {
    }

    /**
     * Convert a MessageDigest algorithm and checksum value to a URN
     * @param algorithm the message digest algorithm
     * @param value the checksum value
     * @return URI
     */
    public static URI asURI(final String algorithm, final String value) {
        try {
            final String scheme = algorithmToScheme.get(algorithm);

            return new URI(scheme, value, null);
        } catch (final URISyntaxException unlikelyException) {
            LOGGER.warn("Exception creating checksum URI: {}",
                    unlikelyException);
            throw propagate(unlikelyException);
        }
    }

    /**
     * Convert a MessageDigest algorithm and checksum byte-array data to a URN
     * @param algorithm the message digest algorithm
     * @param data the checksum byte-array data
     * @return URI
     */
    public static URI asURI(final String algorithm, final byte[] data) {
        return asURI(algorithm, asString(data));
    }

    /**
     * Given a digest URI, get the corresponding MessageDigest algorithm
     * @param digestUri the digest uri
     * @return MessageDigest algorithm
     */
    public static String getAlgorithm(final URI digestUri) {
        if (digestUri == null) {
            return DEFAULT_ALGORITHM;
        }
        return schemeToAlgorithm
                .get(digestUri.getScheme() + ":" +
                        digestUri.getSchemeSpecificPart().split(":", 2)[0]);
    }

    private static String asString(final byte[] data) {
        return Hex.encodeHexString(data);
    }

    /**
     * Placeholder checksum value.
     * @return URI
     */
    public static URI missingChecksum() {
        return asURI("SHA-1", "missing");
    }
}
