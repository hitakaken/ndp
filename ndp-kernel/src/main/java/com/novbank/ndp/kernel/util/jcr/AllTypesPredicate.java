package com.novbank.ndp.kernel.util.jcr;

/**
 * Predicate to match nodes with all of the given mixin types
 *
 * Created by CaoKe on 2015/5/16.
 */
public class AllTypesPredicate extends BooleanTypesPredicate {
    private final int test;

    /**
     * True if all the types specified match.
     * @param types the types
     */
    public AllTypesPredicate(String... types) {
        super(types);
        this.test = types.length;
    }

    @Override
    protected boolean test(final int matched) {
        return matched == test;
    }
}
