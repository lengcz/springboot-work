package com.it2.springbootmybatisplus.controller;

import com.it2.springbootmybatisplus.pojo.SmsCode;
import com.it2.springbootmybatisplus.util.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
public class SmsCodeController {

    @Autowired
    private SmsUtils smsUtils;

    @GetMapping
    public String getCode(String phone) {
        return smsUtils.createCode(phone);
    }

    @PostMapping
    public boolean checkCode(SmsCode smsCode) {
        String code = smsCode.getCode();
        String code2 = smsUtils.getCode(smsCode.getPhone());
        return code.equals(code2);
    }

}
