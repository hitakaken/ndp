package com.novbank.ndp.kernel.impl.service.result;

import com.google.common.collect.ImmutableSet;
import com.novbank.ndp.kernel.util.FixityInputStream;
import com.novbank.ndp.kernel.service.result.CacheEntry;
import com.novbank.ndp.kernel.service.result.FixityResult;
import com.novbank.ndp.kernel.util.ContentDigest;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.io.IOException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import static com.google.common.base.Throwables.propagate;
import static org.apache.commons.io.output.NullOutputStream.NULL_OUTPUT_STREAM;

/**
 * Created by hp on 2015/5/18.
 */
public abstract class AbstractCacheEntry implements CacheEntry {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCacheEntry.class);
    /**
     * Calculate the fixity of a CacheEntry by piping it through
     * a simple fixity-calculating InputStream
     *
     * @param digest the digest
     * @return the fixity of this cache entry
     * @throws RepositoryException if repository exception occurred
     */
    @Override
    public Collection<FixityResult> checkFixity(final String digest)
            throws RepositoryException {

        try (FixityInputStream fixityInputStream = new FixityInputStream(this.getInputStream(),
                MessageDigest.getInstance(digest))) {

            IOUtils.copy(fixityInputStream, NULL_OUTPUT_STREAM);

            final URI calculatedChecksum = ContentDigest.asURI(digest,
                    fixityInputStream.getMessageDigest().digest());
            final FixityResult result =
                    new FixityResultImpl(this,
                            fixityInputStream.getByteCount(),
                            calculatedChecksum);

            LOGGER.debug("Got {}", result.toString());

            return ImmutableSet.of(result);
        } catch (final IOException e) {
            LOGGER.debug("Got error closing input stream: {}", e);
            throw propagate(e);
        } catch (final NoSuchAlgorithmException e1) {
            throw propagate(e1);
        }

    }

}
