package com.novbank.ndp.kernel.exception;

/**
 * Created by CaoKe on 2015/5/16.
 */
public interface Problem {
    String getMessage();

    Throwable getThrowable();
}
