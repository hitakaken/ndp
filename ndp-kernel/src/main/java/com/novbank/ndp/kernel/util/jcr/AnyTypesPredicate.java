package com.novbank.ndp.kernel.util.jcr;

/**
 * Predicate to match nodes with any of the given mixin types
 *
 * Created by CaoKe on 2015/5/16.
 */
public class AnyTypesPredicate extends BooleanTypesPredicate{
    /**
     * True if any of the types specified match.
     * @param types the types
     */
    public AnyTypesPredicate(final String...types) {
        super(types);
    }

    @Override
    protected boolean test(final int matched) {
        return matched > 0;
    }
}
