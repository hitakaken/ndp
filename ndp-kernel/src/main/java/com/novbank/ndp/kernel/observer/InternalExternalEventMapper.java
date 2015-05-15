package com.novbank.ndp.kernel.observer;

import javax.jcr.observation.Event;
import java.util.Iterator;
import java.util.function.Function;

/**
 * Created by hp on 2015/5/15.
 */
public interface InternalExternalEventMapper extends Function<Iterator<Event>, Iterator<NdpEvent>> {

}
