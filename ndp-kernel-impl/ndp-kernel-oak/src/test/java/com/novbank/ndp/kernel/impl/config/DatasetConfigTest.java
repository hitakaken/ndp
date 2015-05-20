package com.novbank.ndp.kernel.impl.config;

import com.novbank.ndp.kernel.impl.server.HdfsApp;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.hadoop.store.dataset.DatasetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.PrivilegedExceptionAction;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by hp on 2015/5/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/hadoop-context.xml")
public class DatasetConfigTest {
    @Autowired
    private org.apache.hadoop.conf.Configuration conf;

    @Test
    public void testDataStoreWriter() throws Exception {
        //conf.set("fs.default.name","hdfs://SHLIB-534:9000");
        UserGroupInformation ugi = UserGroupInformation.createProxyUser("kcao", UserGroupInformation.getLoginUser());

        ugi.doAs((PrivilegedExceptionAction) () -> {
            org.apache.hadoop.conf.Configuration jobconf = new org.apache.hadoop.conf.Configuration();
            jobconf.set("fs.default.name", "hdfs://SHLIB-534:9000");
            FileSystem fs = FileSystem.get(jobconf);
            FileStatus[] files = fs.listStatus(new Path("/home"));
            Arrays.asList(files).forEach(f -> System.out.println(f.getPath()));
            return null;
        });
        FileSystem fs = FileSystem.get(conf);
        FileStatus[] files = fs.listStatus(new Path("/"));
        Arrays.asList(files).forEach(f -> System.out.println(f.getPath()));
    }
}