package com.novbank.ndp.core.schema.internal;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.novbank.ndp.core.Constants;
import com.novbank.ndp.core.schema.NamespaceManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.javatuples.Triplet;
import org.jooq.lambda.Seq;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hp on 2015/6/5.
 */
public class DefaultNamespaceManager implements NamespaceManager {
    protected Set<Triplet<String,String,String>> namespaces;
    protected Multimap<String,Triplet<String,String,String>> spaces;
    protected Multimap<String,Triplet<String,String,String>> urls;
    protected Multimap<String,Triplet<String,String,String>> abbreviations;
    protected UrlValidator urlValidator;

    public DefaultNamespaceManager() {
        this(UrlValidator.getInstance());
    }

    public DefaultNamespaceManager(UrlValidator urlValidator) {
        this(Sets.newHashSet(),HashMultimap.create(),HashMultimap.create(),HashMultimap.create(),urlValidator);
    }

    public DefaultNamespaceManager(Set<Triplet<String, String, String>> namespaces, Multimap<String, Triplet<String, String, String>> spaces, Multimap<String, Triplet<String, String, String>> urls, Multimap<String, Triplet<String, String, String>> abbreviations, UrlValidator urlValidator) {
        this.namespaces = namespaces;
        this.spaces = spaces;
        this.urls = urls;
        this.abbreviations = abbreviations;
        this.urlValidator = urlValidator;
    }

    @Override
    public void register(String space, String url, String abbreviation) throws IllegalArgumentException {
        space = StringUtils.trimToEmpty(space);
        url = StringUtils.trimToEmpty(url);
        abbreviation = StringUtils.trimToEmpty(abbreviation);
        //枚举所有情况
        //1.所有输入为空
        if(space.length()+url.length()+abbreviation.length() == 0 )
            throw Exceptions.registerAllEmptyEntry();
        //2.仅注册命名空间
        else if(url.length()+abbreviation.length() == 0)
            registerSpace(space);
        //3.仅注册RDF URL
        else if(space.length()+abbreviation.length() == 0)
            registerUrl(url);
        //4.仅注册RDF Prefix
        else if(space.length()+url.length() == 0)
            registerAbbreviation(abbreviation);
        //5.注册关系：命名空间 <--> RDF URL
        else if(abbreviation.length() == 0)
            registerSpaceAndUrl(space, url);
        //6.注册关系：命名空间 <--> RDF Abbreviation
        else if(url.length() == 0)
            registerSpaceAndAbbreviation(space, abbreviation);
        //7.注册关系：RDF Url <--> RDF Abbreviation
        else if(space.length() == 0)
            registerUrlAndAbbreviation(url,abbreviation);
        //8.注册全信息
        else {

        }

    }

    /**
     * 注册命名空间完整条目
     * @param triplet 命名空间完整条目
     */
    protected void register(Triplet<String,String,String> triplet){
        namespaces.add(triplet);
        spaces.put(triplet.getValue0(), triplet);
        urls.put(triplet.getValue1(),triplet);
        abbreviations.put(triplet.getValue2(),triplet);
    }

    /**
     * @param space 命名空间
     * @return 合法返回 true, 非法返回 false
     */
    protected boolean validateSpace(String space){
        return (StringUtils.indexOf(space,'.') > 0
                && StringUtils.lastIndexOf(space,'.') < StringUtils.length(space) -2
                && StringUtils.containsOnly(Constants.LOWERCASE + Constants.UPPERCASE + "._"));
    }

    /**
     * @param url
     * @return 合法返回 true, 非法返回 false
     */
    protected boolean validateUrl(String url){
        return urlValidator.isValid(url);
    }

    /**
     * @param abbreviation 缩写
     * @return 合法返回 true, 非法返回 false
     */
    protected boolean validateAbbreviation(String abbreviation){
        return StringUtils.containsOnly(Constants.LOWERCASE + Constants.UPPERCASE + ":_");
    }

    protected void registerSpace(String space){
        //校验命名空间合法性
        if(validateSpace(space))
            throw Exceptions.invalidNamespace(space);
        else if(!spaces.containsKey(space)){
            register(new Triplet<>(space,StringUtils.EMPTY,StringUtils.EMPTY));
        }else throw Exceptions.alreadyExistsNamespace(space);
    }

    protected void registerUrl(String url){
        //校验 url 合法性
        if(validateUrl(url))
            throw Exceptions.invalidUrl(url);
        else if(!urls.containsKey(url)){
            register(new Triplet<>(StringUtils.EMPTY,url,StringUtils.EMPTY));
        }else throw Exceptions.alreadyExistsUrl(url);
    }

    protected void registerAbbreviation(String abbreviation){
        //校验缩写合法性
        if(validateAbbreviation(abbreviation))
            throw Exceptions.invalidAbbreviation(abbreviation);
        else if(!abbreviations.containsKey(abbreviation)){
            register(new Triplet<>(StringUtils.EMPTY,StringUtils.EMPTY,abbreviation));
        }else throw Exceptions.alreadyExistsAbbreviation(abbreviation);
    }

    protected void registerSpaceAndUrl(String space, String url) {
        //校验命名空间合法性
        if(validateSpace(space))
            throw Exceptions.invalidNamespace(space);
        //校验 url 合法性
        else if(validateUrl(url))
            throw Exceptions.invalidUrl(url);
        else if(!urls.containsKey(url))
            //如果 Rdf Url 不存在，则直接注册
            register(new Triplet<>(space,url,StringUtils.EMPTY));
        else {
            //当前 Rdf Url对应的命名空间
            String existing = findNamespaceByUrl(url);
            //如果未映射，则建立映射
            if (StringUtils.isEmpty(existing)){
                urls.get(url).stream().forEach(t -> {
                    spaces.remove(StringUtils.EMPTY,t);
                    t.setAt0(space);
                    spaces.put(space, t);
                });
            }else if(StringUtils.equals(existing,space))
                //命名空间相同则抛出已存在异常
                throw Exceptions.union(Exceptions.alreadyExistsNamespace(space),Exceptions.alreadyExistsUrl(url));
            else
                //命名空间不同则抛出冲突异常
                throw Exceptions.conflictUrl(space,url);
        }

    }

    protected void registerSpaceAndAbbreviation(String space, String abbreviation) {
        //校验命名空间合法性
        if(validateSpace(space))
            throw Exceptions.invalidNamespace(space);
        else if(validateAbbreviation(abbreviation))
            throw Exceptions.invalidAbbreviation(abbreviation);
        else if(!abbreviations.containsKey(abbreviation))
            //如果 Rdf Url 不存在，则直接注册
            register(new Triplet<>(space,StringUtils.EMPTY,abbreviation));
        else {
            //当前 Rdf Url对应的命名空间
            String existing = findNamespaceByAbbreviation(abbreviation);
            //如果未映射，则建立映射
            if (StringUtils.isEmpty(existing)){
                abbreviations.get(abbreviation).stream().forEach(t -> {
                    spaces.remove(StringUtils.EMPTY,t);
                    t.setAt0(space);
                    spaces.put(space, t);
                });
            }else if(StringUtils.equals(existing,space))
                //命名空间相同则抛出已存在异常
                throw Exceptions.union(Exceptions.alreadyExistsNamespace(space),Exceptions.alreadyExistsAbbreviation(abbreviation));
            else
                //命名空间不同则抛出冲突异常
            //TODO
                throw Exceptions.conflictUrl(space,abbreviation);
        }
    }

    protected void registerUrlAndAbbreviation(String url, String abbreviation) {
        //校验 url 合法性
        if(validateUrl(url))
            throw Exceptions.invalidUrl(url);
        else if(validateAbbreviation(abbreviation))
            throw Exceptions.invalidAbbreviation(abbreviation);
        else if(urls.containsKey(url) && abbreviations.containsKey(abbreviation)){

        } else if(urls.containsKey(url)){

        } else if(abbreviations.containsKey(abbreviation)){

        } else
            register(new Triplet<>(StringUtils.EMPTY,url,abbreviation));

    }


    @Override
    public void unregister(String unique) {
        //TODO
    }

    @Override
    public Iterable<Triplet<String, String, String>> getTriplets(String unique) {
        //TODO
        return null;
    }

    private String findNamespaceByUrl(String url) {
        if(!urls.containsKey(url))
            return null;
        Set<String> result = Seq.seq(urls.get(url)).map(Triplet :: getValue0).toSet();
        if(result.size() != 1)
            throw Exceptions.conflictNamespacesByUrl(url, result);
        else
            return result.iterator().next();
    }

    private String findNamespaceByAbbreviation(String abbreviation) {
        if(!abbreviations.containsKey(abbreviation))
            return null;
        Set<String> result = Seq.seq(abbreviations.get(abbreviation)).map(Triplet :: getValue0).toSet();
        if(result.size() != 1)
            throw Exceptions.conflictNamespacesByAbbreviation(abbreviation, result);
        else
            return result.iterator().next();
    }

    @Override
    public String findNamespace(String rdf) {
        if(validateUrl(rdf))
            return findNamespaceByUrl(rdf);
        else if(validateAbbreviation(rdf))
            return findNamespaceByAbbreviation(rdf);
        throw Exceptions.unknown(rdf);
    }

    @Override
    public String findUrlByAbbreviation(String abbreviation) {
        //TODO
        return null;
    }

    @Override
    public Iterable<String> findUrlsByNamespace(String space) {
        //TODO
        return null;
    }

    @Override
    public Iterable<String> findAbbreviations(String unique) {
        //TODO
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(String s) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    public static class Exceptions {
        public static IllegalArgumentException union(IllegalArgumentException... exceptions){
            return new IllegalArgumentException("Namespace manager exceptions:"+ System.lineSeparator()
                    + Seq.seq(Arrays.asList(exceptions)).map(Throwable::getMessage).join(System.lineSeparator()));
        }

        public static IllegalArgumentException registerAllEmptyEntry(){
            return new IllegalArgumentException("Space, url, abbreviation can not be all empty.");
        }

        public static IllegalArgumentException unknown(String unique){
            return new IllegalArgumentException(String.format("Input String %s is unknown format.",unique));
        }

        public static IllegalArgumentException invalidNamespace(String space){
            return new IllegalArgumentException(String.format("Namespace %s is invalid.",space));
        }

        public static IllegalArgumentException invalidUrl(String url){
            return new IllegalArgumentException(String.format("Url %s is invalid.",url));
        }

        public static IllegalArgumentException invalidAbbreviation(String abbreviation){
            return new IllegalArgumentException(String.format("Abbreviation %s is invalid.",abbreviation));
        }
        public static IllegalArgumentException alreadyExistsNamespace(String space){
            return new IllegalArgumentException(String.format("Namespace %s is already exists.",space));
        }


        public static IllegalArgumentException alreadyExistsUrl(String url){
            return new IllegalArgumentException(String.format("Url %s is already exists.",url));
        }

        public static IllegalArgumentException alreadyExistsAbbreviation(String abbreviation){
            return new IllegalArgumentException(String.format("Abbreviation %s is already exists.",abbreviation));
        }

        public static IllegalArgumentException conflictUrl(String space , String url){
            return new IllegalArgumentException(String.format("Url %s is conflict in Namespace %s.",url,space));
        }

        public static IllegalArgumentException conflictAbbreviation(String space , String url){
            return new IllegalArgumentException(String.format("Url %s is conflict in Namespace %s.",url,space));
        }

        public static IllegalArgumentException conflictNamespacesByUrl(String url, Set<String> namespaces) {
            return new IllegalArgumentException(String.format("Error! Url %s is conflict in Namespaces %s.",url, namespaces.toString()));
        }

        public static IllegalArgumentException conflictNamespacesByAbbreviation(String abbreviation, Set<String> namespaces) {
            return new IllegalArgumentException(String.format("Error! Abbreviation %s is conflict in Namespaces %s.",abbreviation, namespaces.toString()));
        }
    }


}
