package com.novbank.ndp.kernel.model;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.version.Version;
import javax.jcr.version.VersionHistory;
import java.net.URI;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by CaoKe on 2015/5/12.
 */
public interface Resource {
    /**
     * @return The JCR node that backs this object.
     */
    Node getNode();

    /**
     * Get the path to the JCR node
     * @return path
     */
    String getPath();

    /**
     * Get the children of this resource
     * @return iterator
     */
    Iterator<Resource> getChildren();

    /**
     * Get the container of this resource
     * @return the container of this resource
     */
    Resource getContainer();

    /**
     * Get the child of this resource at the given path
     * @param relPath the given path
     * @return the child of this resource
     */
    Resource getChild(String relPath);

    /**
     * Does this resource have a property
     * @param relPath the given path
     * @return the boolean value whether the resource has a property
     */
    boolean hasProperty(String relPath);

    /**
     * Retrieve the given property value for this resource
     * @param relPath the given path
     * @return the property
     */
    Property getProperty(String relPath);

    /**
     * Set the given property value for this resource
     * @param relPath the given path
     * @param value the URI value
     */
    void setURIProperty(String relPath, URI value);

    /**
     * Delete this resource, and any inbound references to it
     */
    void delete();

    /**
     * Get the date this datastream was created
     * @return created date
     */
    Date getCreatedDate();

    /**
     * Get the date this datastream was last modified
     * @return last modified date
     */
    Date getLastModifiedDate();

    /**
     * Check if this object uses a given mixin
     * @param type the given type
     * @return a collection of mixin names
     */
    boolean hasType(final String type);

    /**
     * Get the JCR Base version for the node
     *
     * @return base version
     */
    Version getBaseVersion();

    /**
     * Get JCR VersionHistory for the node.
     *
     * @return version history
     */
    VersionHistory getVersionHistory();

    /**
     * Check if a resource was created in this session
     * @return if resource created in this session
     */
    Boolean isNew();


    /**
     * Construct an ETag value from the last modified date and path. JCR has a
     * mix:etag type, but it only takes into account binary properties. We
     * actually want whole-object etag data. TODO : construct and store an ETag
     * value on object modify
     *
     * @return constructed etag value
     */
    String getEtagValue();

    /**
     * Enable versioning
     */
    void enableVersioning();

    /**
     * Disable versioning
     */
    void disableVersioning();

    /**
     * Check if a resource is versioned
     * @return whether the resource is versioned
     */
    boolean isVersioned();

    /**
     * Check if a resource is frozen (a historic version).
     * @return whether the resource is frozen
     */
    boolean isFrozenResource();

    /**
     * When this is a frozen node, get the ancestor that was explicitly versioned
     * @return the ancestor that was explicity versioned
     */
    Resource getVersionedAncestor();

    /**
     * Get the unfrozen equivalent of a frozen versioned node
     * @return the unfrozen equivalent of a frozen versioned node
     */
    Resource getUnfrozenResource();

    /**
     * Get the node for this object at the version provided.
     * @param label the label
     * @return the node for this object at the version provided
     */
    Node getNodeVersion(String label);


}
