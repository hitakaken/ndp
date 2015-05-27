package com.novbank.ndp.kernel.core.metadata.schema;

import com.beust.jcommander.internal.Maps;
import org.junit.Test;
import org.mapdb.*;

import java.util.Map;
import java.util.NavigableSet;
import java.util.Observable;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by hp on 2015/5/27.
 */
public class NamespaceTest {

    @Test
    public void testGetSpace() throws Exception {
        DB db = DBMaker.newMemoryDB().transactionDisable().closeOnJvmShutdown().make();
        BTreeMap<Namespace,String> namespaces = db.createTreeMap("namespaces").counterEnable().makeOrGet();;
        Map<Namespace,String> urls = Maps.newHashMap();
        Map<Namespace,String> prefixes = Maps.newHashMap();
        Bind.secondaryValue(namespaces,urls, (namespace, space) -> namespace.getUrl());
        Bind.secondaryValue(namespaces,prefixes, (namespace, space) -> namespace.getPrefix());
        Namespace foaf = new Namespace("xmlns.com",null,null);
        namespaces.put(foaf,foaf.getSpace());
        foaf.setUrl("http://xmlns.com/foaf/0.1/");
        foaf.setPrefix("foaf");
        System.out.println(namespaces);
        System.out.println(urls);
        System.out.println(prefixes);
    }
}