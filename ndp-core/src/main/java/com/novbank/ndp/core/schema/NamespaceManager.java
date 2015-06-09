package com.novbank.ndp.core.schema;

import org.javatuples.Triplet;

import javax.xml.namespace.NamespaceContext;
import java.util.Collection;

/**
 * 命名空间管理器
 * 命名空间： Triplet space,url,prefix
 * space 形如：x.y(.z)
 * url  形如：http(s)://x.y(.z)/*
 * abbreviation 形如：x(:)y
 *
 * space -> url OneToMany
 * url -> abbreviation OneToMany
 *
 * Created by hp on 2015/6/5.
 */
public interface NamespaceManager extends Iterable<Triplet<String,String,String>>,NamespaceContext {
    /**
     * @param space     命名空间
     * @param url       RDF url
     * @param abbreviation    RDF prefix
     * @throws IllegalArgumentException 一对多关系冲突抛出异常
     */
    void register(String space, String url, String abbreviation) throws IllegalArgumentException;

    /**
     * @param unique    唯一标识（非空的命名空间/RDF标识）
     */
    void unregister(String unique);

    /**
     * @param unique 唯一标识（非空的命名空间/RDF标识）
     * @return  命名空间对象，不存在返回 null
     */
    Iterable<Triplet<String,String,String>> getTriplets(String unique);

    /**
     * @param rdf 非空的RDF标识
     * @return 对应的命名空间, 不存在该RDF标识返回 Null，空字符串表示该RDF标识存在，但未定义命名空间
     */
    String findNamespace(String rdf);

    /**
     * @param abbreviation 缩写
     * @return  对应的url, 不存在该RDF标识返回 Null，空字符串表示该RDF标识存在，但未定义命名空间
     */
    String findUrlByAbbreviation(String abbreviation);

    /**
     * @param space 命名空间
     * @return  命名空间对应的 url, 不存在则返回 Null，空集合表示该命名空间不存在定义的url
     */
    Iterable<String> findUrlsByNamespace(String space);

    /**
     * @param unique 命名空间或RDF url
     * @return  对应的前缀
     */
    Iterable<String> findAbbreviations(String unique);

    /**
     * @return 记录条数
     */
    int size();

    /**
     * @return 命名空间列表
     */
    Collection<String> spaces();

    /**
     * @return url列表
     */
    Collection<String> urls();

    /**
     * @return 缩写列表
     */
    Collection<String> abbreviations();

    /**
     * 清空
     */
    void clear();
}
