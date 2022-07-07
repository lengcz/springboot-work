package com.it2.springbootmybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.it2.springbootmybatisplus.pojo.Book;

public interface IBookService extends IService<Book> {


    Book queryById(Integer id);

    Book queryById2(Integer id);

    boolean deleteBook(Integer id);

}