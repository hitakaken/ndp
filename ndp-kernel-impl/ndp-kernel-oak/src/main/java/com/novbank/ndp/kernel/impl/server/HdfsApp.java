package com.novbank.ndp.kernel.impl.server;

import com.novbank.ndp.kernel.impl.model.hadoop.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.hadoop.store.DataStoreWriter;
import org.springframework.data.hadoop.store.dataset.DatasetOperations;

/**
 * Created by hp on 2015/5/20.
 */
@ComponentScan
@EnableAutoConfiguration
public class HdfsApp {
    public static void main(String[] args) {
        SpringApplication.run(HdfsApp.class, args);
    }
}
