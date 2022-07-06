package com.it2.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.it2.springbootmybatisplus.pojo.Book;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface BookMapper extends BaseMapper<Book> {
}

