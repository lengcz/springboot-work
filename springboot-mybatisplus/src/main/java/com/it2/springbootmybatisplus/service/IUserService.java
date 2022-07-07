package com.it2.springbootmybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.it2.springbootmybatisplus.pojo.User;
import org.springframework.stereotype.Service;

public interface IUserService extends IService<User> {


    /**
     * 自定义的方法
     * @param id
     * @return
     */
    User getById2(Integer id);

}