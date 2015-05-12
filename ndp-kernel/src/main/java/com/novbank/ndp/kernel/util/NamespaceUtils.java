package com.novbank.ndp.kernel.util;

import com.novbank.ndp.kernel.exception.InvalidNamespaceException;
import com.novbank.ndp.kernel.exception.RepositoryRuntimeException;

import javax.jcr.NamespaceException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Utilities for working with the JCR Namespace Registry
 *
 * Created by CaoKe on 2015/5/12.
 */
public class NamespaceUtils {
    /**
     * Return the javax.jcr.NamespaceRegistry associated with the arg session.
     *
     * @param session
     * @return NamespaceRegistry
     */
    public static javax.jcr.NamespaceRegistry getNamespaceRegistry(final Session session) {
        final javax.jcr.NamespaceRegistry namespaceRegistry;
        try {
            namespaceRegistry =
                    session.getWorkspace().getNamespaceRegistry();
            checkNotNull(namespaceRegistry,
                    "Couldn't find namespace registry in repository!");
            return namespaceRegistry;
        } catch (final RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    /**
     * Validate resource path for unregistered namespace prefixes
     *
     * @param session the JCR session to use
     * @param path the absolute path to the object
     * @throws InvalidNamespaceException on unregistered namespaces
     * @throws RepositoryRuntimeException if repository runtime exception occurred
     */
    public static void validatePath(final Session session, final String path) {

        final javax.jcr.NamespaceRegistry namespaceRegistry = getNamespaceRegistry(session);

        final String relPath = path.replaceAll("^/+", "").replaceAll("/+$", "");
        final String[] pathSegments = relPath.split("/");
        for (final String segment : pathSegments) {
            if (segment.length() > 0 && segment.contains(":") &&
                    !segment.substring(0, segment.indexOf(':')).equals("fedora")) {
                final String prefix = segment.substring(0, segment.indexOf(':'));
                if (prefix.length() == 0) {
                    throw new InvalidNamespaceException(
                            String.format("Unable to identify namespace for (%s)", segment));
                }
                try {
                    namespaceRegistry.getURI(prefix);
                } catch (final NamespaceException e) {
                    throw new InvalidNamespaceException(
                            String.format("The namespace prefix (%s) has not been registered", prefix), e);
                } catch (final RepositoryException e) {
                    throw new RepositoryRuntimeException(e);
                }
            }
        }
    }
}
