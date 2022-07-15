package com.it2.springbootmybatisplus.config;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class XMemcachedConfig {

    @Bean
    public MemcachedClient memcachedClient() throws IOException {
        MemcachedClientBuilder builder=new XMemcachedClientBuilder("106.13.2.249:11211");
        builder.setConnectionPoolSize(10);//设置连接池数量
        builder.setOpTimeout(5000);//设置操作超时时间
        MemcachedClient memcachedClient=builder.build();
        return memcachedClient;
    }
}
