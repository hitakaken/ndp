package com.novbank.ndp.kernel.impl.model.hadoop;

import org.apache.avro.reflect.Nullable;

/**
 * Created by hp on 2015/5/20.
 */
public class FileInfo {
    private String name;
    private @Nullable String path;
    private long size;
    private long modified;

    public FileInfo(String name, String path, long size, long modified) {
        this.name = name;
        this.path = path;
        this.size = size;
        this.modified = modified;
    }

    public FileInfo() {
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public long getModified() {
        return modified;
    }
}
