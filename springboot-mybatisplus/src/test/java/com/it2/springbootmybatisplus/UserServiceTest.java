package com.it2.springbootmybatisplus;

import com.it2.springbootmybatisplus.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {


    @Autowired
    private IUserService userService;
    @Test
    void testGetById2(){
        System.out.println(userService.getById2(1).getName());

    }
}
