package com.it2.springbootmybatisplus.service;

import com.alicp.jetcache.anno.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it2.springbootmybatisplus.mapper.BookMapper;
import com.it2.springbootmybatisplus.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    @Cached(name="book_",key="#id",expire = 3600)
    @CacheRefresh(refresh = 30)
    public Book queryById(Integer id) {
        System.out.println("读取数据库:id="+id);
        return bookMapper.selectById(id);
    }

    @Override
    public Book queryById2(Integer id) {
        System.out.println("使用myKeyGenerator生成key,读取数据库:id="+id);
        return bookMapper.selectById(id);
    }

    @Override
    @CacheInvalidate(name="book_",key="#id")
    public boolean deleteBook(Integer id) {
        System.out.println("删除了数据:id="+id);
        return true;
    }

    @Override
    @CacheUpdate(name="book_",key="#book.id",value="#book")
    public boolean updateBook(Book book) {
        return bookMapper.updateById(book)>0;
    }


}