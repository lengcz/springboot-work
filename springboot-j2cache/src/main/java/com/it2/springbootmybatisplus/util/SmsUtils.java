package com.it2.springbootmybatisplus.util;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SmsUtils {

//    @CachePut(value="smscode",key="#phone")
    public String createCode(String phone) {
        int rand = new Random().nextInt(1000_000);
        return String.format("%06d",rand);
    }


//    @Cacheable(value="smscode",key="#phone")
    public String getCode(String phone) {
        return null;
    }

}
