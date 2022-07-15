package com.it2.springbootmybatisplus.controller;

import com.it2.springbootmybatisplus.pojo.SmsCode;
import com.it2.springbootmybatisplus.util.SmsUtils;
import net.oschina.j2cache.CacheChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
public class SmsCodeController {

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private CacheChannel cacheChannel;


    @GetMapping
    public String getCode(String phone) {
        String code= smsUtils.createCode(phone);
        cacheChannel.set("sms",phone,code);
        return code;
    }

    @PostMapping
    public boolean checkCode(SmsCode smsCode) {
        String code = smsCode.getCode();
//        String code2 = smsUtils.getCode(smsCode.getPhone());
        String code2 = cacheChannel.get("sms",smsCode.getPhone()).asString();
        return code.equals(code2);
    }

}
