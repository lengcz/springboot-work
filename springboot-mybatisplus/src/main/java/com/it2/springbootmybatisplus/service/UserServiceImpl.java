package com.it2.springbootmybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it2.springbootmybatisplus.mapper.UserMapper;
import com.it2.springbootmybatisplus.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private  UserMapper userMapper;

    /**
     * 如果mybatis-plus提供的方法不够，可以自己扩展新方法
     * @param id
     * @return
     */
    @Override
    public User getById2(Integer id) {
        return userMapper.getById2(id);
    }


}
