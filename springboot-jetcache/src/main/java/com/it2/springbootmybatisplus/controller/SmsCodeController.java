package com.it2.springbootmybatisplus.controller;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.it2.springbootmybatisplus.pojo.SmsCode;
import com.it2.springbootmybatisplus.util.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
public class SmsCodeController {

    @Autowired
    private SmsUtils smsUtils;

    @CreateCache(name="jetCache",expire = 3600,cacheType= CacheType.LOCAL)
    private Cache<String,String> jetCache;

    @GetMapping
    public String getCode(String phone) {
        String code= smsUtils.createCode(phone);
        jetCache.put(phone,code);
        return code;
    }

    @PostMapping
    public boolean checkCode(SmsCode smsCode) {
        String code = smsCode.getCode();
//        String code2 = smsUtils.getCode(smsCode.getPhone());
        String code2 =  jetCache.get(smsCode.getPhone());
        return code.equals(code2);
    }

}
