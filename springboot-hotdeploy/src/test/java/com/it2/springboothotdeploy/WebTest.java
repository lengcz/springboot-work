package com.it2.springboothotdeploy;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.*;

@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //开启mvc调用
public class WebTest {

    //通过形参方式注入Mvc对象
    @Test
    void hello(@Autowired MockMvc mockMvc) throws Exception {
        //创建请求，http://localhost:xxxx/hello
        MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.get("/hello");
       ResultActions action=mockMvc.perform(builder);

    }

    //通过形参方式注入Mvc对象
    @Test
    void hello2(@Autowired MockMvc mockMvc) throws Exception {
        //创建请求，http://localhost:xxxx/hello
        MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.get("/hello2");
        ResultActions action=mockMvc.perform(builder);

        StatusResultMatchers status= MockMvcResultMatchers.status(); //定义预期执行状态
        ResultMatcher ok=status.isOk();//设定的预期状态
        //将请求结果与预期结果比对
        action.andExpect(ok);

    }

    //通过形参方式注入Mvc对象
    @Test
    void hello3(@Autowired MockMvc mockMvc) throws Exception {
        //创建请求，http://localhost:xxxx/hello
        MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.get("/hello");
        ResultActions action=mockMvc.perform(builder);

        ContentResultMatchers content= MockMvcResultMatchers.content();
//        ResultMatcher ok=content.string("hello2");
        ResultMatcher result=content.json("{\"flag\":true}");
        //将请求结果与预期结果比对
        action.andExpect(result);

    }

    //通过形参方式注入Mvc对象
    @Test
    void hello4(@Autowired MockMvc mockMvc) throws Exception {
        //创建请求，http://localhost:xxxx/hello
        MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.get("/getById");
        ResultActions action=mockMvc.perform(builder);

        ContentResultMatchers content= MockMvcResultMatchers.content();
        ResultMatcher result=content.json("{\"id\":1,\"name\":\"wangsan\"}");
        //将请求结果与预期结果比对
        action.andExpect(result);

    }

    //通过形参方式注入Mvc对象
    @Test
    void hello5(@Autowired MockMvc mockMvc) throws Exception {
        //创建请求，http://localhost:xxxx/hello
        MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.get("/getById");
        ResultActions action=mockMvc.perform(builder);

        HeaderResultMatchers header= MockMvcResultMatchers.header();
        ResultMatcher result=header.string("Content-Type","application/json");//匹配响应头
        //将请求结果与预期结果比对
        action.andExpect(result);

    }
}
