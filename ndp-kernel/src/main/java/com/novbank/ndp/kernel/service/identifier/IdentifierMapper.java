package com.novbank.ndp.kernel.service.identifier;

import com.novbank.ndp.kernel.util.convert.Converter;

/**
 * An {@link IdentifierMapper} accepts and returns identifiers, translating
 * them in some type-specific manner. The typical use of this
 * contract is for translating between internal and external identifiers.
 *
 * Created by CaoKe on 2015/5/16.
 */
public interface IdentifierMapper<A, B> extends Converter<A, B> {
    /**
     * Check if the given resource is in the domain of this converter
     * @param resource the given resource
     * @return boolean value of the check
     */
    default boolean inDomain(final A resource) {
        return convert(resource) != null;
    }

    /**
     * Convert a plain string to a resource appropriate to this converter
     * @param resource the plain string resource
     * @return the resource appropriate to this converter
     */
    A toDomain(final String resource);

    /**
     * Convert the given resource into a plain string representation of the conversion to the resource
     * @param resource the given resource
     * @return a plain string representation of the conversion to the resource
     */
    String asString(final A resource);
}
