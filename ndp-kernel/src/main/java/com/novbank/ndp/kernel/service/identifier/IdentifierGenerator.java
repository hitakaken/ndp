package com.novbank.ndp.kernel.service.identifier;

/**
 * Defines the behavior of a component that can accept responsibility
 * for the creation of NDP PIDs.
 *
 * Created by CaoKe on 2015/5/16.
 */
public interface IdentifierGenerator {
    /**
     * Generate new PID
     * @return a new identifier
     */
    String newIdentifier();
}
