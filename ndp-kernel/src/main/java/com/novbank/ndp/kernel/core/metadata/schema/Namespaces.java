package com.novbank.ndp.kernel.core.metadata.schema;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import org.javatuples.Triplet;
import org.joor.Reflect;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hp on 2015/5/27.
 */
public class Namespaces implements Set<Namespace>, PropertyChangeListener, VetoableChangeListener {
    protected String idName ;
    protected Function<Namespace,String> idFunction;
    protected ConcurrentMap<String, Namespace> ids = Maps.newConcurrentMap();
    protected ConcurrentMap<String, Triplet<
            Function<Namespace,Object>,
            Boolean,
            Multimap<Object,Namespace>>>  indexes = Maps.newConcurrentMap();

    public Namespaces() {
        setIdProperty("space", Namespace::getSpace);
        addIndex("url", true,Namespace :: getUrl);
        addIndex("prefix", true, Namespace :: getPrefix);
    }

    public void setIdProperty(String idName, Function<Namespace,String> idFunction){
        this.idName = idName;
        this.idFunction = idFunction;
    }

    public  void addIndex(String indexName, boolean unique){
        addIndex(indexName, unique, null);
    }

    public  void addIndex(String indexName, boolean unique, Function<Namespace,Object> indexFunction){
        checkNotNull(indexName);
        final Multimap<Object, Namespace> index = HashMultimap.create();
        final Function<Namespace, Object> function = indexFunction!=null? indexFunction : n -> Reflect.on(n).get(indexName);
        ids.values().forEach(n->{
            Object key = function.apply(n);
            index.put(key,n);
        });
        boolean valid = true;
        if(unique){
            for(Object key : index.keySet()){
                if(key!=null && index.get(key).size()>1) {
                    valid =false;
                    break;
                }
            }
        }
        if(valid)
            indexes.put(indexName, new Triplet<>(function, unique, index));
        else
            throw new RuntimeException("Index: "+ indexName+" expect Unique, but fails");
    }

    public void removeIndex(String indexName){
        if(indexes.containsKey(indexName))
            indexes.remove(indexName);
    }

    public Collection<Namespace> getByIndex(String indexName, Object key){
        if(indexName!=null && indexes.containsKey(indexName)
                && indexes.get(indexName).getValue2().containsKey(key))
            return indexes.get(key).getValue2().get(key);
        else
            return Collections.EMPTY_LIST;
    }

    @Override
    public int size() {
        return ids.keySet().size();
    }

    @Override
    public boolean isEmpty() {
        return ids.keySet().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return ids.values().contains(o);
    }

    @Override
    public Iterator<Namespace> iterator() {
        return ids.values().iterator();
    }

    @Override
    public Object[] toArray() {
        return ids.values().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return ids.values().toArray(a);
    }

    @Override
    public boolean add(Namespace namespace) {
        checkNotNull(namespace);
        String id = idFunction.apply(namespace);
        if(id == null || ids.containsKey(id))
            return false;
        Map<String, Object> indexValues = Maps.newHashMap();
        for(String indexName : indexes.keySet()){
            Object indexValue = indexes.get(indexName).getValue0().apply(namespace);
            if(indexValue!=null && indexes.get(indexName).getValue1()
                    && indexes.get(indexName).getValue2().containsKey(indexValue))
                return false;
            indexValues.put(indexName, indexValue);
        }
        ids.put(id,namespace);
        for(String indexName :  indexes.keySet())
            indexes.get(indexName).getValue2().put(indexValues.get(indexName),namespace);
        namespace.addVetoableChangeListener(this);
        namespace.addPropertyChangeListener(this);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Set<Namespace> toRemove = Sets.newHashSet();
        for(Namespace n : ids.values())
            if(n.equals(o))
                toRemove.add(n);
        boolean success = true;
        for(Namespace n: toRemove)
            success = success && removeItem(n);
        return success;
    }

    public boolean removeItem(Namespace namespace) {
        String id = idFunction.apply(namespace);
        ids.remove(id);
        for(String indexName : indexes.keySet()){
            Object indexValue = indexes.get(indexName).getValue0().apply(namespace);
            if(indexValue!=null && indexes.get(indexName).getValue1())
                indexes.get(indexName).getValue2().removeAll(indexValue);
            else
                indexes.get(indexName).getValue2().remove(indexValue,namespace);
        }
        namespace.removeVetoableChangeListener(this);
        namespace.removePropertyChangeListener(this);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return ids.values().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Namespace> c) {
        boolean success = true;
        for(Namespace n : c)
            success = success && add(n);
        return success;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Set<Namespace> toRemove = Sets.newHashSet();
        for(Namespace n : ids.values())
            if(!c.contains(n))
                toRemove.add(n);
        boolean success = true;
        for(Namespace n: toRemove)
            success = success && removeItem(n);
        return success;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean success = true;
        for(Object n : c)
            success = success && remove(n);
        return success;
    }

    @Override
    public void clear() {
        ids.values().forEach(n-> {
            n.removePropertyChangeListener(this);
            n.removeVetoableChangeListener(this);
        });
        ids.clear();
        for(String indexName : indexes.keySet()){
            indexes.get(indexName).getValue2().clear();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Namespace source = (Namespace) evt.getSource();
        if(evt.getPropertyName().equals(idName)){
            ids.remove(evt.getOldValue());
            ids.put((String) evt.getNewValue(),source);
            return;
        }
        if(indexes.containsKey(evt.getPropertyName())){
            indexes.get(evt.getPropertyName()).getValue2().remove(evt.getOldValue(),source);
            if(indexes.get(evt.getPropertyName()).getValue2().get(evt.getOldValue()).isEmpty())
                indexes.get(evt.getPropertyName()).getValue2().removeAll(evt.getOldValue());
            indexes.get(evt.getPropertyName()).getValue2().put(evt.getNewValue(),source);
        }
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if(evt.getPropertyName().equals(idName)){
            String newId = (String) evt.getNewValue();
            if(newId == null)
                throw new PropertyVetoException("Id Field Can not be null",evt);
            if(ids.containsKey(newId))
                throw new PropertyVetoException("Id: "+newId+ " already exists", evt);
            return;
        }
        if(indexes.containsKey(evt.getPropertyName())){
            if(evt.getNewValue() !=null && indexes.get(evt.getPropertyName()).getValue1()
                    && indexes.get(evt.getPropertyName()).getValue2().containsKey(evt.getNewValue()))
                throw new PropertyVetoException("Unique Property: "+ evt.getPropertyName()+ " already has value: " + evt.getNewValue(), evt);
        }
    }
}
