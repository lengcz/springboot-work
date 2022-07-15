package com.it2.springbootmybatisplus;

import com.it2.springbootmybatisplus.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {


    @Autowired
    private BookMapper bookMapper;

    @Test
    public void hello(){
        System.out.println(bookMapper.selectById(1).getName());
    }

}
