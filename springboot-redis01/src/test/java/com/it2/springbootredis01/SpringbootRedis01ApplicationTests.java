package com.it2.springbootredis01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class SpringbootRedis01ApplicationTests {


    @Test
    void set(@Autowired RedisTemplate redisTemplate) {
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("a","123");

    }

    @Test
    void get(@Autowired RedisTemplate redisTemplate) {
        ValueOperations ops = redisTemplate.opsForValue();
        System.out.println(ops.get("a"));

    }

    @Test
    void hset(@Autowired RedisTemplate redisTemplate){
        HashOperations ops=  redisTemplate.opsForHash();
        ops.put("key1","name","xiaowang");
        ops.put("key1","age","11");
    }

    @Test
    void hget(@Autowired RedisTemplate redisTemplate){
        HashOperations ops=  redisTemplate.opsForHash();
        System.out.println(ops.get("key1","name"));
        System.out.println(ops.entries("key1"));
    }

}
