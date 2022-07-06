package com.it2.springboothotdeploy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@Import(UserConfig.class)
public class ConfigTest2 {

    @Autowired
    private String abc;

    @Test
    void testConfig(){
        System.out.println(abc);
    }
}
