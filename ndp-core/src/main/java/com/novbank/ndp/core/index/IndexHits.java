package com.novbank.ndp.core.index;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 命中索引结果
 *
 * Created by hp on 2015/6/1.
 */
public interface IndexHits<T> extends Iterator<T>,Iterable<T>,AutoCloseable {
    /**
     * @return 命中的记录数
     */
    int size();

    /**
     * @return 返回唯一的记录，不存在则返回 {@code null}。命中数非唯一，则抛出 {@link NoSuchElementException}
     */
    T getSingle();

    /**
     * @return 当前记录的打分值
     */
    float currentScore();
}
