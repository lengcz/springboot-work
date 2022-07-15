package com.it2.springbootmybatisplus.config;


import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;


@Configuration
public class MyCacheConfig {

    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
        KeyGenerator keyGenerator=new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
//                System.out.println(target);//实现类本身
//                System.out.println(method.getName());//调用的方法名
//                System.out.println(params); //形参

                String key=target.getClass().getName()+"["+ Arrays.asList(params).toString()
                        +"]";

                return key;
            }
        };
        return keyGenerator;
    }
}
