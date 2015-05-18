package com.novbank.ndp.kernel.observer;

import com.novbank.ndp.kernel.exception.RepositoryRuntimeException;

import javax.jcr.RepositoryException;
import javax.jcr.observation.Event;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Sets.union;
import static java.util.Collections.singleton;
import static javax.jcr.observation.Event.PROPERTY_ADDED;
import static javax.jcr.observation.Event.PROPERTY_CHANGED;
import static javax.jcr.observation.Event.PROPERTY_REMOVED;

/**
 * A very simple abstraction to prevent event-driven machinery downstream from the repository from relying directly
 * on a JCR interface {@link Event}. Can represent either a single JCR event or several.
 *
 * Created by hp on 2015/5/15.
 */
public class NdpEvent {
    private Event e;

    private Set<Integer> eventTypes = new HashSet<>();
    private Set<String> eventProperties = new HashSet<>();

    /**
     * Wrap a JCR Event with our NdpEvent decorators
     *
     * @param e the JCR event
     */
    public NdpEvent(final Event e) {
        checkArgument(e != null, "null cannot support a NDP Event!");
        this.e = e;
    }

    /**
     * Create a NdpEvent from an existing NdpEvent object
     * Note: Only the wrapped JCR event is passed on to the new object.
     *
     * @param e the given ndp event
     */
    public NdpEvent(final NdpEvent e) {
        checkArgument(e != null, "null cannot support a NDP Event!");
        this.e = e.e;
    }

    /**
     * @return the event types of the underlying JCR {@link Event}s
     */
    public Set<Integer> getTypes() {
        return eventTypes != null ? union(singleton(e.getType()), eventTypes) : singleton(e.getType());
    }

    /**
     * @param type the type
     * @return this object for continued use
     */
    public NdpEvent addType(final Integer type) {
        eventTypes.add(type);
        return this;
    }

    /**
     * @return the property names of the underlying JCR property {@link Event}s
     **/
    public Set<String> getProperties() {
        return eventProperties;
    }

    /**
     * Add a property name to this event
     * @param property property name
     * @return this object for continued use
     **/
    public NdpEvent addProperty( final String property ) {
        eventProperties.add(property);
        return this;
    }

    /**
     * @return the path of the underlying JCR {@link Event}s
     */
    public String getPath() {
        return getPath(e);
    }

    /**
     * Get the path of the node related to this event (removing property names
     * from the end of property nodes).
     * @param e JCR Event
     **/
    public static String getPath(final Event e) {
        try {
            // TODO: It would be better for this test to use a constant collection of:
            // - PROPERTY_ADDED, PROPERTY_CHANGED, PROPERTY_REMOVED and Collection.contains().
            if (e.getType() == PROPERTY_ADDED   ||
                    e.getType() == PROPERTY_CHANGED ||
                    e.getType() == PROPERTY_REMOVED) {
                return e.getPath().substring(0, e.getPath().lastIndexOf("/"));
            }
            return e.getPath();
        } catch (RepositoryException e1) {
            throw new RepositoryRuntimeException("Error getting event path!", e1);
        }
    }

    /**
     * @return the user ID of the underlying JCR {@link Event}s
     */
    public String getUserID() {
        return e.getUserID();
    }

    /**
     * @return the node identifer of the underlying JCR {@link Event}s
     */
    public String getIdentifier() {
        try {
            return e.getIdentifier();
        } catch (RepositoryException e1) {
            throw new RepositoryRuntimeException("Error getting event identifier!", e1);
        }
    }

    /**
     * @return the info map of the underlying JCR {@link Event}s
     */
    public Map<Object, Object> getInfo() {
        try {
            return new HashMap<>(e.getInfo());
        } catch (RepositoryException e1) {
            throw new RepositoryRuntimeException("Error getting event info!", e1);
        }
    }

    /**
     * @return the user data of the underlying JCR {@link Event}s
     */
    public String getUserData() {
        try {
            return e.getUserData();
        } catch (RepositoryException e1) {
            throw new RepositoryRuntimeException("Error getting event userData!", e1);
        }
    }

    /**
     * @return the date of the underlying JCR {@link Event}s
     */
    public long getDate() {
        try {
            return e.getDate();
        } catch (RepositoryException e1) {
            throw new RepositoryRuntimeException("Error getting event date!", e1);
        }
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("Event types:", String.join(",",
                        getTypes().stream().map(EventType::valueOf).toArray(String[]::new)))
                .add("Event properties:",
                        String.join(",", eventProperties))
                .add("Path:", getPath())
                .add("Date: ", getDate())
                .add("Info:", getInfo()).toString();
    }
}
