package com.it2.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.it2.springbootmybatisplus.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {


    /**
     * 自定义的扩展方法,注解写法
     * @return
     */
    @Select("SELECT * FROM user WHERE id=${id}")
    public User getById2(int id);



}

