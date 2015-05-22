package com.novbank.ndp.kernel.impl.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

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
