package com.it2.springbootmybatisplus.controller;

import com.it2.springbootmybatisplus.pojo.SmsCode;
import com.it2.springbootmybatisplus.util.SmsUtils;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/sms")
public class SmsCodeController {

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private MemcachedClient memcachedClient;

    @GetMapping
    public String getCode(String phone) {
        String code= smsUtils.createCode(phone);
        try {
            memcachedClient.set(phone,10,code);// 存入缓存
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    @PostMapping
    public boolean checkCode(SmsCode smsCode) {
        String code = smsCode.getCode();
        String code2 = null;
        try {
            code2 = memcachedClient.get(smsCode.getPhone());//取出
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code.equals(code2);
    }

}
