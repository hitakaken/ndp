package com.novbank.ndp.kernel.observer;

import javax.jcr.Session;
import javax.jcr.observation.Event;
import java.util.function.Predicate;

/**
 * Filter JCR events to remove extraneous events
 *
 * Created by CaoKe on 2015/5/16.
 */
public interface EventFilter extends Predicate<Event> {
    /**
     * Return a {@link Predicate} with which to filter JCR {@link Event}s.
     *
     * @param session the session
     * @return Predicate
     */
    Predicate<Event> getFilter(final Session session);
}
