package com.novbank.ndp.core.schema.type;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Sets;
import org.javatuples.Pair;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Created by hp on 2015/6/2.
 */
public class SimpleRecordTypeContainer implements RecordTypeContainer {
    protected Set<Pair<String, RecordType>> binds;

    public SimpleRecordTypeContainer() {
        this(new HashSet<>());
    }

    public SimpleRecordTypeContainer(Set<Pair<String, RecordType>> binds) {
        this.binds = binds;
    }

    private Optional<Pair<String,RecordType>> find(String className){
        return binds.stream().filter(p -> p.getValue0().equals(className)).findFirst();
    }

    @Override
    public void bind(String className, RecordType type) {
        Optional<Pair<String,RecordType>> existing = find(className);
        if(existing.isPresent())
            existing.get().setAt1(type);
        else
            binds.add(new Pair<>(className,type));
    }

    @Override
    public void unbind(String className, RecordType type) {
        binds.stream()
                .sorted()
                .filter(p -> Objects.equals(p.getValue0(),className) && Objects.equals(p.getValue1(),type))
                .peek(binds::remove);
    }

    @Override
    public void unbind(String className) {
        binds.stream()
                .sorted()
                .filter(p -> Objects.equals(p.getValue0(), className))
                .peek(binds::remove);
    }

    @Override
    public void unbind(RecordType type) {
        binds.stream()
                .sorted()
                .filter(p -> Objects.equals(p.getValue1(), type))
                .peek(binds :: remove);
    }

    @Override
    public RecordType getRecordType(String className) {
        Optional<Pair<String,RecordType>> existing = find(className);
        return existing.isPresent()?existing.get().getValue1():null;
    }
}
