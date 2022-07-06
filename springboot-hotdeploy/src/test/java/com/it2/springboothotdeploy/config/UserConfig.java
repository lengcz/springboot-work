package com.it2.springboothotdeploy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public String abc(){
        return "abcfg";
    }
}


