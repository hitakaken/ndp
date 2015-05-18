package com.novbank.ndp.kernel.observer;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

import static com.google.common.base.Functions.forMap;
import static com.google.common.collect.ImmutableMap.builder;

/**
 * A convenient abstraction over JCR's integer-typed events.
 *
 * Created by hp on 2015/5/15.
 */
public enum EventType {
    NODE_ADDED(javax.jcr.observation.Event.NODE_ADDED, "node added"),
    NODE_REMOVED(javax.jcr.observation.Event.NODE_REMOVED, "node removed"),
    PROPERTY_ADDED(javax.jcr.observation.Event.PROPERTY_ADDED, "property added"),
    PROPERTY_REMOVED(javax.jcr.observation.Event.PROPERTY_REMOVED, "property removed"),
    PROPERTY_CHANGED(javax.jcr.observation.Event.PROPERTY_CHANGED, "property changed"),
    NODE_MOVED(javax.jcr.observation.Event.NODE_MOVED, "node moved"),
    PERSIST(javax.jcr.observation.Event.PERSIST, "persist");

    private static final Map<Integer, EventType> translation;

    private final Integer jcrEventType;

    private final String eventName;


    /*
     * Create a translation map
     */
    static {
        final ImmutableMap.Builder<Integer, EventType> b = builder();
        for (final EventType eventType : values()) {
            b.put(eventType.jcrEventType, eventType);
        }
        translation = b.build();
    }

    EventType(final Integer eventType, final String eventName) {
        this.jcrEventType = eventType;
        this.eventName = eventName;
    }

    /**
     * @return a human-readable name for this event
     */
    public String getName() {
        return this.eventName;
    }

    /**
     * Get the NDP event type for a JCR type
     *
     * @param i the integer value of a JCR type
     * @return EventType
     */
    public static EventType valueOf(final Integer i) {
        return forMap(translation).apply(i);
    }
}
