package com.novbank.ndp.kernel.core.metadata.schema;

import javax.validation.Validator;

/**
 * Created by ken on 15-5-26.
 */
public interface RecordProperty extends Comparable<RecordProperty>{
    String getName();

    Validator getValidator();
}
