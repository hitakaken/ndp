package com.novbank.ndp.kernel.impl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.config.annotation.EnableHadoop;
import org.springframework.data.hadoop.config.annotation.SpringHadoopConfigurerAdapter;
import org.springframework.data.hadoop.config.annotation.builders.HadoopConfigConfigurer;

/**
 * Created by hp on 2015/5/20.
 */
@Configuration
@EnableHadoop
public class HadoopConfig extends SpringHadoopConfigurerAdapter {
    @Override
    public void configure(HadoopConfigConfigurer config) throws Exception {
        config.fileSystemUri("hdfs://SHLIB-534:9000").resourceManagerAddress("SHLIB-534:8032");
    }
}
