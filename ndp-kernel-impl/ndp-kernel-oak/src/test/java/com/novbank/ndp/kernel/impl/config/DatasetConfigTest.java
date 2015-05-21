package com.novbank.ndp.kernel.impl.config;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.hadoop.fs.HdfsResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by hp on 2015/5/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/hadoop-context.xml")
@TestPropertySource(properties = { "spring.hadoop.fsUri=hdfs://SHLIB-534:9000"})
public class DatasetConfigTest {
    @Autowired
    private org.apache.hadoop.conf.Configuration conf;

    @Autowired
    private FileSystem hdfs;

    @Autowired
    @Qualifier("loader")
    private HdfsResourceLoader loader;

    @Test
    public void testDataStoreWriter() throws Exception {
        //conf.set("fs.default.name","hdfs://SHLIB-534:9000");
        /*UserGroupInformation ugi = UserGroupInformation.createProxyUser("kcao", UserGroupInformation.getLoginUser());

        ugi.doAs((PrivilegedExceptionAction) () -> {
            org.apache.hadoop.conf.Configuration jobconf = new org.apache.hadoop.conf.Configuration();
            jobconf.set("fs.default.name", "hdfs://SHLIB-534:9000");
            FileSystem fs = FileSystem.get(jobconf);
            FileStatus[] files = fs.listStatus(new Path("/home"));
            Arrays.asList(files).forEach(f -> System.out.println(f.getPath()));
            return null;
        });*/
        //FileSystem fs = FileSystem.get(conf);
        /*FileSystem fs = FileSystem.get(FileSystem.getDefaultUri(conf),conf,"xtwh");
        FileStatus[] files = fs.listStatus(new Path("/"));
        Arrays.asList(files).forEach(f -> System.out.println(f.getPath()));
        for (FileStatus file : files) {

        }*/
        Arrays.asList(hdfs.listStatus(new Path("/"))).forEach(f -> System.out.println(f.getPath()));
        System.out.println("");
        Arrays.asList(loader.getResources("/*")).forEach(r -> System.out.println(r.getFilename()));

    }
}